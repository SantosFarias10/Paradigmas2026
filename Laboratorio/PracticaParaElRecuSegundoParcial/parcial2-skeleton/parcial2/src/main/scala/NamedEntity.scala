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
}

class Person(text: String) extends NamedEntity(text) {
  def entityType: String = "Person"
}

class Organization(text: String) extends NamedEntity(text) {
  def entityType: String = "Organization"
}

class University(text: String) extends Organization(text) {
  override def entityType: String = "University"
}

class Person(text: String) extends NamedEntity(text) {
  def entityType: String = "Person"
  // Sobreescribimos matches 
  // contains busca si el texto está contenido en el texto original
  override def matches(text: String): Boolean = text.contains(this.text)
}

class Technology(text: String) extends NamedEntity(text) {
  def entityType: String = "Technology"
  // Sobreescribimos matches
  // case-sensitive: sin (?i), igual de resto
  override def matches(input: String): Boolean = {
    val pattern: String = s"(?<![\\w])${quote(this.text)}(?![\\w])"
    pattern.r.findFirstIn(input).isDefined
  }
}
class ProgrammingLanguage(text: String) extends Technology(text) {
  override def entityType: String = "ProgrammingLanguage"
}
