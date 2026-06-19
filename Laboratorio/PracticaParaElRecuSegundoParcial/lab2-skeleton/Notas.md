# Clase Abstraca vs. Clase Concreta

Clase Concreta:

- Puede ser instanciada (con `new`).
- Todos sus metodos tiene implementación.
- Representan una entidad completa.

Clase Abstracta:

- No puede ser instanciada.
- Sirve como una "plantilla" para subclases.
- Las subsclases deben implementar los métodos abstractos.

## ¿Por qué describe funciona correctamente para todos los tipos sin necesidad de redefinirlo en cada subclase?

Porque usa `entityType`. Cuando se ejecuta en una subclase, scala va a buscar `entityType` en el objeto real, no en la clase donde está escrito `describe`.

---

# En el ejercicio 2

Implementamos `loadFromFile`

- Lee un archivo de diccionarios y crea una lista de entidades del tipo indicado.
- Toma como parametros el `filePath` (ruta al archivo de diccionario) y el `entityType` (el tipo de entidad)
- Retornamos una `List[NamedEntity]` con todas las entidades de `entityType` que se encuentran en `filePath`.

También se implemento `loadAll`

- Lee todos los diccionarios disponibles y combina sus entidades.
- No toma parametros.
- Retorna una `List[NamedEntity]` con todas las entidades de todos los diccionarios.

---

# Ejercicio 4

## ¿Qué ventaja tiene usar métodos de forma polimórfica respecto a escribir cada caso que distinga cada subclase?

Con polimorfismo, el objeto sabe cómo comportarse. Sin él, el código externo tiene que adivinar qué tipo es y manejar cada caso manualmente.

---
