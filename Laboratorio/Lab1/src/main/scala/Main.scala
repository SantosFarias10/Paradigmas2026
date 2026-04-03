object Main {
  def main(args: Array[String]): Unit = {

    val path = "subscriptions.json" // path relativo al directorio donde se compila
    val header = s"Reddit Post Parser\n${"=" * 40}"

    val subscriptions: List[(String, String)] = FileIO.readSubscriptions(path)

    val allPosts: List[(String, String)] = subscriptions.map { case (name, url) =>
      println(s"Fetching posts from: $url")
      val posts = FileIO.downloadFeed(url)
      (url, posts)
    }

    // Ejercicio 2
    val formattedPosts: List[(String, String, String, String)] = allPosts.flatMap { case (url, posts) =>
      // Usamos flatMap ya que nos quedan una Lista[List[(String...)]]
      // flatMap combina .map con .flatten; flatten convierte una coleccion de colecciones
      // osea List[List[A]] en List[A]

      Format.subredditPosts(url, posts)
    }

    val output = allPosts
      .map { case (url, posts) => 
        Format.subredditPosts(url, posts) 
      }
      .mkString("\n")

    println(output)
  }
}
