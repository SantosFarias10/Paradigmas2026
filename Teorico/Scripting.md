## ¿Qué es un lenguaje de scripting?
Un **lenguaje de scripting** es un tipo de lenguaje de programación diseñado para ser **muy rápido de aprender y de escribir**, cuyo propósito principal suele ser la **coordinación de múltiples programas y la automatización de tareas**, a diferencia de los lenguajes tradicionales que se enfocan en crear aplicaciones autónomas desde cero.
### Tipos de lenguajes de scripting
(Según notebook)
- **Lenguajes de propósito general** o "**pegamento**" (*glue languages*): Están diseñados para conectar componentes de software, manipular archivos, gestionar procesos y comunicarse con el sistema operativo o servicios de red. 
	- Ejemplos clásicos son Perl, Python y Ruby.
- **Lenguajes de extensión o específicos de dominio:** Se incorporan dentro de una aplicación mayor (como un navegador web, un programa de diseño o un videojuego) para **permitir al usuario crear nuevos comandos o automatizar tareas** interactuando directamente con la interfaz y las funciones de dicha aplicación.
	- Ejemplos de esto son JavaScript, Tcl, Lua o lenguajes de macros como VBA.
### Características de los lenguajes de scripting
(Según notebook)
- **Código muy conciso:** Permiten lograr resultados escribiendo muchas menos líneas de código.
- **Tipado dinámico y flexible:** Los tipos de las variables suelen comprobarse justo antes de usarse, y muchos lenguajes realizan conversiones automáticas (casteos) sobre la marcha.
- **Sin declaraciones explícitas:** Por lo general, omiten la necesidad de declarar variables antes de usarlas y operan con reglas de alcance (visibilidad) muy simples.
- **Tipos de datos de alto nivel:** Incorporan de manera nativa estructuras complejas como listas, tuplas, conjuntos y diccionarios, y abstraen el manejo de la memoria mediante garbage collection.
- **Manejo avanzado de cadenas de texto:** Poseen capacidades muy sofisticadas y eficientes para el _pattern matching_ y la manipulación de strings, generalmente apoyadas en expresiones regulares.
- **Facilidad de integración:** Proveen bibliotecas con amplio soporte para interactuar con otros programas, redes, bases de datos y el sistema de archivos del sistema operativo.
- **Ejecución interpretada:** Suelen ser interpretados a partir de su código fuente o de un _bytecode_, lo que permite ejecutarlos secuencialmente "como un guión" (de principio a fin) y facilita enormemente su uso de forma interactiva en la consola de comandos.
### Diferentes tipos de lenguajes de scripting
* Lenguajes específicos de dominio o lenguajes de extensión: 
	* Para escribir pequeños programas que automatizan la ejecución de tareas en un entorno específico (un navegador, un intérprete de comandos, etc), combinando llamadas a APIs o tareas elementales.
* Lenguajes dinámicos de muy alto nivel de propósito general.
## Lenguajes de Dominio: Scripts
Lenguajes de interprete de comandos (shell).
* Pensados para el uso interactivo.
* Mecanismos para manejar nombres de archivos, argumentos y comandos, y para "pegar" otros programas (los lenguajes genéricos también tienen estas utilidades).
# Scripts
## Expansión de nombres de archivos y variables
Expansión de *wildcards* (o "*globbing*" por el comando original de Unix glob) para obtener archivos que se corresponden con patrones.
Por ejemplo:
* `ls *.pdf`
* `ls fig?.pdf`
## Test y queries
## Pipes and redirection
## Functions
## The !# syntax
