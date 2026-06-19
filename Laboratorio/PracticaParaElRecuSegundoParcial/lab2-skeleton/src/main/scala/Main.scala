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
    //   Para cada post:
    //     1. Detectar entidades
    //     2. Formatear y mostrar el resultado
    // Retornamos todas las entidades detectadas (usando flatMap) para usarlas en el Paso 4
    val allDetectedEntities: List[NamedEntity] = allPosts.flatMap { 
      case (url, titles) =>
        println(s"\n--- Resultados para $url ---")
      
        titles.flatMap { title =>
        // 1. Detectar entidades
        val entities: List[NamedEntity] = Analyzer.detectEntities(title, dictionary)
        
        // 2. Formatear y mostrar el resultado
        println(Formatters.formatNERResult(title, entities))
        
        // Devolvemos las entidades de este post para que flatMap las junte todas
        return entities
      }
    }

    // ------------------------------------------------------------------
    // Paso 4: Estadísticas globales
    // ------------------------------------------------------------------
    //   1. Recolectar TODAS las entidades detectadas en todos los posts
    //   (Esto se hizo en el paso 3 guardándolas en allDetectedEntities)
    
    // 2. Contar por tipo
    val globalCounts: Map[String, Int] = Analyzer.countByType(allDetectedEntities)
    
    // 3. Mostrar el resumen
    println("\n" + Formatters.formatEntityStats(globalCounts))
  }
}
