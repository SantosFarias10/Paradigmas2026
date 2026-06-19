import java.util.regex.Pattern.quote
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

  // Ej 1.a
  // (?i) = Case-insensitive, o sea que no distingue mayúsculas de minúsculas
  // (?<![\\w]) = lo que está antes no es letra/digito, o sea no es palabra.
  // Pattern.quote(...) = escapa caracteres especiales como + en C++
  def matches(text: String): Boolean = {
    //creamos el patrón 
    val pattern: String = s"(?i)(?<![\\w])${quote(this.text)}(?![\\w])"
    // .r lo convierte en regex
    // findFirstIn busca la primera aparicion del regex en el texto
    // isDefined convierte el Option en Boolean
    //lo convierte a regex y busca si está en el texto
    pattern.r.findFirstIn(text).isDefined
  }

  // Ej 2.a
  def isRelevant: Boolean = true
}

class Place(text: String) extends NamedEntity(text) {
  def entityType: String = "Place"

  // Ej 2.a
  override def isRelevant: Boolean = false
}

class Organization(text: String) extends NamedEntity(text) {
  def entityType: String = "Organization"

  // Ej 2.a
  override def isRelevant: Boolean = false
}

class University(text: String) extends Organization(text) {
  override def entityType: String = "University"

  // Ej 2.a
  override def isRelevant: Boolean = true
}

class Person(text: String) extends NamedEntity(text) {
  def entityType: String = "Person"
  // Ej 1.c
  // Sobreescribimos matches 
  // contains busca si el texto está contenido en el texto original
  override def matches(text: String): Boolean = text.contains(this.text)

  // Ej 2.a
  override def isRelevant: Boolean = true
}

class Technology(text: String) extends NamedEntity(text) {
  def entityType: String = "Technology"
  // Ej 1.b
  // Sobreescribimos matches
  // case-sensitive: sin (?i), igual de resto
  override def matches(input: String): Boolean = {
    val pattern: String = s"(?<![\\w])${quote(this.text)}(?![\\w])"
    pattern.r.findFirstIn(input).isDefined
  }

  // Ej 2.a
  override def isRelevant: Boolean = false
}
class ProgrammingLanguage(text: String) extends Technology(text) {
  override def entityType: String = "ProgrammingLanguage"

  // Ej 2.a
  override def isRelevant: Boolean = true
}

// Ej b
abstract class Event(text: String) extends NamedEntity(text)

class Conference(text: String) extends Event(text) {
  def entityType: String = "Conference"
}
