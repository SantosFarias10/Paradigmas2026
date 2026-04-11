import org.json4s._
import org.json4s.jackson.JsonMethods._
import scala.io.Source
import scala.util.{Try, Using}


object Main {
  // Define `Subscription` as a simple tuple type alias
  // Agregamos los campos de count y before
  type Subscription = (String, String, Int, String) // (name, url, count, before)

  // Pure function to read subscriptions from a JSON file
  def readSubscriptions(path: String): Option[List[Subscription]] = {
    // Utilizamos 'Using' para garantizar que el archivo se cierre de forma segura
    // Incluso si ocurre una excepcion, evitando "resource leaks"
    Using(Source.fromFile(path)) { source =>
      val jsonString = source.mkString
      implicit val formats: Formats = DefaultFormats

      val json = parse(jsonString)
      
      // El metodo flatMap nos permite descartar automáticamente aquellas suscripciones
      // Que devuelvan None
      val subscriptions = json.children.flatMap { child =>
        // Try{...}.toOption maneja de forma "pura" el error si una suscripción no puede
        // Ser parseada por tener campos faltantes (o sea Lear Python)
        Try {
          val name = (child \ "name").extract[String]
          val before = (child \ "before").extract[String]
          val count = (child \ "count").extract[Int]
          // Construimos la URL asociada a cada Subreddit con la expresión pedida
          val url = (child \ "url").extract[String].concat(s"?count=$count&before=$before")
          (name, url, count, before)
        }.toOption
      }
      // Retornamos subscriptions
      subscriptions
    }.toOption // Si ocurre un error mayor (ej: archivo no existe o JSON inválido), devuelve None de forma pura
  }

  def readPosts(url: String): List[String] = {
    val source = Source.fromURL(url)
    val jsonContent = source.mkString
    implicit val formats: Formats = DefaultFormats

    val json = parse(jsonContent)
    val children = (json \ "data" \ "children").extract[List[JValue]]

    children.map { child =>
      val data = child \ "data"
      val title = (data \ "title").extract[String]
      title
    }
  }

  // FUNCIONES AUXILIARES PARA IMPRIMIR UN POST Y UN SUBSCRIPTION
  // PUEDEN SER MODIFICADAS PARA AGREGAR OPTION Y COMPLETAR EL EJERCICIO 3
  // def printPost(post: Post): Unit = {
  //   val content = post._2
  //   val truncatedContent = if (content.length > 80) content.take(80) else content
  //   println(s"${post._1} by **${post._3}**")
  //   println(truncatedContent)
  //   println("-----------------------")
  // }

  // def printSubscription(allPosts: (String, List[Post])): Unit = {
  //   val url = posts._1
  //   println(s"Posts from: $url")
  //   posts._2.map(printPost)
  // }

  // Main function to run
  def main(args: Array[String]): Unit = {
    val header = s"Reddit Post Parser\n${"=" * 40}"

    println("=======================")
    println("EJ1: LEER SUSCRIPCIONES")
    val subscriptions: List[Subscription] = readSubscriptions("subscriptions.json").toList.flatten

    // Print subscriptions read - We can use imperative for I/O
    for (subscription <- subscriptions) {
      println(subscription)
    }

    println("=======================")
    println("")
    println("=======================")
    println("EJ2: DESCARGAR POSTS")

    // Descargar y parsear los posts
    var allPosts: List[String] = List.empty
    for (subscription <- subscriptions) {
      var postTitles = readPosts(subscription._2)
      allPosts = allPosts ++ postTitles
    }

    println("=======================")
    println("")
    println("=======================")
    println("EJ3: IMPRIMIR POSTS Y CONTEO DE PALABRAS CENSURADAS")

    // Print final results
    //allPosts.map(printSubscription)
    println("=======================")
  }
}
