# Simula
## Objetos en Simula
* Clase
	* Un procedimiento que devuelve un puntero al activation record en el que se ejecuta.
* Objeto
	* Activation record que se genera al llamar una clase.
* Acceder un Objeto
	* Acceder cualquier variable o procedimiento local
* Manejo de memoria
	* Recolección de Basura.
## Cómo se representan los objetos
Un Objeto se representa con un activation record con un access link para encontrar las variables globales con alcance estático.
![](Screenshot_2026-07-29-10-52-28_17824.png)
## Clases Derivadas en Simula
Cuando se declara una clase se le puede prefijar el nombre de otra clase
![](Screenshot_2026-07-29-10-54-37_30702.png)
Un objeto de una clase "prefijada" es la concatenación de objetos de cada clase del prefijo
![](Screenshot_2026-07-29-10-55-22_23661.png)
## Subtipado
El tipo de un objeto es su clase.
El tipo de una subclase se trata como un subtipado del tipo asociado con la Superclase.
## Características Principales Orientadas a Objetos
* Clases.
* Objetos.
* Herencia ("Prefijado de clases").
* Subtipado.
* Métodos virtuales: Se puede redefinir una función en una subclase.
## ¿Qué NO tiene Simula?
* Encapsulamiento: Se puede acceder a todos los datos y funciones.
* Sin mecanismo self/super (a diferencia de Smalltalk)
	* Pero se puede usar la expresión `this <class>` para referirse al objeto en sí mismo.
* Sin variables de clase, pero con variables globales.
* Sin excepciones.
## Resumen
- Una clase es un procedimiento que devuelve un puntero a un activation record, el código de inicialización se ejecuta siempre como cuerpo del procedimiento.
- Un objeto es una clausura creada por una clase.
- NO tiene encapsulamiento.
- Subtipado es mediante jerarquía de clases.
- Herencia por prefijado de clases.
# Smalltalk
* Todo es un objeto, incluso una clase (como en Lisp "Todo es una lista").
* Todas las operaciones son mensajes a objetos.
* Muy flexible y poderoso
	* Si un objeto que recibe, un mensaje que no entiende, trata de inferir qué puede hacer.
## Terminología Smalltalk
* Objeto: Instancia de una clase
* Clase: Define el comportamiento de sus objetos
* Subclase: Clase definida con modificaciones incrementales a una superclase.
* Selector: Nombre de un mensaje.
* Método: Código que usa una clase para responder a un mensaje.
* Variable de instancia: Datos guardados en un objeto.
## Tipos de Objetos
Cada objeto tiene una interfaz
* Interfaz: Método de instancia declarados en la clase.
	* Es una forma de tipo: Sólo los nombres de los métodos, nada sobre los argumentos.
* Uso de objetos con tipo
	* Cuando se envía un mensaje a un objeto
	* El mensaje anda si el mensaje está en la interfaz.
## Encapsulación
* Los métodos son públicos.
* Las variables de instancia están ocultas.
	* Invisible para otros objetos
	* Pero las pueden manipular los métodos de subclase.
		* Esto limita la forma de establecer invariantes.
		* Por ejemplo:
			* Una superclase mantiene una lista ordenada de mensajes con algún selector.
			* Una subclase puede acceder esta lista directamente y reordenarla.
## Herencia y Subtipado
### Subtipado
* Relación entre interfaces
	* Supongamos que la expresión tiene sentido `p msg::params`, funciona si `msg` está en la interfaz de `p`.
	* Sustituimos `p` por `q` si la interfaz de `q` contiene a la interfaz de `p`.
* Subtipado
	* Si la interfaz es un superconjunto, entonces es un subtipo
El subtipado es implícito 
* No es parte del lenguaje.
* Es un aspecto importante de cómo se construye los sistemas.
Mientras que la Herencia es explícita
* Se usa para implementar sistemas.
* No fuerza la relación a subtipado.
## Flexibilidad y Expresividad
* Expresividad: ¿se pueden definir las construcciones del lenguaje en el lenguaje mismo?
	* Lisp cond: Lisp permite formas especiales definidas por el usuario.
	* ML datatype: Suficiente para definir listas polimórficas, equivalentes al tipo lista built-in.
	* ML overloading: No está disponible para el programador.
* Smalltalk es expresivo en este sentido
	* Muchas construcciones primitivas en otros lenguajes se pueden definir en Smalltalk (como Booleanos y Bloques).
### Booleanos y Bloques
El valor Booleano es un objeto con `ifTrue:` `ifFalse:`.
* La clase `boolean` con subclases `True` y `False`.
* `True ifTrue:B1 ifFalse:B2` ejecuta `B1`
* `False ifTrue:B1 ifFalse:B2` ejecuta `B2`.
Los Booleanos y los bloques son muy comunes
* Hay una optimización para booleanos.
* Sintaxis especial para bloques.
## Operaciones de Enteros
Propiedades:
* Todas las operaciones se ejecutan enviando mensajes.
* Si `x` es de algún nuevo tipo de entero, la expresión tiene sentido siempre que `x` tenga los métodos `plus`, `times`, `print`.
En realidad el compilador tiene algunas optimizaciones hardcodeadeas, pero se revierte a esto si `x` no es un entero built-in.
## Resumen
* Clase: Crea objetos que comparten métodos.
	* Punteros al template, diccionario, clase madre.
* Objetos: Creados por una clase, contienen variables de instancia.
* Encapsulación: Los métodos son públicos, las variables de instancia son ocultas.
* Subtipado: Implícito, sin sistema de tipos estático.
* Herencia: Subclases, self, super.
