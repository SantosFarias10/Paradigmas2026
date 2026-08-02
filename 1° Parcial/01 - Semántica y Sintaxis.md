# Semántica
Un Programa es la descripción de un proceso dinámico
### Semántica vs. Sintaxis
La **Sintaxis** es el texto del programa, mientras que la **Semántica** es el significado del programa.
## Delimitación de la semántica de los lenguajes de programación
Los programas pueden definir Funciones Parciales:
* Algunos de sus valores pueden ser **Indefinidos** (por ejemplo, si no terminan).
* Algunos de sus valores puede ser **Errores**.

Intuitivamente, una función es computable si hay algún programa que la computa.
* El problema, definición dependiente de la implementación de un lenguaje de programación concreto, con sus limitaciones y particularidades.
Nosotros queremos una definición independiente (libre) de lenguaje.
### ¿Cómo definir la clase de funciones computables?
* Funciones Matemáticas: **Funciones Recursivas Parciales**.
* Las que se pueden computar con una máquina idealizada abstracta: **Máquina de Turing**.
* Las que se pueden expresar en **Lambda Cálculo**.
## Semántica Operacional
Es una representación abstracta de la ejecución de un programa, como secuencia de transiciones entre estados (en una máquina abstracta).
Los **Estados** son una descripción abstracta de la memoria y estructura de datos.
Las transiciones siguen la estructura de la sintaxis.
## Maquina abstracta
![](ModeloSimplificadoDeLaComputadora.png)
Separamos la memoria de código y de datos:
* **Contador de programa**: Dirección de memoria con la instrucción que se está ejecutando.
* **Puntero de Entorno**: Valores de las variables en una parte del código.
### Lenguajes NO estructurados por bloques vs. Lenguajes estructurados por bloque
- **Lenguajes no estructurados por bloques**: La memoria de datos es no estructurada, los valores de las variables son visibles desde todo el código.
- **Lenguajes estructurados por bloques**: Cuando un programa entra en un nuevo bloque, se agrega a la pila un *Activation Record* con espacio para las variables locales del bloque, y el puntero de entorno apunta al nuevo activation record. Cuando el programa sale del bloque, se retira el activation record de la pila y el puntero de entorno se restablece a su ubicación anterior.
## Stack (Pila de Ejecución)
Los activation record se guardan en el stack
* Cada nuevo bloque apila (*push*) un nuevo activation record en el stack,
* Cada vez que se termina un bloque se saca (*pop*) el activation record de arriba de la pila.
* El stack tiene todos los activation record que son activos en un determinado momento de la ejecución, con el que se usó más recientemente en la punta.
### Activation Record o Stack frames (Marcos de pila)
Guardan la información de un bloque:
* Variables locales.
* Control link al que ha llamado al activation record, para reubicar el puntero de entorno.
* Variables temporales y resultados intermedios.
Entra y salen del stack, eso hace que puedan usarse llamados anidados, recursivas.
## Ejemplo de Pila de Ejecución
![](SecuenciaActivationRecord.png)

# Sintaxis
Como ya vimos:
* La sintaxis es el texto del programa,
* Mientras que la semántica es el significado del programa.
La **implementación** de un lenguaje de programación debe transformar la sintaxis de un programa en instrucciones de máquina que se pueden ejecutar para que suceda la secuencia de acciones que se pretendía.
## Transformación de Sintaxis a Semántica
Un lenguaje de programación es un conjunto de abstracciones y empaquetamientos quizás sin correspondencia directa con la máquina.
* Es necesario traducir lenguajes de programación a instrucciones de máquina.
* El **Compilador** hace esa traducción, se encarga de procesar la sintaxis de los lenguajes de programación.
* Un **Interprete** puede combinar traducción y ejecución.
### ¿Qué es un compilador?
Un programa que lee un programa escrito en un lenguaje origen y lo traduce a un programa equivalente (con el mismo significado) en un lenguaje destino.
* Dos componentes:
	* Entender el programa (o sea, asegurarse de que es correcto).
	* Reescribir el programa.
* Normalmente, el lenguaje origen es de alto nivel y el destino es de bajo nivel.
### Fases de un compilador
* Análisis léxico.
* Análisis sintáctico.
* Análisis semántico.
* Generación de código intermedio.
* Optimización de código intermedio.
* Generación de código destino.
* Optimización de código destino.
### Proceso de Compilación
![](ProcesoDeCompilacion.png)
#### Scanner: Análisis léxico
Se divide un programa (secuencia de caracteres) en palabras (Tokens)
#### Parser: Análisis Sintáctico
* Comprueba si la secuencia de tokens conforma a la especificación gramatical del lenguaje y genera el árbol sintáctico.
* La especificación gramatical suele representarse con una gramática independiente de contexto (*context free grammar*), que también le da forma al árbol sintáctico.
#### Análisis semántico
* El compilador trata de ver si en un programa tiene sentido analizando su árbol sintáctico.
* Un programa sin errores gramaticales no siempre es correcto, puede haber problemas de tipo.
* El compilador hace comprobaciones semánticas **Estáticas** (*Static semantic checks*)
	* Comprobación de tipos.
	* Declaración de variables antes de su uso.
	* Se usan los identificadores en contexto adecuados.
	* Comprobar argumentos.
	* Si hay un fallo en compilación, se genera un error.
* En **Tiempo de Ejecución** (*dynamic semantic checks*) se comprueba:
	* Que los valores de los arreglos estén dentro de los límites.
	* Errores aritméticos.
	* No se desreferencian los punteros si no apuntan a un objeto válido.
	* Se usan variables sin inicialización.
	* Si hay un fallo en ejecución, se levanta una **excepción**.
#### Código Intermedio
El código intermedio está cerca de la máquina pero sigue siendo fácil de manipular, para poder implementar optimizaciones.
#### Código Destino
De la forma independiente de máquina se genera ensamblador, este código específico de máquina se optimiza para explotar características de hardware específicas.
### Gramáticas independientes de contexto
Se definen categorías de construcciones del lenguaje, como por ejemplo:
* Sentencias (*Statements*).
* Expresiones (*Expressions*).
* Declaraciones (*Declarations*).
### Tipado Fuerte
Un lenguaje tiene tipado fuerte si siempre se detectan los errores de tipo:
* En tiempo de compilación o tiempo de ejecución.
* Algunos lenguajes con tipado fuerte son: Haskell, ML, Java, Ada.
* Algunos lenguajes con tipado débil: Fortran, Pascal, C/C++, Lisp.
El tipado fuerte hace que el lenguaje sea más seguro y fácil de usar sin errores, pero pontencialmente más lento por las comprobaciones dinámicas.
En algunos lenguajes algunos errores de tipo se detectan tarde, lo que los hace poco fiable.
## Gramáticas
### Gramáticas Independiente de contexto
Una gramática es un método para:
* Definir conjuntos infinitos de expresiones.
* Procesar expresiones.
consiste de:
* Símbolo inicial.
* No terminales:
* Terminales.
* Producciones.
### Gramáticas como método
* No terminales:
	* Forma adecuada de describir la composicionalidad de las expresiones.
	* No pueden formar parte de una expresión, siempre se tienen que substituir por terminales.
* Derivación: Secuencia de sustituciones que termina en una cadena de terminales.
