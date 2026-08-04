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
      else entities.map { e =>
        val lowerTitle = postTitle.toLowerCase
        if (lowerTitle.contains(e.text.toLowerCase)) {
          s"  ${e.describe}"
        } else {
          val alias = e.aliases.find(a => lowerTitle.contains(a.toLowerCase)).getOrElse("")
          s"  ${e.describe}  (detectado como: $alias)"
        }
      }.mkString("\n")
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

  def formatScoredResult(postTitle: String, entities: List[NamedEntity]): String = {
    val header = s"""Post: "$postTitle"\nEntidades detectadas (por relevancia):"""
    val body =
      if (entities.isEmpty) "  (sin entidades detectadas)"
      else entities
        // Truco al pone (-) invertimos el orden, por lo que nos los va a ordenar de mayor a menor
        .sortBy(-_.relevanceScore)
        .map(e => s"  [${e.relevanceScore}] ${e.describe}")
        .mkString("\n")
    s"$header\n$body"
  }
}
