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
   */
  def loadFromFile(filePath: String, entityType: String): List[NamedEntity] = {
    // Leemos el filepath sacando espacios vacios y lineas vacias
    val lines: List[String] = FileIO.readLines(filePath)
                              .map(_.trim)
                              .filter(_.nonEmpty)
    
    // Hacemos match con el tipo de entidad y creamos la instancia correspondiente
    // Nos termina quedando algo como 
    // List[Person(Ada Lovelace), Person(Grace Hopper), ...]
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
   */
  def loadAll(): List[NamedEntity] = {
    // Por cada archivo, llamamos a loadFromFile para crear una lista de entidades
    val people: List[NamedEntity]        = loadFromFile("data/people.txt", "Person")
    val organizations: List[NamedEntity] = loadFromFile("data/organizations.txt", "Organization")
    val universities: List[NamedEntity]  = loadFromFile("data/universities.txt", "University")
    val places: List[NamedEntity]        = loadFromFile("data/places.txt", "Place")
    val languages: List[NamedEntity]     = loadFromFile("data/languages.txt", "ProgrammingLanguage")

    // Combinamos todas las listas en una sola
    val all: List[NamedEntity] =
      people ++ organizations ++ universities ++ places ++ languages

    return all
  }

  // Prueba
  def main(args: Array[String]): Unit = {
    val dict = Dictionary.loadAll()
    println(s"Total de entidades: ${dict.size}")
    dict.filter(_.entityType == "Person").foreach(p => println(p.describe))
  }
}