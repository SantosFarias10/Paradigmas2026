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
   *   Usar el método describe de cada entidad para generar la salida.
   *   No es necesario hacer match sobre el tipo concreto de cada entidad:
   *   describe ya funciona correctamente para cualquier subtipo (polimorfismo).
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
      // Recorremos cada entidad y aplicamos describe, luego unimos las lineas con \n
      val entityLines: String = entities.map(entity => s"  ${entity.describe}").mkString("\n")
      // Usamos s""" para crear un string multilinea
      s"""Post: "$postTitle"\nEntidades detectadas:\n$entityLines\n"""
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
    val stats: String = "=== Estadísticas de entidades ===" + "\n" +
      counts.map(c => c._1 + ": " + c._2).mkString("\n")
    return stats
  }

  // prueba
  def main(args: Array[String]): Unit = {
    val entities = List(
      new Person("Alan Turing"),
      new University("MIT"),
      new ProgrammingLanguage("Scala"),
      new Place("San Francisco")
    )
    println(formatNERResult("Scala 3 released at EPFL by Martin Odersky", entities))

    val counts: Map[String, Int] = Analyzer.countByType(entities)
    println(formatEntityStats(counts))
  }
}
