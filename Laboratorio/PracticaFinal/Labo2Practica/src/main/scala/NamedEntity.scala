// =====================================================================
// Ejercicio 1: Modelar la jerarquía de entidades [x]
// =====================================================================

/**
 * Clase base abstracta para todas las entidades nombradas.
 *
 * Una entidad nombrada es una expresión del texto que refiere a un objeto
 * del mundo real (persona, lugar, organización, tecnología, etc.).
 *
 * @param text el texto tal como aparece en el corpus
 */
abstract class NamedEntity(val text: String) {

  // Metodos

  /**
   * Retorna el tipo de la entidad como String.
   * Ejemplo: "Person", "University", "ProgrammingLanguage"
   */
  def entityType: String

  /**
   * Retorna una línea de descripción de la entidad para el informe.
   *
   * Al usar entityType aquí, este método funciona correctamente para cualquier
   * subclase sin necesidad de redefinirlo. Esto es polimorfismo.
   */
  def describe: String = s"[$entityType] $text"
}

// =====================================================================.
//
// Jerarquía de Entidades:
//
//   NamedEntity
//   ├── Person
//   ├── Organization
//   │   └── University
//   ├── Place
//   └── Technology
//       └── ProgrammingLanguage
//
// =====================================================================

// Subclases Concretas

class Person(text: String) extends NamedEntity(text) {
  override def entityType: String = "Person"
}

class Organization(text: String) extends NamedEntity(text){
  override def entityType: String = "Organization"
}

class University(text: String) extends Organization(text){
  override def entityType: String = "University"
}

class Place(text: String) extends NamedEntity(text){
  override def entityType: String = "Place"
}

class Technology(text: String) extends NamedEntity(text){
  override def entityType: String = "Technology"
}

class ProgrammingLanguage(text: String) extends Technology(text){
  override def entityType: String = "ProgrammingLanguage"
}

// Ejemplo
object PruebaNamedEntity {
  def main(args: Array[String]): Unit = {
    val entities: List[NamedEntity] = List(
      new Person("Alan Turing"),
      new University("MIT"),
      new ProgrammingLanguage("Scala"),
      new Place("San Francisco")
    )
    entities.foreach(e => println(e.describe))
  }
}

// ¿Por qué describe funciona correctamente para todos los tipos sin necesidad de redefinirlo en cada subclase?
/*

El método describe utiliza la variable entityType, que es una variable de instancia de la clase base NamedEntity.
Cuando se llama a describe en una instancia de una subclase, la variable entityType se refiere al tipo de la subclase,
y por lo tanto, describe funciona correctamente para todos los tipos sin necesidad de redefinirlo en cada subclase.

*/