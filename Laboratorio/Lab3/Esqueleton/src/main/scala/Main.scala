import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.util.LongAccumulator

object Main {
  def main(args: Array[String]): Unit = {
    // Ejercicio 2.a
    // SparkSession
    /*
      * Creamos una SparkSession
        - appName: El nombre de la aplicación
        - master: El clúster en el que se ejecutará la aplicación
        - y getOrCreate(): Crea una SparkSession si no existe, o la devuelve si ya existe
    */
    val spark = SparkSession.builder()
      .appName("RedditNER")
      .master("local[*]")
      .getOrCreate()
    val sc = spark.sparkContext

    // Parse command-line arguments
    val cmdArgs = CommandLineArgs.parse(args) match {
      case Some(parsed) => parsed
      case None => return // scopt prints error messages
    }

    // Load subscriptions
    val subscriptionOpts = FileIO.readSubscriptions(cmdArgs.subscriptionFile)

    // Filter out malformed subscriptions (None values)
    val subscriptions = subscriptionOpts.flatten

    // Inciso a)
    // Convertimos la lista de subscriptions en un RDD
    /*
      * sc es el contexto de Spark, o sea, es el obj que representa la conexión con el clúster de Spark
      * parallelize es la función que crea un RDD a partir de la lista de subscriptions
      * RDD[Subscription] es basicamente una lista, pero que se puede ejecutar en paralelo y esta distribuida en distintos workers
    */
    val subscriptionsRDD: RDD[Subscription] = sc.parallelize(subscriptions)

/*
    // Download feeds and parse posts, tracking success/failure (for statistics)
    val downloadResults = subscriptions.map { subscription =>
      val feedOpt = FileIO.downloadFeed(subscription.url)
      val posts = feedOpt.fold(List[Post]())(JsonParser.parsePosts(_, subscription.name))
      (feedOpt.isDefined, posts)
    }
*/

    /* inciso b) y c)
      * Accumulators: variables que los workers pueden incrementar como "efecto secundario"
    */
    val accFeedsOk: LongAccumulator       = sc.longAccumulator("feedsSuccess")  // #Feeds que se descargaron bien
    val accFeedsFailed: LongAccumulator   = sc.longAccumulator("feedsFailed")   // #Feed que fallaron
    val accPostsParsed: LongAccumulator   = sc.longAccumulator("postsParsed")   // #Post parseados
    val accPostsFailed: LongAccumulator   = sc.longAccumulator("postsFailed")   // #Feed OK pero 0 Post parseados
    val accPostsFiltered: LongAccumulator = sc.longAccumulator("postsFiltered") // #Post vacios

    /* inciso b)
      * flatMap lo que hace es que nos va a devolver un RDD con todos los posts ya aplanados, o sea: 
        - con una entrada (RDD[Subscription]): [Sub 1, Sub 2, Sub3]
        - con flatMap (RDD[Post]): [Post 1, Post 2, Post 3, ...]
      * flatMap sobre el RDD de suscripciones:
        - descarga el feed de cada URL en paralelo
        - parsea los posts del JSON
        - filtra posts con title o selftext vacíos
        - actualiza los accumulators como efecto secundario
    */
    val downloadResultsRDD: RDD[Post] = subscriptionsRDD.flatMap { sub =>
      FileIO.downloadFeed(sub.url) match {
        case None =>
          // Feed que no se pudieron descargar
          accFeedsFailed.add(1)
          Iterator.empty
        case Some(json) =>
          // Feed que si se pudo descargar
          accFeedsOk.add(1)
          
          val allPost: List[Post] = JsonParser.parsePosts(json, sub.name)
          val postNotEmpty: List[Post] = Analyzer.filterEmptyPosts(allPost)
          
          accPostsParsed.add(allPost.length)
          accPostsFiltered.add(allPost.length - postNotEmpty.length)

          if (allPost.isEmpty) accPostsFailed.add(1)
          // @return Iterator[Post] -> luego spark lo convierte en RDD[Post]
          postNotEmpty.iterator
      }
    }

    // inciso c)
    /*
      * Forzamos la evaluacion del RDD para que los accumulators se llenen
    */
    val filteredPosts: List[Post] = downloadResultsRDD.collect().toList
    val filteredCount: Long = filteredPosts.length.toLong

    val feedsSuccess:  Long = accFeedsOk.value
    val feedsFailed:   Long = accFeedsFailed.value
    val postsSuccess:  Long = accPostsParsed.value
    val postsFailed:   Long = accPostsFailed.value
    val postsFiltered: Long = accPostsFiltered.value

    // Largo promedio en caracteres de los posts filtrados
    // Tomar con cuidado porque lo hizo la IA
    val totalChars: Long = filteredPosts.map(p => p.title.length + p.selftext.length).sum.toLong
    val avgChars: Int = if (filteredCount > 0) (totalChars / filteredCount).toInt else 0

/*
    // Count feed successes/failures
    val feedsSuccess = downloadResults.count(_._1)
    val feedsFailed = downloadResults.length - feedsSuccess

    // Flatten all posts and count JSON parse failures
    val allPosts = downloadResults.flatMap(_._2)
    val postsSuccess = allPosts.length
    val postsFailed = downloadResults.count(_._2.isEmpty)

    // Filter empty posts
    val filteredPosts = Analyzer.filterEmptyPosts(allPosts)
    val postsFiltered = allPosts.length - filteredPosts.length

    // Calculate average characters in filtered posts
    val totalChars = filteredPosts.map(post => post.title.length + post.selftext.length).sum
    val avgChars = if (filteredPosts.nonEmpty) totalChars / filteredPosts.length else 0
*/

    // Inciso (c) para imprimir las estadísticas
    // Prepare statistics
    val stats: Map[String, Int] = Map(
      "feedsSuccess" -> feedsSuccess.toInt,
      "feedsFailed" -> feedsFailed.toInt,
      "postsSuccess" -> postsSuccess.toInt,
      "postsFailed" -> postsFailed.toInt,
      "postsFiltered" -> postsFiltered.toInt,
      "avgChars" -> avgChars
    )

    // Print output
    println(Formatters.formatProcessingStats(stats))
    println()

    // Inciso (d)
    // Check if we have any posts to process
    if (filteredPosts.isEmpty) {
      println("===================== ERROR =====================")
      println("Error: No valid posts downloaded after filtering")
      println("=================================================")
      // Detenemos la ejecución de la aplicación (para evitar warning en la consola)
      spark.stop()
      return
    }

    // Load dictionaries
    val dictionary = Dictionary.loadAll(cmdArgs.entitiesDir)

    // Detect entities in all posts (combine title and selftext)
    val allEntities = filteredPosts.flatMap { post =>
      val combinedText = post.title + " " + post.selftext
      Analyzer.detectEntities(combinedText, dictionary)
    }

    // Count entities
    val entityCounts = Analyzer.countEntities(allEntities)
    val typeStats = Analyzer.countByType(allEntities)

    println(Formatters.formatTypeStats(typeStats))
    println()
    println(Formatters.formatEntityStats(entityCounts, cmdArgs.topK))
  }
}
