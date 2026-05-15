// importa la clase Regex que representa a una "Expresión Regular"
import scala.util.matching.Regex
// 
import java.util.regex.Pattern

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
    // `val listFiltered: List[NamedEntity] = dictionary.filter(entity => text.contains(entity.text))`
    // Esta implementación esta mal, ya que al tener por ejemplo "Alan Turing programa 
    // en Javascript", si tenemos en el diccionario "Java" toma a "Javascript" como una entity.

    // Para solucionar el problema de contains, usamos "Regular Expression"
    val dictionaryFiltered: List[NamedEntity] = dictionary.filter { entity =>
      // Pattern.quote() = Si tenemos "C++", este metodo lo transforma en un string "Seguro", para que la regex lo interprete como string y no un operador.
      val textRegex: String = Pattern.quote(entity.text)

      /*
      Creamos una regex (expresión regular) que coincida con la palabra como una "palabra completa". Esto lo hacemos por si tenemos +, -, \, /, etc.
        * \b (Word Boundary / Limite de palabras) = Indica donde empieza la palabra (el principio) y donde finaliza la palabra (al final) a comparar, aca garantizamos que el programa no matchee la palabra Java si aparece "\bJavascript\b"
        * .r = Metodo de scala que convierte ese string interpolado que acabamos de construir en un objeto de tipo Regex.
        * findFirstIn() = Este metodo que busca la primera vez que la expresion regular aparecen dentro de text, retorna un Option[String], si lo encuentra entonces devuelve Some(match) donde match es el string que se encontro y si no lo encuentra devuelve None
        * isDefined() = Este metodo es de la clase Option, retorna un booleano, true si encuentra algo y false si no.
      */
      val regex: Regex = s"\\b${textRegex}\\b".r
      //Busca la primera ocurrencia en el texto
      val entityFound: Boolean = regex.findFirstIn(text).isDefined
      entityFound
    }

    return dictionaryFiltered
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

    // Diccionario de entidades conocidas
    val dictionary: List[NamedEntity] = Dictionary.loadAll()

    val dictionary2: List[NamedEntity] = List(
      new Person("Alan Turing"),
      new ProgrammingLanguage("Java"),
      new Place("San Francisco"),
      new University("EPFL")
    )

    val text: String = "alan turing Programa en Javascript, en San Francisco, y EPFF,"
    val result: List[NamedEntity] = detectEntities(text, dictionary2)
    
    println(result)
    // Esto imprime 
  }
}
