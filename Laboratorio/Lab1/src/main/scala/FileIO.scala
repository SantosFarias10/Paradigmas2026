import scala.io.Source
import org.json4s._
import org.json4s.jackson.JsonMethods._

object FileIO {

  type Subscription = (String, String)  //(subredditName, url)

  // implicit le dice al compilador si en algun lugar se necesita un Formats
  // y no lo pasamos explicitamente, entonces se usa formats automaticamente.
  // Formats es un paquete de configuracion que incluye reglas para convertir
  // JSON a objetos, como convertir fechas, numeros, etc.
  implicit val formats: Formats = DefaultFormats

  // Ejercicio 1
  // Pure function to read subscriptions from a JSON file
  def readSubscriptions(path: String): List[Subscription] = {
    // Abrimos y leemos el archivo
    val source = Source.fromFile(path)

    // mkString lee todo el archivo y lo devuelve como un String.
    // parse viene de la libreria json4s y toma ese String y lo convierte a un
    // JSON Value, que es un tipo que representa el JSON en Scala.
    val json = parse(source.mkString)

    // children es un metodo que devuelve una List[JValue] con los "elementos hijos".
    // Luego map recorrere esa lista y aplica una funcion a cada elemento.
    // item es "cada hijo", o sea cada JValue, del json.children.
    // En json4s, \ es un operador que se usa para acceder a un campo de un JSON,
    // por lo que si item es un objeto JSON, entonces (item \ "name") te devuelve
    // el JValue del campo "name".
    // extract convierte el JValue a un String. Este es la llamda que usa 
    // el implicit val formats ... para saber como hacer la conversion.
    val result = json.children.map { item =>
      val name = (item \ "name").extract[String]
      val url = (item \ "url").extract[String]
      (name, url)
    }

    // cerramos el archivo para que no se generen resource leaks.
    source.close()

    result
  }

  // Pure function to download JSON feed from a URL
  def downloadFeed(url: String): String = {
    val source = Source.fromURL(url)
    source.mkString
  }
}
