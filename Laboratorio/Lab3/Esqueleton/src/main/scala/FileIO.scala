import scala.io.Source
import scala.util.control.NonFatal
import org.json4s._
import org.json4s.jackson.JsonMethods._

object FileIO {

  /**
   * Read subscriptions from JSON file.
   * @param filePath path to subscriptions file
   * @return list of options: Some(Subscription) for valid entries, None for malformed entries
   *         returns empty list if file not found
   */
  def readSubscriptions(filePath: String): List[Option[Subscription]] = {
    // Caso de error
    /*
      Si el archivo no existe
    */
    val file = new java.io.File(filePath)
    if (!file.exists()) {
      println(s"Error: Could not load $filePath - file not found")
      return Nil
    }

    implicit val formats: Formats = DefaultFormats
    val source = Source.fromFile(filePath)
    val content = source.mkString
    source.close()

    try {
      val json = parse(content)
      val subscriptions = json.extract[List[Map[String, String]]]

      subscriptions.map { sub =>
        (sub.get("name"), sub.get("url")) match {
          case (Some(name), Some(url)) => Some(Subscription(name, url))
          case _ =>
            println("Warning: Skipping malformed subscription (missing 'name' or 'url' field)")
            None
        }
      }
    } catch {
      case NonFatal(_) =>
        println(s"Error: Could not load $filePath - invalid JSON format")
        Nil
    }
  }

  /**
    * Download feed JSON from URL.
    * @param url Reddit feed URL
    * @return Option containing JSON as String, None on network error or timeout
    */
  def downloadFeed(url: String): Option[String] = {
    val source = Source.fromURL(url)
    val content = source.mkString
    source.close()
    Some(content)
  }

  /**
   * Read dictionary file line by line.
   * @param filePath path to dictionary file
   * @return Option containing list of entities, None if file missing
   */
  def readDictionaryFile(filePath: String): Option[List[String]] = {
    val source = Source.fromFile(filePath)
    val lines = source.getLines()
      .map(_.trim)
      .filter(_.nonEmpty)
      .filterNot(_.startsWith("#"))
      .toList
    source.close()
    Some(lines)
  }
}
