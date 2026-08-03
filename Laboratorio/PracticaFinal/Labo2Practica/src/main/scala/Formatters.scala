// =====================================================================
// Ejercicios 4 y 5: Formateo de resultados
// =====================================================================

/**
 * Responsable de convertir los resultados del análisis a texto para mostrar.
 */
object Formatters {

  /**
   * Formatea el análisis NER de un post individual.
   *
   * @param postTitle título del post analizado
   * @param entities  entidades detectadas en ese post
   * @return bloque de texto con el título y las entidades encontradas
   *
   *   Ejemplo de salida esperada:
   *
   *     Post: "Scala 3 released at EPFL by Martin Odersky"
   *     Entidades detectadas:
   *       [ProgrammingLanguage] Scala
   *       [University] EPFL
   *       [Person] Martin Odersky
   *
   *   Si no se detectaron entidades, mostrar un mensaje indicándolo.
   */
  def formatNERResult(postTitle: String, entities: List[NamedEntity]): String = {
    if (entities.isEmpty) {
      return "Post: " + postTitle + "\n" + "No se detectaron entidades." + "\n"
    } else {
      return "Post: " + postTitle + "\n" + "Entidades detectadas:\n" + entities.map(entity => "    " + entity.describe).mkString("\n") + "\n"
    }
  }

  /**
   * Formatea un resumen de estadísticas de entidades por tipo.
   *
   * @param counts mapa de entityType → cantidad
   * @return texto con las estadísticas ordenadas por cantidad (de mayor a menor)
   *
   *   Ejemplo de salida esperada:
   *
   *     === Estadísticas de entidades ===
   *     Person: 5
   *     ProgrammingLanguage: 3
   *     Organization: 2
   *     University: 2
   */
  def formatEntityStats(counts: Map[String, Int]): String = {
    return "=== Estadísticas de entidades ===\n" + counts.map(c =>
      // c es una tupla de dos elementos, o sea (clave, valor)
      // para poder acceder a las componentes de una tupla se usa ._1 y ._2
      // => ("Person", 5) tupla._1 -> "Person" y tupla._2 -> 5
      "    " + c._1 + ": " + c._2
    ).mkString("\n") + "\n"
  }
}

object PruebaFormatNERResult {
  def main(args: Array[String]): Unit = {
    val entities = List(
      new ProgrammingLanguage("Scala"),
      new University("EPFL"),
      new Person("Martin Odersky")
    )
    println(Formatters.formatNERResult("Scala 3 released at EPFL by Martin Odersky", entities))

    println(Formatters.formatNERResult("How do I center a div?", List.empty))
  }
}

object PruebaFormatEntityStats {
  def main(args: Array[String]): Unit = {
    val counts = Map(
      "Person" -> 5,
      "ProgrammingLanguage" -> 3,
      "Organization" -> 2,
      "University" -> 2
    )
    println(Formatters.formatEntityStats(counts))
  }
}