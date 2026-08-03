// =====================================================================
// Ejercicio 6: Integración del sistema completo
// =====================================================================

object Main {
  def main(args: Array[String]): Unit = {

    // ------------------------------------------------------------------
    // Paso 1: Cargar diccionarios
    // ------------------------------------------------------------------
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
    val globalCounts = Analyzer.countByType(allDetectedEntities)
    
    // 3. Mostrar el resumen
    println("\n" + Formatters.formatEntityStats(globalCounts))

  }
}
