// Cambiamos el nombre de Formatters a Formart
import org.json4s._
import org.json4s.jackson.JsonMethods._
import RedditTypes._
import scala.util.Try
import scala.io.Source

object Format {
  implicit val formats: Formats = DefaultFormats

  // Ejercicio 2
  // Post: (SubredditName, postTitle, postText, postedDate)
  def subredditPosts(url: String, posts: String): Option[List[Post]] = {
    Try {
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
      // Ejercicio 6
      // Usamos el metodo de getOrElse ya que extractOpt nos devuelve un Option[Int] (un Some(score)).
      // Para el caso de None se devolvera 0.
      val score            = (data \ "score").extractOpt[Int].getOrElse(0)
      val postUrl          = (data \ "url").extractOpt[String].getOrElse("")
    
      (subreddit, title, selftext, publication_date, score, postUrl)
      }.filter { case (_, title, selftext, _, _, _) =>
        // Ejercicio 3
        // hay que eliminar los post que no tengan texto, o sea que tengan el selftext sea vacio
        // los posts que solo tengan espacios y por ultimo los que no tengan titulo.
        title.trim.nonEmpty && selftext.trim.nonEmpty
        // El metodo de trim quita todos los caracteres de espacio en blanco al inicio y al final
        // Luego de quitar el primer y ultimo caracter, vemos si queda algun caracter en el titulo y en el contenido 
      }
      formattedPosts
    }.toOption
  }


  // Ejercicio 5
  // Esta funcion recibe una lista de Post (subreddit, title, selftext, postedDate) y devuelve
  // Una lista de pares Subreddit y List[(palabra, contador)], esta funcion solo cuenta las palabras
  // Que empiezan con mayuscula, que NO este en StopWords.txt, y que aparecen mas de una vez en el
  // Titulo y el cuerpo de los posts del subreddit.
  def wordFrecuency(posts: List[Post]): List[(String, List[(String, Int)])]= {
    val source = Source.fromFile("./StopWords.txt")
    // getLines recorre el archivo linea a linea (separado por fin de linea). Cada elemento es una cadena
    // con el contenido de una linea, sin el caraccter de salto de linea final.
    // toSet convierte esa secuencia de lienas en un Set[String] inmutables. Elimina los duplicados.
    // Basicamente lee cada linea del archivo como texto y las guardamos en un Set para luego poder preguntar
    // Rapido si una palabra esta en la lista de stopwords.
    val stopwords = source.getLines().toSet
    source.close()

    // posts = [(Subreddit, postTitle, postText, postedDate)]
    posts
      // Recorremos la lista y agrupamos los posts que comparten la misma clave. Aca la clave es solo el nombre
      // Del subreddit, el resultado es un Map[String, List[Post], a cada subreddit le corresponde la lista de
      // Todos sus posts.
      .groupBy { case (subreddit, _, _, _, _, _) => subreddit}
      // Aca se itere ese Map. En scala, al hacer .map sobre un Map, cada elemento es un par (clave, valor),
      // O sea (Subreddit, postList), postList es la List[Post] de ese subreddit (lo que grupBy junto).
      .map { case (subreddit, postList) =>
        // por cada post tomamos solo el title y selftext.
        val words = postList.flatMap { case (_, title, selftext, _, _, _) => 
          // Los unimos en un solo String. 
          // El metodo split parte el texto en "palabras" usando cualquier
          // Secuencia de numeros como separador (\\W+ = no "word character"). 
          // toList y el flatMap hacen que todas las palabras de todos los posts del subreddit queden en una
          // Sola List[String]
          (title + " " + selftext).split("\\W+").toList
        }
        // 
        val wordcount = words
          // Nos quedamos solo tokens que no esten vacios, empiecen con mayus y en minuscula NO estan en stopword
          .filter(word => word.nonEmpty && word.head.isUpper && !stopwords.contains(word.toLowerCase))
          // Agrupamos las ocurrencias iguales. Queda un Map[String, List[String]] donde cada clave es la palabra
          // Y el valor es la lista de todas sus apariciones.
          .groupBy(word => word)
          // Convertimos cada grupo en el par (palabra, cantidadd)
          .map { case (word, count) => (word, count.length)}
          // El Map pasa a lista de pares (el orden del Mao no lo garantiza como regla general)
          .toList
          // Ordenamos por frecuencia (de menor a mayot cantidad)
          .sortBy { case  (_, count) => count}
          // Dejamos solo las palabras que aparencen mas de una vez en el conjunto filtrado de ese subbreddit
          .filter { case (_, count) => count>1}
        (subreddit, wordcount)
      }
      // Convertimos el Map que salio del groupBy+map exterior en una List[(String, List[(String, Int)])]
      //  para el tipo de retorno de wordFrecuency.
      .toList
  }
}