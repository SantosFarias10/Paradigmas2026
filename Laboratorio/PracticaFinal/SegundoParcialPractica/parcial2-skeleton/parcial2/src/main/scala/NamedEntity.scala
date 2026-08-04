import scala.util.matching.Regex
import java.util.regex.Pattern

/**
 * Clase base abstracta para todas las entidades nombradas.
 *
 * Una entidad nombrada es una expresión del texto que refiere a un objeto
 * del mundo real (persona, lugar, organización, tecnología, etc.).
 *
 * @param text el texto tal como aparece en el corpus
 */
abstract class NamedEntity(val text: String) {

  /**
   * Retorna el tipo de la entidad como String.
   */
  def entityType: String

  /**
   * Retorna una línea de descripción de la entidad para el informe.
   */
  def describe: String = s"[$entityType] $text"

  def matches(text: String): Boolean = {
    // convertimos el text en una expresion regular "segura"
    // this.text es el nombre de la entidad
    val textRegex: String = Pattern.quote(this.text)
    
    // (?i) case-insensitive, asi se puede matchear "Scala" -> "scala"
    // (?<![a-zA-Z0-9]) matchea con el espacio antes de la palabra
    // Ver si cambiando (?<![a-zA-Z0-9]) por \\b sigue dando lo mismo
    val regex: Regex = s"(?i)(?<![a-zA-Z0-9])${textRegex}(?![a-zA-Z0-9])".r

    val found: Boolean = regex.findFirstIn(text).isDefined
    
    return found
  }

  def isRelevant: Boolean = true
}

class Person(text: String) extends NamedEntity(text) {
  def entityType: String = "Person"

  // case-sensitive: "Martin Odersky" != "martin odersky"
  // nombre completo: busca el texto exacto (ej: "Martin Odersky") como secuencia
  override def matches(text: String): Boolean = {
    val textRegex: String = Pattern.quote(this.text)
    val regex: Regex = s"(?<![a-zA-Z0-9])${textRegex}(?![a-zA-Z0-9])".r

    val found: Boolean = regex.findFirstIn(text).isDefined

    return found
  }
}

class Organization(text: String) extends NamedEntity(text) {
  def entityType: String = "Organization"

  override def isRelevant: Boolean = false
}

class University(text: String) extends Organization(text) {
  override def entityType: String = "University"

  override def isRelevant: Boolean = true
}

class Place(text: String) extends NamedEntity(text) {
  def entityType: String = "Place"

  override def isRelevant: Boolean = false
}

class Technology(text: String) extends NamedEntity(text) {
  def entityType: String = "Technology"

  override def matches(text: String): Boolean = {
    val textRegex: String = Pattern.quote(this.text)

    // Eliminamos (?i) para el case-sensitive
    val regex: Regex = s"(?<![a-zA-Z0-9])${textRegex}(?![a-zA-Z0-9])".r
    
    val found: Boolean = regex.findFirstIn(text).isDefined

    return found
  }

  override def isRelevant: Boolean = false
}

class ProgrammingLanguage(text: String) extends Technology(text) {
  override def entityType: String = "ProgrammingLanguage"

  override def isRelevant: Boolean = true
}

abstract class Event(text: String) extends NamedEntity(text) {

}

class Conference(text: String) extends Event(text) {
  def entityType: String = "Conference"
}