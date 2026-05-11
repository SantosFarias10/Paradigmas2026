// =====================================================================
// Ejercicio 6: Integración del sistema completo
// =====================================================================

object Main {
  def main(args: Array[String]): Unit = {

    // ------------------------------------------------------------------
    // Paso 1: Cargar diccionarios
    // ------------------------------------------------------------------
    // (Ejercicio 2)
    val dictionary: List[NamedEntity] = Dictionary.loadAll()

    println(s"Diccionario cargado: ${dictionary.size} entidades.\n")

    // ------------------------------------------------------------------
    // Paso 2: Descargar posts
    // ------------------------------------------------------------------
    val subscriptions = FileIO.readSubscriptions()

    val allPosts: List[(String, List[String])] = subscriptions.map { url =>
      println(s"Descargando posts de: $url")
      val json   = FileIO.downloadFeed(url)
      val titles = FileIO.extractPostTitles(json)
      (url, titles)
    }

    // ------------------------------------------------------------------
    // Paso 3: Detectar entidades y mostrar resultados por post
    // ------------------------------------------------------------------
    // (Ejercicios 3, 4 y 6):
    //   Para cada post:
    //     1. Detectar entidades
    //     2. Formatear y mostrar el resultado

    // Retornamos todas las entidades detectadas (usando flatMap) para usarlas en el Paso 4
    // flatMap: aplica la función y luego convierte una lista de listas en una sola lista
    val allDetectedEntities: List[NamedEntity] = allPosts.flatMap { 
      case (url, titles) =>
        println(s"\n--- Resultados para $url ---")
      
        titles.flatMap { title =>
        // 1. Detectar entidades
        val entities = Analyzer.detectEntities(title, dictionary)
        
        // 2. Formatear y mostrar el resultado
        println(Formatters.formatNERResult(title, entities))
        
        // Devolvemos las entidades de este post para que flatMap las junte todas
        entities
      }
    }

    // ------------------------------------------------------------------
    // Paso 4: Estadísticas globales
    // ------------------------------------------------------------------
    // (Ejercicios 5 y 6):
    //   1. Recolectar TODAS las entidades detectadas en todos los posts
    //   (Esto se hizo en el paso 3 guardándolas en allDetectedEntities)
    
    // 2. Contar por tipo
    val globalCounts = Analyzer.countByType(allDetectedEntities)
    
    // 3. Mostrar el resumen
    println("\n" + Formatters.formatEntityStats(globalCounts))
  }
}
