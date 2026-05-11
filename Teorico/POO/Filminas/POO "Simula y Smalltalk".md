# Simula
## Objetos en Simula
* **Clases**: Un procedimiento que devuelve un puntero al activation record en el que se ejecuta.
* **Objeto**: Activation record que se genera al llamar a una clase.
* **Acceder un objeto**: Acceder cualquier variable o procedimiento local.
* **Manejo de memoria**: Recolección de basura.
## Como se representan los objetos
![](ComoSeRepresentanLosObjetosEnSimula.png)
Un objeto se representa con un activation record con un access link para encontrar las variables globales con alcance estático.
## Clases derivadas en Simula
Cuando se declara una clase se le puede prefijar el nombre de otra clase 
Un objeto de una clase "prefijada" es la concatenación de cada clase del prefijo
![](ClasesDerivadasSimula1.png)
![](ClasesDerivadasSimula2.png)
## Subtipado
El tipo de un objeto es su clase.
El tipo de una subclase se trata como un subtipo del tipo asociado con la superclase.
### Ejemplo
```
Class A(...); ...

A Class B(...); ...

ref (A) a:- new A(...)
ref (B) b:- new B(...)

a := b    // Esto es legal porque B es una sib clase de A
b := a    // Tambien es legal, pero hay que comprobarlo en 
		  //tiempo de ejecucion
```
## Que NO tenía Simula
* Encapsulación: Se pueden acceder a todos los datos y funciones.
* Sin mecanismo self/super (a diferencia de Smalltalk).
	* Pero se puede usar la expresión `this <class>` para referirse al objeto en sí mismo.
* Sin variables de clase, pero con variables globales.
* Sin excepciones.
## Resumen de Simula
* Una clase es un procedimiento que devuelve un puntero a un activation record, el código de inicialización se ejecuta siempre como cuerpo del procedimiento.
* Un objeto es una clausura creada por una clase.
* Sin encapsulación.
* Subtipado mediante jerarquía de clases.
* Herencia por prefijado de clases.
# Smalltalk
* Todo es un objeto, incluso una clase (como en Lisp "todos es una lista").
* Todas las operaciones son mensajes a objetos.
* Muy flexible y poderoso: Si un objeto que recibe un mensaje que no entiende, trata de inferir qué puede hacer.
## Terminología Smalltalk
* **Objeto**: Instancia de una clase.
* **Clase**: Define el comportamiento de sus objetos.
* **Subclase**: Clase definida como modificaciones incrementales a una superclase.
* **Selector**: Nombre de un mensaje.
* **Mensaje**: Selector con valores para sus parámetros.
* **Método**: Código que usa una clase para responder a un mensaje.
* **Variable de instancia**: Datos guardados en un objeto.
## Tipos de objetos
Cada objeto tiene una interfaz
* Interfaz: Método de instancia declarados en la clase.
* Es una forma de tipo, solo los nombres de los métodos, nada sobre los argumentos.
Uso de objetos con tipo
* Cuando se envía un mensaje a un objeto la expresión anda si el mensaje está en la interfaz.
## Encapsulación en Smalltalk
Los métodos son públicos.
Las variables de instancia están ocultas
* Invisibles para otros objetos, pero las pueden manipular los métodos de subclases
	* Esto limita la forma de establecer invariantes.
	* por ejemplo
		* Una superclase mantiene una lista ordenada de mensajes con algún selector, por ejemplo, `insert`.
		* Una subclase puede acceder esta lista directamente y reordenarla.
## Subtipado
El subtipado es implícito
* No es parte del lenguaje
* Es un aspecto importante de cómo se construyen los sistemas.
## Herencia 
La herencia es explícita
* Se usa para implementar sistemas.
* No fuerza la relación a subtipado.
## Flexibilidad de Smalltalk
Expresividad: ¿Se pueden definir las construcciones del lenguaje en le lenguaje mismo?
Smalltalk es expresivo en este sentido, muchas construcciones primitivas en otros lenguajes se pueden definir en Smalltalk (por ejemplo, booleanos y bloques)
## Resumen
**Clase**: Crea objetos que comparten métodos.
* Punteros al template, diccionario, clase madre.
**Objetos**: Creados por una clase, contienen variables de instancia.
**Encapsulación**:
* Los métodos son públicos, las variables de instancia son ocultas.
**Subtipado**: Implícito, sin sistemas de tipos estático.
**Herencia**: Subclases, self, super.