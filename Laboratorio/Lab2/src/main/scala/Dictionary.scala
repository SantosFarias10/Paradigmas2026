// =====================================================================
// Ejercicio 2: Cargar diccionarios de entidades
// =====================================================================

/**
 * Responsable de cargar colecciones de entidades nombradas desde archivos.
 *
 * Un diccionario es un archivo de texto plano donde cada línea contiene
 * el nombre de una entidad conocida del mismo tipo.
 *
 * Ejemplo — data/people.txt:
 *   Martin Odersky
 *   Alan Turing
 *   Ada Lovelace
 *
 * Ejemplo — data/languages.txt:
 *   Scala
 *   Python
 *   Haskell
 */
object Dictionary {

  /**
   * Lee un archivo de diccionario y crea una lista de entidades del tipo indicado.
   *
   * @param filePath   ruta al archivo de diccionario (ej: "data/people.txt")
   * @param entityType tipo de entidad: "Person", "University", "ProgrammingLanguage", etc.
   * @return lista de NamedEntity del tipo correspondiente
   *
   */
  def loadFromFile(filePath: String, entityType: String): List[NamedEntity] = {
    // Read lines from file, used function from FileIO
    val lines = FileIO.readLines(filePath)
    // Remove whitespace
    lines.map(_.trim)
    // Remove empty lines
    lines.filter(_.nonEmpty)

    // create list of NamedEntity, based on entityType
    val list: List[NamedEntity] = lines.map { name =>
      entityType match {
        case "Person"               => new Person(name)
        case "Organization"         => new Organization(name)
        case "University"           => new University(name)
        case "Place"                => new Place(name)
        case "Technology"           => new Technology(name)
        case "ProgrammingLanguage"  => new ProgrammingLanguage(name)
      }
    }

    return list
  }

  /**
   * Carga todos los diccionarios disponibles y combina sus entidades.
   *
   * @return lista con todas las entidades de todos los diccionarios
   *
   */
  def loadAll(): List[NamedEntity] = {
    // Load all dictionaries
    val people        = loadFromFile("data/people.txt", "Person")
    val organizations = loadFromFile("data/organizations.txt", "Organization")
    val universities  = loadFromFile("data/universities.txt", "University")
    val places        = loadFromFile("data/places.txt", "Place")
    val languages     = loadFromFile("data/languages.txt", "ProgrammingLanguage")

    // Combine all dictionaries
    val all: List[NamedEntity] =
      people ++ organizations ++ universities ++ places ++ languages

    return all
  }

  // Test
  def main(args: Array[String]): Unit = {
    val dict = Dictionary.loadAll()
    println(s"Total de entidades: ${dict.size}")
    dict.filter(_.entityType == "Person").foreach(p => println(p.describe))
  }
}