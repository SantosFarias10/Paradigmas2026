import RedditTypes._ // Subreddit = String; Url = String; etc

object Main {
  def main(args: Array[String]): Unit = {
    val path = "subscriptions.json" // Path relativo al directorio donde se compila
    val header = s"Reddit Post Parser\n${"=" * 40}"

    // Como readSubscriptions nos devuelve Some[List[Subscription]] o None,
    // se usa ".toList" para transformar el Option en una lista y sacarlo de encima.
    // O sea, agarra por ejemplo el Some o None devuelto por ".readSubscriptions()"
    // y lo mete en una lista, por lo que vamos a terminar teniendo
    // List[List[Subscription]] o List[] si previamente era None.
    // Luego ".flatten" agarra la lista de listas y las une en una sola,
    // por lo que vamos a terminar teniendo List[Subscription] o List[] si era None.
    val subreddits: List[(Subreddit, Url)] = FileIO.readSubscriptions(path).toList.flatten
    //println(s"\nLista obtenida de tuplas de la forma (subredditName, url): $subreddits\n")

    // Por si quieren cortar la ejecución acá para ver este print, pueden descomentar esto
    //System.exit(0)

    // Option se comporta como si fuera una mini-lista vacía o con un elemento, o sea
    // Some(Valor) es como una lista de un elemento: [Valor]
    // y None es como una lista vacia: []
    // ".flatMap" lo que hace es agarrar todas las "mini-listas", desempaquetarlas
    // y unirlas en una sola lista final.
    // Por lo tanto, si el subreddit se descargo bien, o sea devolvio Some(tupla)
    // el ".flatMap" abre el Some, extrae la tupla y la mete en la lista final allPosts.
    val allPosts: List[(Url, SubredditContent)] = FileIO.fetchAllPosts(subreddits)
    // El output de esto es el mismo que se ve en los links de subscriptions.json
    // Se ve algo como List[("https://www.reddit.com/r/scala/.json?count=10", {kind: "Listing", ...}), ("https...", {kind:...", ...})]
    // El contenido de cada subreddit es larguísimo, por lo que con un print no se va a entender.
    // Si prefieren verlo, entren a cualquiera de los links subscriptions.json, que es más entendible.

    // Ejercicio 2: Formatear posts (Quedarse solo con las partes "importantes")
    val formattedPosts: List[(Subreddit, postTitle, postText, postedDate)]
      = allPosts.flatMap { case (url, posts) =>
        // Usamos flatmap porque nos quedan List[List[(String...)]
        // flatMap combina map con flatten; flatten convierte una coleccion de colecciones
        // List[List[A]] en List[A].

        Format.subredditPosts(url, posts).toList.flatten
      }

    val frecuencyRes: List[(Subreddit, List[(String, Int)])]= // List[(subreddit, List[(words, count)])]
      Format.wordFrecuency(formattedPosts)

    // para poder ver el output mas facil
    val output = frecuencyRes
      .map { case (subreddit, wordcount) => // wordcount = (word, count)
        val sreddit = s"subredditName:$subreddit"
        val words = wordcount.map { case (word, count) =>
          s"$word - $count"
        }.mkString("\n")
        s"$sreddit\n$words"
      }.mkString("\n\n")

    println(output)

    // para poder leer mejor el output
    // val output = formattedPosts
    //     .map { case (subreddit, title, selftext, created_utc) =>
    //         s"subreddit:($subreddit) $title\nselftext: $selftext\ntime: $created_utc"
    //     }
    //     .mkString("\n\n\nPOSTT\n")
    // println(output)
  }
}