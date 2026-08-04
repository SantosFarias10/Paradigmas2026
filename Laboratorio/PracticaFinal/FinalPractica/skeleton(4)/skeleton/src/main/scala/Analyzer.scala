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
   */
  def detectEntities(text: String, dictionary: List[NamedEntity]): List[NamedEntity] = {
    val lowerText = text.toLowerCase

    dictionary.filter(entity =>
      // allNames devuelve una lista con el nombre base y los aliases
      // exists(...) recorre esa lista y devuelve true si al menos uno cumple la condición
      entity.allNames.exists(name =>
        lowerText.contains(name.toLowerCase)
      )
    )
  }

  /**
   * Cuenta cuántas entidades de cada tipo fueron detectadas.
   *
   * @param entities lista de entidades detectadas
   * @return mapa de entityType → cantidad de apariciones
   */
  def countByType(entities: List[NamedEntity]): Map[String, Int] = {
    entities.groupBy(_.entityType).view.mapValues(_.size).toMap
  }

  //
  def detectAboveThreshold(text: String, dictionary:List[NamedEntity], threshold: Int): List[NamedEntity] = {
    val listFiltered: List[NamedEntity]= detectEntities(text, dictionary).filter(entity =>
      entity.relevanceScore >= threshold
    )

    return listFiltered
  }
}
