## Objetivos POO
* Desarrollo de programas modulares
	* Refinamiento incremental.
	* Interfaz, especificación e implementación.
* Soporte de los lenguajes para la modularidad
	* Abstracción procedural.
	* Tipos abstractos de datos.
	* Paquetes y módulos.
	* Abstracciones genéricas (con parámetros de tipo).
# Modularidad
## Conceptos Básicos
* **Componente**: Unidad de programa con sentido
	* Función, estructura de datos, módulo, ...
* **Interfaz**: Tipo y operaciones definidos dentro de una componente que son visibles fuera de la componente.
* **Especificación**: Comportamiento esperado de una componente, expresado como una propiedad observable a través de la interfaz.
* **Implementación**: Estructura de datos y funciones dentro de la componente.
# Herramienta de los Lenguajes para la Abstracción
## Tipo Abstracto de Datos (TADs)
### Idea 1
Separar la interfaz de la implementación
### Idea 2
Usar comprobación de tipos para forzar la separación
* El programa cliente solo tiene acceso a las operaciones de la interfaz.
* La implementación encapsula en el constructo TAD.
## Modulo
Construcción general para ocultar.
* En Modula son los módulos,
* En Ada son los paquetes,
* En ML las estructuras, etc.
Interfaz: Conjunto de nombres y sus tipos
Implementación:
* Declaración para cada entrada en la interfaz,
* Declaraciones extra que están ocultas.
## Abstracciones genéricas
Parametrizar los módulos por tipos
* Implementaciones generales, que se pueden instanciar de muchos formas; La misma implementación para múltiples tipos.
### Templates de C++
Mecanismo de parametrización de tipos, `template <class T>` indica el parámetro de tipo `T`.
* C++ tiene templates de clase y de función.
Se instancian en tiempo de ligado
* Se crea una copia del template generado para cada tipo.
* Pero ¿Por qué duplicar el código?
	* Tamaño de variables locales en el activation record.
	* Ligado a las operaciones del tipo instanciado.
### Diferencias entre ML y C++
* ML
	* Las funciones polimórficas en ML se compilan a **una sola secuencia de instrucciones** compartida por todos los llamados. Esto es posible porque en ML las variables locales son punteros a valores en el heap con tamaño constante, evitando duplicar código ejecutable.
* C++
	* Aunque la duplicación en tiempo de ligado genera ejecutables más grandes, C++ toma esta decisión de diseño para evitar el costo (_overhead_) de seguir punteros indirectos en tiempo de ejecución, garantizando la máxima velocidad posible.
# Propiedades Importantes de la Orientación a Objetos
* Objetos.
* Lookup dinámico.
* Encapsulación.
* Herencia.
* Subtipado.
## Objetos
Un objeto consiste de:
* Datos ocultos
	* Variables de la instancia (datos del miembro)
	* Posiblemente funciones ocultas.
* Operaciones públicas
	* Métodos (funciones del miembro)
	* Puede tener variables públicas en algunos mensajes.
Un programa orientado a objetos envía mensajes a los objetos.

## Lookup Dinámico
El **lookup dinámico** (búsqueda dinámica). Se refiere al mecanismo por el cual la selección del código concreto (el método) que se va a ejecutar al enviar un mensaje a un objeto se determina en tiempo de ejecución (*run-time*), dependiendo de la implementación específica del objeto receptor.

En programación convencional, el significado de una operación con los mismos operandos es siempre el mismo.
* `Operación(operando)`.
En POO
* `object -> message(arguments)`.
El código depende del objeto y el mensaje.
### Sobrecarga vs. Lookup Dinámico
La sobrecarga se resuelve en tiempo de compilación, mientras que el lookup dinámico se resuelve en tiempo de ejecución.
## Encapsulamiento
El programador de un concepto tiene una vista detallada.
El usuario de un concepto tiene una vista abstracta.
La encapsulación separa estas dos vistas, de forma que el código de cliente opera con un conjunto fijo de operaciones que provee el implementador de la abstracción.
## Subtipado y Herencia
El **subtipado** es una relación entre interfaces, recordar que una interfaz es la vista externa de un objeto.
La **herencia** es una relación entre implementaciones, de forma que nuevos objetos se pueden definir reusando implementaciones de otros objetos. Recordar que la implementación es la representación interna de un objeto.
### Subtipado
Si la interfaz `A` contiene todos los elementos de la interfaz `B`, entonces los objetos de tipo `A` también se pueden usar como objetos de tipo `B`.
# Estructura de un Programa Orientado a Objetos
* Agrupar datos y funciones.
* Clase
	* Define el comportamiento de todos los objetos que son instancias de la clase.
* Subtipado
	* Organiza datos semejantes en clases relacionadas.
* Herencia
	* Evita reimplementar funciones ya definidas.
