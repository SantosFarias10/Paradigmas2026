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
   */
  def formatNERResult(postTitle: String, entities: List[NamedEntity]): String = {
    val header = s"""Post: "$postTitle"\nEntidades detectadas:"""
    val body =
      if (entities.isEmpty) "  (sin entidades detectadas)"
      else entities.map(e => s"  ${e.describe}").mkString("\n")
    s"$header\n$body"
  }

  /**
   * Formatea un resumen de estadísticas de entidades por tipo.
   *
   * @param counts mapa de entityType → cantidad
   * @return texto con las estadísticas ordenadas por cantidad (de mayor a menor)
   */
  def formatEntityStats(counts: Map[String, Int]): String = {
    val lines = counts.toList
      .sortBy(-_._2)
      .map { case (entityType, count) => s"$entityType: $count" }
    ("=== Estadísticas de entidades ===" :: lines).mkString("\n")
  }

  /**
   * Ejemplo:
   *   Post: "Odersky at ICFP: Scala's future is bright"
   *   Entidades detectadas:
   *     Conference (1):
   *       ICFP
   *     Person (1):
   *       Odersky
   *     ProgrammingLanguage (1):
   *       Scala
  */
  def formatGroupedNERResult(postTitle: String, entities: List[NamedEntity]): String = {
    val header = s"""Post: "$postTitle"\nEntidades detectadas:"""

    val body =
      if (entities.isEmpty) "  (sin entidades detectadas)"
      else {
        val grouped = entities
          // Primero agrupamos por tipo de entidad, recordar que groupBy retorna un Map[String, List[NamedEntity]] = entidad -> lista de entidades
          .groupBy(_.entityType)
          // Luego convertimos el Map en List[(String, List[NamedEntity])]
          .toList
          // Y por ultimo ordenamos alfabeticamente
          // _._1 toma el primer elemento de la List o sea el string del tipo de entidad y lo ordena alfabeticamente
          .sortBy(_._1)

        grouped.map { case (entityType, group) =>
          val groupHeader = s"  $entityType (${group.size}):"

          // entidades del grupo ordenadas alfabéticamente por text
          val groupLines = 
            // Ordenamos alfabeticamente por el nombre 
            group.sortBy(_.text)
            // agregamos 4 espacio al principio de cada nombre de entidad
            .map(e => s"    ${e.text}")
          
          // unimos el header del grupo con las entidades del grupo
          (groupHeader :: groupLines).mkString("\n")
        }
        // unimos todos los grupos
        .mkString("\n")
      }

    // Imprimimos todo
    s"$header\n$body"
  }
}
