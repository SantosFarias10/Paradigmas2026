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

  /**
   * Retorna los aliases de la entidad.
   */
  def aliases: List[String] = List.empty

  /**
   * Retorna una lista con el nombre base y los aliases.
   */
  def allNames: List[String] = text :: aliases

  def relevanceScore: Int = 5
}

class Person(text: String) extends NamedEntity(text) {
  def entityType: String = "Person"

  override def relevanceScore: Int = 8
}

class Organization(text: String) extends NamedEntity(text) {
  def entityType: String = "Organization"

  override def relevanceScore: Int = 3
}

class University(text: String) extends Organization(text) {
  override def entityType: String = "University"

  override def relevanceScore: Int = 6
}

class Place(text: String) extends NamedEntity(text) {
  def entityType: String = "Place"

  override def relevanceScore: Int = 2
}

class Technology(text: String) extends NamedEntity(text) {
  def entityType: String = "Technology"

  override def aliases: List[String] = {
    val lines: List[String] = FileIO.readLines("data/aliases.txt")
      // Buscamos la linea que empiece con el nombre de la entidad
      // find retorna un Option[String], osea que puede ser Some(line) o None
      .find(line => line.startsWith(text + "="))
      // Si es Some => separamos por el simbolo "="
      // Con (1) tomamos el primer indice
      // Luego con el split(",") divicimos por comas
      // Por ultimo convertimos en una lista
      .map(line => line.split("=", 2)(1).split(",").toList)
      // Si es None => retorna una lista vacia
      .getOrElse(List.empty)

    return lines
  }

  override def relevanceScore: Int = 4
}

class ProgrammingLanguage(text: String) extends Technology(text) {
  override def entityType: String = "ProgrammingLanguage"

  override def relevanceScore: Int = 9
}
