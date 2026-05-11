// =====================================================================
// Ejercicios 3 y 5: Detección y conteo de entidades
// =====================================================================

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
    // Each entry in the dictionary is a NamedEntity (can be Person, University, etc.)
    // Filter dictionary to only include entities that appear in text
    val listFiltered: List[NamedEntity] = dictionary.filter(entity => text.contains(entity.text))

    return listFiltered
  }

  /**
   * Cuenta cuántas entidades de cada tipo fueron detectadas.
   *
   * @param entities lista de entidades detectadas
   * @return mapa de entityType → cantidad de apariciones
   *
   * (Ejercicio 5): Implementar este método.
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
    val result: Map[String, Int] = entities
      // recorre la lista original de entidades (entities), para cada entidad evalua la funcion
      // que le pasamos entity => entity.entityType. La clave es el tipo de entidad (String)
      // y el valor es una lista con todas las entidades que pertenecen a ese "tipo"
      // groupBy return a Map[String, List[NamedEntity]]
      .groupBy(entity => entity.entityType)
      // recorremos cada par (clave, valor) del Map. Usando pattern marching extraemos el nombre
      // del tipo y la lista de entidades. Luego le decimos que por cada par original, genere
      // un nuevo par: La clave sigue siendo el entityType (String), y el valor ahora es el tamaño
      // de la lista entityList (Int) 
      .map {
        case (entityType, entityList) => (entityType, entityList.size)
      }
    
    return result
  }

  // Test
  def main(args: Array[String]): Unit = {
    val entities = List(
      new Person("Alan Turing"),
      new ProgrammingLanguage("Scala"),
      new Person("Ada Lovelace"),
      new University("MIT")
    )

    val counts = countByType(entities)
    println(counts)
    // Map(ProgrammingLanguage -> 1, Person -> 2, University -> 1)
  }
}
