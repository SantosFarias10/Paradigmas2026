// =====================================================================
// Ejercicios 3 y 5: Detección y conteo de entidades
// =====================================================================

import scala.util.matching.Regex
import java.util.regex.Pattern

/**
 * Responsable de detectar entidades nombradas en texto libre y
 * producir estadísticas sobre ellas.
 */
object Analyzer {

  /**
   * Detecta las entidades del diccionario que aparecen en el texto dado.
   *
   * @param text       texto a analizar (ej: título o cuerpo de un post)
   * @param dictionary lista de entidades conocidas (cargadas desde los diccionarios)
   * @return lista de entidades cuyo texto aparece en el texto analizado
   *
   *   Ejemplo:
   *     text       = "Scala fue creado en EPFL por Martin Odersky"
   *     dictionary = List(
   *                    ProgrammingLanguage("Scala"),
   *                    University("EPFL"),
   *                    Person("Martin Odersky"),
   *                    Person("Ada Lovelace")   ← no aparece en el texto
   *                  )
   *     resultado  = List(
   *                    ProgrammingLanguage("Scala"),
   *                    University("EPFL"),
   *                    Person("Martin Odersky")
   *                  )
   */
  def detectEntities(text: String, dictionary: List[NamedEntity]): List[NamedEntity] = {
    // Filtramos el dictionary
    val listFiltered: List[NamedEntity] = dictionary.filter { entity =>
      // Creamos un string "seguro" para usar en una expresion regular (Regex)
      // Por ejemplo, Pattern.quote("C++") -> trata el + como texto literal
      val textRegex: String = Pattern.quote(entity.text)
      // entity.describe retorna "[ProgrammingLanguage] Scala"
      // En cambio entity.text retorna solamente "Scala" es por eso que usamos entity.text

      // Construimos un Regex que busca la entitda como palabras completas
      // \b = limite de palabra, o sea inicio/fin
      // .r = convierte el string en un objeto Regex de scala
      val regex: Regex = s"\\b${textRegex}\\b".r
      // Ejemplo con entity.text = "Java", el regex = \bJava\b
      // "aprendi Java hoy" Matchea con Java, mientras que "Aprendi Javascript" no va a matchear.

      // findFirstIn(text) busca la primera aparicion de la Regex en text. Retorna un Option[String]: Some("Scala") si la encontro o None si no
      // isDefined convierte eso en Boolean, true -> si la encontro, false -> si no la encontro.
      val entityFound: Boolean = regex.findFirstIn(text).isDefined

      entityFound
    }

    return listFiltered
  }

  /**
   * Cuenta cuántas entidades de cada tipo fueron detectadas.
   *
   * @param entities lista de entidades detectadas
   * @return mapa de entityType → cantidad de apariciones
   *
   *   Ejemplo:
   *     entities = List(
   *                  Person("Alan Turing"),
   *                  ProgrammingLanguage("Scala"),
   *                  Person("Ada Lovelace"),
   *                  University("MIT")
   *                )
   *     resultado = Map(
   *                   "Person"              -> 2,
   *                   "ProgrammingLanguage" -> 1,
   *                   "University"          -> 1
   *                 )
   */
  def countByType(entities: List[NamedEntity]): Map[String, Int] = {
    // Para cada entities encontrada
    val result: Map[String, Int] = entities
      // groupBy retorna Map[Strin, List[NamedEntity]] 
      // O sea, Map[Tipo de entity, Lista de entities del tipo]
      // Por ejemplo:
      // Map("Person" -> List(Person("Alan Turing"), Person("Ada Lovelace")), "ProgrammingLanguage" -> List(ProgrammingLanguage("Scala")), "University" -> List(University("MIT")))
      .groupBy(entity => entity.entityType)
      .map {
        case (entityType, entityList) => (entityType, entityList.size)
      }
    
    return result
  }
}

object PruebaAnalyzerDetectedEntity {
  def main(args: Array[String]): Unit = {
    val text = "Scala fue creado en EPFL por Martin Odersky"
    val dict = Dictionary.loadAll()
    val found = Analyzer.detectEntities(text, dict)
    
    found.foreach(e => println(e.describe))
  }
}

object PruebaAnalyzerCountByType {
  def main(args: Array[String]): Unit = {
    val entities = List(
      new Person("Alan Turing"),
      new ProgrammingLanguage("Scala"),
      new Person("Ada Lovelace"),
      new University("MIT")
    )
    val result = Analyzer.countByType(entities)
    println(result)
  }
}