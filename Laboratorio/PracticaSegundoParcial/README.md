# Roles de los archivos

* `NamedEntity`: Class, modela las entidades nombradas.
* `Dictionary`: Objetct, carga las entidades desde archivos txt.
* `FileIO`: Object, lectura de archvos, descarga de urls, parseo JSON.
* `Analyzer`: Object, detecta entidades en un text y cuenta por su tipo.
* `Formatters`: Object, formatea los resultados como texto legible.
* `Main`: Object, integra todo.

## `class`

* Es el model para crear multiples instancias con `new`. Tiene estado propio, o sea los atributos xd.

## `object`

* Existe UNA SOLA instancia, no se instancia con `new`.

## `abstract class`

* No se puede instanciar directamente.
* Puede tener métodos concretos y abstractos. ¿?
* Las subclases DEBEN de implementar los métodos abstractos. ¿?

## `extendes`

* Herencia: Una clase hija hereda todo de la madre.

## `override`

* Indica que la subclase está reemplazando un método de la superclase.

# Dentro de `NamedEntity`

Clase abstracta `NamedEntity`

```scala
abstract class NamedEntity(val text: String) {
  // abstracto, por lo que cada subclase debe de definirlo
  def entityType: String

  // concreto, ya esta implementado para todas las subclases
  def describe: String = s"[$entityType] $text"
}
```D

* `text`: el nombre de la entidad (como por ejemplo: `Alan turing`, `Scala`, etc).
* `entityType`: Es un atributo abstracto, cada subclase devuelve su tipo como String.
* `describe`: Metodo concreto que arma una describción legile. Usa `entityType`, por lo que esto es **Polimorfismo**, el mismo método funciona distinto segun la subclase real.

## Jerarquia

```
NamedEntity (abstracta)
├── Person                → entityType = "Person"
├── Organization          → entityType = "Organization"
│   └── University        → entityType = "University"  (override)
├── Place                 → entityType = "Place"
└── Technology            → entityType = "Technology"
    └── ProgrammingLanguage → entityType = "ProgrammingLanguage"  (override)
```

Notar que:
* `University` extiende `Organization` (no directamente `NamedEntity`). Pero, como `Organization` extiende `NamedEntity`, `University` tambien es una `NamedEntity`. Esto es **herencia transitiva**.

# Dentro de `Dictionary`

Es un Object **Singleton**, no se instancia. Se llama directamente `Dictionary.loadAll().

## Función `loadFromFile`

```scala
def loadFromFile(filePath: String, entityType: String): List[NamedEntity]
```

* Lee un archivo con `FileIO.readLines(filePath)`, que retorna una `List[String]`.
* Por cada linea del archivo, crea la instancia correcta segun su `entityType` usando pattern matching xd.

```scala
entityType match {
  case "Person"              => new Person(name)
  case "Organization"        => new Organization(name)
  case "University"          => new University(name)
  case "Place"               => new Place(name)
  case "Technology"          => new Technology(name)
  case "ProgrammingLanguage" => new ProgrammingLanguage(name)
}
```

## Función `loadAll`

```scala
def loadAll(): List[NamedEntity]
```

* Llama a `loadFromFile` por cada archivo de diccionario.
* Los concatena con `++` en una sola `List[NamedEntity]`.

```
data/people.txt        → List[Person]
data/organizations.txt → List[Organization]
data/universities.txt  → List[University]
data/places.txt        → List[Place]
data/languages.txt     → List[ProgrammingLanguage]
                         ───────────────────────────
                         List[NamedEntity] (todos juntos)
```

# Dentro de `FileIO`

## Función `readSubscriptions(): List[String]`

* Devuelve hardcodeadas las URLs de los subreddits

## Función `downloadFeed(url: String): String`

* Usa `scala.io.Source.fromUrl(url)` para hacer peticiones HTTP.
* Devuelve el contenido de la Url como texto.

## Función `extractPostTitles(json: String): List[String]`

* Usa la libreria `json4s` para poder parsear el JSON del reddit.
* Busca todos los campos "title" dentro del JSON y los devuelve como lista.

## Función `readLines(filePath: String): List[String]`

* Lee un archivo de texto linea por linea.
* Filtra automaticamente las lineas vacias (`_.nonEmpty`) y comentarios (`_.startsWith("#")`).
* Esta función la usamos en `Dictionary.loadFromFile` para poder leer el diccionario.

# Dentro de `Analyzer`

## Función `detectEntities`

```scala
def detectEntities(text: String, dictionary: List[NamedEntity]): List[NamedEntity]
```

* Filtra el diccionario y devuelve solo las entidades que aparecen en `text`.
* Usamos Regex (Regular Expression) con limites en las palabras

## Función `countByType`

```scala
def countByType(entities: List[NamedEntity]): Map[String, Int]
```

* Cuenta cuantas entidades hay de cada tipo.

# Dentro de `Formatters`

## Función `formatNERResult(postTitle, entities)`

* Si `entities` esta vacio, entonces devolvemos `"No se detectaron entidades"`.
* Si hay entidades, entonces las lista usando entity.describe.

## Función `formatEntityStats(counts: Map[String, Int])`

* Toma el Map de tipo, cantidad y lo formatea  como texto.
* Ordenado de mayor a menor.

# Dentro de `Main`

Aca integramos todos los modulos.

```
PASO 1  →  Dictionary.loadAll()
              ↓ List[NamedEntity] (todo el diccionario)

PASO 2  →  FileIO.readSubscriptions()        → List[String] (URLs)
              ↓
           FileIO.downloadFeed(url)           → String (JSON crudo)
              ↓
           FileIO.extractPostTitles(json)     → List[String] (títulos)

PASO 3  →  Para cada título:
           Analyzer.detectEntities(title, dictionary)   → List[NamedEntity]
           Formatters.formatNERResult(title, entities)  → String (mostrar)
           (se acumulan todas las entidades con flatMap)

PASO 4  →  Analyzer.countByType(allDetectedEntities)   → Map[String, Int]
           Formatters.formatEntityStats(counts)         → String (mostrar)
```

# Flujo del programa

```
data/people.txt ──┐
data/places.txt ──┤ Dictionary.loadAll() ──→ List[NamedEntity] (diccionario)
  ...             ┘                                    │
                                                       │
Reddit API ──→ FileIO.downloadFeed() ──→ JSON          │
                    │                                  │
              extractPostTitles() ──→ List[String]     │
                    │                                  │
                    └──────────────────────────────────┘
                                     │
                            Analyzer.detectEntities()
                                     │
                            List[NamedEntity] detectadas
                                     │
                    ┌────────────────┴────────────────┐
                    │                                  │
          Formatters.formatNERResult()    Analyzer.countByType()
                    │                                  │
               println()                  Formatters.formatEntityStats()
                                                       │
                                                  println()
```
