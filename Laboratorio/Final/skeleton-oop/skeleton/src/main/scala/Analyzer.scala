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
    val listFiltered: List[NamedEntity] = dictionary.filter { entity =>
      entity.appearsIn(text)
    }

    return listFiltered
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

  // Agrupa las entidades detectadas y retorna, por cada grupo, la cadena generada por pluralLabel de ese tipo
  //def summarizeByLabel(entities: List[NamedEntity]): List[String] = {
    // Map[String, List[NamedEntity]] -> List[(String, List[...])]
    /*
    val grouped = entities.groupBy(_.entityType).toList
    val result: List[String] = grouped.map { case(entity, group) =>
      val count: Int = group.size

      val resultado= pluralLabel(count)
    }

    return result
  }
  */

  // Retorna las entidades que aparecen en al menos minMentions post distintos.
  def trendingEntities(posts: List[String], dictionary: List[NamedEntity], minMentions: Int): List[NamedEntity] = {
    val dictionaryFiltered: List[NamedEntity] = dictionary.filter { entities =>
      val x: List[NamedEntity] = post.map { p =>
        detectEntities(p)
      }
      x.isDefined()
    }
  }
}
