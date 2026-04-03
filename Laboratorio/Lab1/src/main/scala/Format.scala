// Cambiamos el nombre de Formatters a Formart
import org.json4s._
import org.json4s.jackson.JsonMethods._

object Format {
  implicit val formats: Formats = DefaultFormats

  type Post = (String, String, String, String) // (subreddit, title, selftext, Date)

  // Ejercicio 2
  // Esta funcion  
  def subredditPosts(url: String, posts: String): List[Post] = {
    val urlContent = parse(posts)

    // Accedemos a urlJson.data.children y mapeamos
    val formattedPosts = ( urlContent \ "data" \ "children").children.map { post =>
      // dado que cada children tiene un campo "data" => lo skipeamos
      val data = post \ "data"

      val subreddit        = (data \ "subreddit").extract[String]
      val title            = (data \ "title").extract[String]
      val selftext         = (data \ "selftext").extract[String]
      val created_utc      = (data \ "created_utc").extract[Double].toLong
      val publication_date = TextProcessing.formatDateFromUTC(created_utc)
    
      (subreddit, title, selftext, publication_date)
    }.filter { case (_, title, selftext, _) =>
      // Ejercicio 3
      // hay que eliminar los post que no tengan texto, o sea que tengan el selftext sea vacio
      // los posts que solo tengan espacios y por ultimo los que no tengan titulo.
      title.trim.nonEmpty && selftext.trim.nonEmpty
      // El metodo de trim quita todos los caracteres de espacio en blanco al inicio y al final
      // Luego de quitar el primer y ultimo caracter, vemos si queda algun caracter en el titulo y en el contenido 
    }
    formattedPosts
  }
}
