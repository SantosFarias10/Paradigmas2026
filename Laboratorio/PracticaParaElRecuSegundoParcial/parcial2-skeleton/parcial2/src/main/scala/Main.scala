object Main {
  def main(args: Array[String]): Unit = {

    val dictionary: List[NamedEntity] = Dictionary.loadAll()
    println(s"Diccionario cargado: ${dictionary.size} entidades.\n")

    val subscriptions = FileIO.readSubscriptions()

    val allDetected: List[NamedEntity] = subscriptions.flatMap { url =>
      println(s"Descargando posts de: $url")
      val json   = FileIO.downloadFeed(url)
      val titles = FileIO.extractPostTitles(json)
      println(s"\n${"=" * 60}\n$url\n${"=" * 60}")
      titles.flatMap { title =>
        // Ej 3.b: detectRelevant + formatGroupedNERResult para mostrar por post
        val relevantEntities = Analyzer.detectRelevant(title, dictionary)
        println(Formatters.formatGroupedNERResult(title, relevantEntities))
        // Las estadísticas globales siguen sobre TODAS las entidades (sin filtrar)
        Analyzer.detectEntities(title, dictionary)
      }
    }

    println(s"\n${Formatters.formatEntityStats(Analyzer.countByType(allDetected))}")
  }
}
