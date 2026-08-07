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

  // Método para verificar si en content existe una tiene una entity
  def appearsIn(content: String): Boolean

  // Retorna el nombre de las clases en plural dependiendo si es 1 o mayor.
  def pluralLabel(count: Int): String

}

class Person(text: String) extends NamedEntity(text) {
  def entityType: String = "Person"
/*
  override def pluralLabel(count: Int): String = {
    val r1: String = s"[$count]" + "persona"
    val r2: String = s"[$count]" + "personas"

    if (count = 1) {
      return r1
    } else {
      return r2
    }
  }
  */
}

class Organization(text: String) extends NamedEntity(text) {
  def entityType: String = "Organization"
/*
  override def pluralLabel(count: Int): String = {
    val r1: String = s"[$count]" + "organización"
    val r2: String = s"[$count]" + "organizaciones"

    if (count = 1) {
      return r1
    } else {
      return r2
    }
  }
  */
}

class University(text: String) extends Organization(text) {
  override def entityType: String = "University"
/*
  override def pluralLabel(count: Int): String = {
    val r1: String = s"[$count]" + "universidad"
    val r2: String = s"[$count]" + "universidades"

    if (count = 1) {
      return r1
    } else {
      return r2
    }
  }
  */
}

class Place(text: String) extends NamedEntity(text) {
  def entityType: String = "Place"
/*
  override def pluralLabel(count: Int): String = {
    val r1: String = s"[$count]" + "lugar"
    val r2: String = s"[$count]" + "lugares"

    if (count = 1) {
      return r1
    } else {
      return r2
    }
  }
  */
}

class Technology(text: String) extends NamedEntity(text) {
  def entityType: String = "Technology"
/*
  override def pluralLabel(count: Int): String = {
    val r1: String = s"[$count]" + "tecnología"
    val r2: String = s"[$count]" + "tecnologías"

    if (count = 1) {
      return r1
    } else {
      return r2
    }
  }
  */
}

class ProgrammingLanguage(text: String) extends Technology(text) {
  override def entityType: String = "ProgrammingLanguage"

  override def appearsIn(content: String): Boolean = {
    // Convertimos el texto en un texto "seguro"
    val textRegex: String = Pattern.quote(this.content)

    // case-insensitive
    val regex: Regex = s"(?i)(?<![a-zA-Z0-9])${textRegex}(?![a-zA-Z0-9])".r

    val found: Boolean = regex.findFirstIn(content).isDefined

    return found
  }
/*
  override def pluralLabel(count: Int): String = {
    val r1: String = s"[$count]" + "lenguaje de programación"
    val r2: String = s"[$count]" + "lenguajes de programación"

    if (count = 1) {
      return r1
    } else {
      return r2
    }
  }
  */
}
