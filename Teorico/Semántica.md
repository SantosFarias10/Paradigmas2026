### Programa
Un **Programa** es la descripción de un proceso dinámico.
* La Sintaxis es el texto del programa.
* Y la Semántica es la cosa que hace.
### Metalenguajes
El **Metalenguaje** es el que usamos para hablar de un lenguaje de objetos. No se necesita un lenguaje para hablar de la semántica de los lenguajes de programación.

---
## Semántica
#### El Capitulo 2 del Mitchell dice:
#### Sintaxis y Semántica
Los lenguajes son sistemas que sirven de una **Forma** para comunicar un **Significado**.
Lo que tiene que ver con la Forma recibe el nombre de **Sintaxis** y lo que tiene que ver con el Significado recibe el nombre de **Semántica**. Por ejemplo, en los **Lenguajes Naturales**, como el castellano, las palabras son la Forma, y el contenido proposicional de las oraciones es el Significado.
En los **Lenguajes Formales** la Forma son **Los Programas** y el Significado es **Lo que Los Programas Hacen**.
Un lenguaje de programación se describe con su Sintaxis (que es lo que se puede escribir legalmente en ese lenguaje) y su Semántica (que efectos tiene en la maquina lo que se escribe en ese lenguaje).

#### Alcance de los Lenguajes de Programación
* ¿Que es un Algoritmo? 
	* Un **Algoritmo** es un conjunto de instrucciones bien definidas, ordenadas y finitas que permite realizar algo de forma in-ambigua mediante pasos. 
* ¿Que son las Funciones Computables?
	* Las **Funciones Computables** son la formalización de la noción intuitiva de algoritmo, en el sentido de que una función es computable si existe un algoritmo que puede hacer el trabajo de la función, o sea, dada una entrada del dominio de la función puede devolver la salida correspondiente. 
	* Este concepto se usa para hablar de computabilidad sin hacer referencia a ningún modelo concreto de computación. Cualquier definición, sin embargo, debe hacer referencia a algún modelo especifico de computación, como la maquina de Turing o lambda cálculo.

#### Sintaxis a través de Gramáticas
Nuestro objetivo con respecto a la Sintaxis de los lenguajes de programación es describir de forma compacta el conjunto de los programas validos de ese lenguaje. El Instrumento formal para describir la Sintaxis de los lenguajes de programación son las **Gramáticas Independientes de Contexto**. Describen el grueso de la forma de los lenguajes de programación.
Una gramática de ejemplo clásica es la que genera los `strings` que representan expresiones aritméticas con los cuatro operadores ($+$, $-$, $*$, $/$) y los números como operando:

![](GramaticaEjemplo.png)
![](GramaticaEjemplo2.png)
El único símbolo no terminal en esta gramática es `expresion`, que también es el símbolo inicial. Los terminales son {+, -, $*$, /, (, ), numero}, donde `numero` representa cualquier numero valido.
La primer regla (o producción) dice que una `<expresion>` se puede reescribir como (o se reemplazada por) un numero. Por lo tanto, un numero es una expresión valida. La segunda regla dice que una expresión entre paréntesis es una expresión valida, usando una definición recursiva de expresión. El resto de reglas dicen que la suma, resta, producto o división de dos expresiones también es una expresión valida.

Las gramáticas que describen los lenguajes de programación se usan para verificar la correctitud de un programa escrito en ese lenguaje, mediante un compilador. En un **Compilador**, estas limitaciones se solucionan en parte porque hay módulos de procesamiento del programa posteriores a la gramática que tratan algunos de los fenómenos que las gramáticas no pueden capturar. 
Sin embargo, también se usan las gramáticas para describir los lenguajes para **Consumo Humano**. En ese uso, uno desea que la gramática pueda expresar de forma compacta todas las propiedades relevantes del lenguaje. Para conseguirlo, se suelen usar mecanismos **ad-hoc** para aumentar su poder expresivo, como por ejemplo predicados asociados a reglas. Por ejemplo, si queremos establecer una restricción de tipos en una determinada regla, podemos hacerlo de la siguiente forma:
![](RestriccionDeTipos.png)
![](RestriccionDeTipos2.png)
En la practica, las restricciones de tipo las realiza el análisis semántico del compilador

#### Semántica Operacional vs. Lambda Calculo o Denotacional
Existen diferentes maneras de describir y manipular formalmente la Semántica de un programa. Algunas de ellas es **Lambda Calculo**, la **Semántica Denotacional** y diferentes tipos de **Semántica Operacional** .
La Semántica Operacional (de pequeños casos) describe formalmente como se llevan a cabo cada uno de los pasos de un calculo en un Sistema Computacional.
Cuando describimos la semántica de un programa mediante semántica operacional, describimos como un programa valido se interpreta como secuencia de pasos computacionales. Estas secuencias son el significado del programa.

---
### De-limitación de la semántica de los lenguajes de programación
Los programas pueden definir **Funciones Parciales**, o sea que algunos de sus valores pueden ser **Indefinidos**, o algunos de sus valores pueden ser **Errores**.
Una función es computable si hay algún programa que la computa xd, El problema es que la definición es dependiente de la implementan de un lenguaje de programación concreto, con sus limitaciones y particularidades.

## Semántica Operacional
#### El capitulo 5 de la Guía de Lectura Dice:
#### Código Estructurado vs. Código *Spaghetti*
En lenguajes como Ensamblador o Fortran es muy fácil escribir programas que resulten incomprensibles porque al que lee le cuesta entender la estructura de control del programa. A este tipo de código se le llama **Código Spaghetti**, Por ejemplo:
![](CodigoSpaghetti.png)

Un Ejemplo de Fortran:
![](CodigoSpaghettiFortran.png)
Este fragmento de Código Spaghetti podría traducirse a un lenguaje con bloques de la siguiente forma:
![](TraduccionALenguajesConBloques.png)

##### ¿Como Evitamos Este Problema?
La mayoría de lenguajes de programación modernos proporcionan alguna forma de bloque. Un bloque es una región del texto del programa con inicio y fin explícitos e inambiguos. Esta región del texto permite organizar de forma explicita la lógica del programa. Pero ademas, posibilita sucesivas abstracciones sobre el flujo de ejecución.
En primer lugar, los bloques nos permite hacer declaraciones de variables locales. Por ejemplo:
![](DeclaracionesDeVariablesLocales.png)
En esta sección hay 2 bloques. Cada bloque comienza con una llave y termina con otra llave.
La variable `x` se declara en el bloque exterior y la variable y se declara en el bloque interior. Una variable declarada dentro de un bloque se dice que es una **Variable Local** para ese bloque. Las variables que no están declaradas en un bloque, si no en algún otro bloque que lo contiene, se dice que es una **Variable Global** para el bloque mas interno. En el ejemplo, `x` es local en el bloque exterior, `y` es local para el bloque interior, y `x` es global para el bloque interior.

#### Estructura de Bloque
Los lenguajes con estructura de bloque se caracterizan por las siguientes propiedades:
* Las nuevas variables se pueden declarar en varios puntos de un programa.
* Cada declaración es visible dentro de una determinada región de texto del programa, llamada **Bloque**. Los bloques pueden ser anidados, pero no pueden superponerse parcialmente. En otras palabras, si dos bloques contienen expresiones o declaraciones en común, entonces un bloque debe estar enteramente contenida dentro del otro.
* Cuando un programa inicia la ejecución de las instrucciones contenidas en un bloque en tiempo de ejecución, se asigna memoria a las variables declaradas en ese bloque.
* Cuando un programa sale de un bloque, parte o toda la memoria asignada a las variables declaradas en ese bloque se libera.
* Un identificador de variable que no esta declarado en el bloque actual se considera global a ese bloque, y su referencia es la entidad con el mismo identificador nombre que se encuentra en el bloque mas cercano que contiene al bloque actual.

Aunque la mayoría de los lenguajes de programación de propósito general modernos son estructurados por bloques, muchos lenguajes importantes no explotan todas las capacidades que permite la estructura por bloques. Por ejemplo, `C` y `C++` no permiten declaraciones de funciones locales dentro de los bloques anidados, por lo que no se ocupan de las cuestiones de aplicación asociados con el entorno de las funciones de los bloques anidados.

##### Como se Manejan en Memoria Tres Clases de Variables:
* **Variables Locales** se almacenan en la pila de ejecución, en el ***Activation Record***.
* **Parámetros de Función** se almacenan en el Activation Record asociado con el bloque.
* **Variables Globales** se declaran en algún bloque que contiene al bloque actual y por lo tanto hay que acceder a ellos desde un Activation Record que se colocó en la pila de ejecución antes del bloque actual.

La mayoría de complicaciones y diferencias entre los lenguajes están relacionadas con el acceso a las Variables Globales, a consecuencia de la estructura de la pila: Para dejar mas a mano las variables locales, una variable global puede quedar enterrada en la pila bajo un numero arbitrario de Activation Record.

#### Activation Record
Para describir la semántica de los lenguajes de programación usamos una semántica operacional, o sea, describimos los efectos de las diferentes expresiones del lenguaje sobre una maquina. Por eso usamos un modelo simplificado de la computadora
![](ModeloSimplificadoDeLaComputadora.png)
Vemos que en este modelo de maquina separa la memoria en la que se guarda el código de la memoria en la que se guardan los datos. Usamos dos variables para saber a que parte de la memoria necesitamos acceder en cada momento de la ejecución del programa: El ***Program Counter*** (**PC**) y el **Puntero de Entorno**. El PC es una dirección en la parte de la memoria donde se guarda el código, en concreto, la dirección donde se encuentra la instrucción de programa que se esta ejecutando actualmente. Normalmente se incrementa después de ejecutar cada instrucción.
El Puntero de Entorno nos sirve para saber cuales son los valores que se asignan a las variables que se están usando en una parte determinada del código. En los lenguajes no estructurados por bloques, la memoria de datos es NO estructurada, todo el espacio es ***Heap***, y por lo tanto los valores que se asignan a las variables son visibles desde cualquier parte del código. En cambio, en los lenguajes estructurados por bloques, se guardan en el ***Heap*** algunos datos para los que necesitamos persistencia (como por ejemplos las variables globales), en los registros se guardan algunos datos para los que queremos que sean de rápido acceso, y en **Pila de Ejecución** o ***Stack*** se guardan los datos estructurados en forma de pila xd, lo cual hace posible que se instancien variables locales y argumentos distintos para cada llamada de función. Esto posibilita que las funciones puedan ser mas abstractas y que se puedan hacer llamadas recursivas.

##### ¿Como Funcionan el Stack?
Cuando el programa entra en un nuevo bloque, se agrega a la pila una estructura de datos que se llama  ***Activation Record*** o **Marca de Pila** (***Stack Frame***), que contiene el espacio para las variables locales declaradas en el bloque, normalmente, por la parte de arriba de la pila. Entonces, el puntero de entorno apunta al nuevo Activation Record. Cuando el programa sale del bloque, se retira el Activation Record de la pila y el puntero de entorno se restablece a su ubicación anterior, o sea, al puntero de entorno correspondiente a la función que llamaba a la función que ha sido desapilada. El Activation Record que se apila mas recientemente es el primero en ser desapilado.

##### ¿Que contiene un Activation Record?
![](EsquemaDeLosContenidosDeUnActivationRecord.png)
En la figura se puede ver la información que hay en un Activation Record. Se ve que en esta arquitectura hay, ademas de espacio para variables locales, también espacio para los argumentos que puede recibir una función, que funcionan como variables locales con respecto a la función pero que son escritos por la función que la llama. También se encuentra un espacio para guardar resultados intermedios, en el caso de que sea necesario. Ademas hay dos direcciones de memoria donde se guardan datos importantes: 
* Una, el ***Control Link***, contiene el que sera el puntero de entorno cuando se desapile el Activation Record actual, o sea, el puntero de entorno del Activation Record correspondiente a la función que llamaba a la función del Activation Record actual. 
* La otra dirección de memoria distinguida es la llamada **Dirección de Retorno**, que es donde se va a guardar el resultado de la ejecución de la función, si es que lo hay.
Los Activation Record pueden tener tamaños variables, por lo que, las operaciones que ponen y sacan activation records de la pila guardan también en cada activation record una variable con la dirección de memoria que corresponde a la parte superior del registro de activación anterior. Esta variable o puntero es la que se le llama ***Control Link***, porque es el enlace que se sigue cuando se devuelve el control a la instrucción en el bloque precedente.

### Detalle de Ejecución de un Activation Record
Primero describiremos el comportamiento de los bloques ***In Line***. Un bloque *In Line* es un bloque que no es el cuerpo de una función o procedimiento.
Cuando un programa en ejecución entra a un bloque *in line*, se asigna espacio en memoria para las variables que se declaran en el bloque, un conjunto de posiciones de memoria en la pila (***Activation Record***).
Vemos como funciona con el siguiente ejemplo de código:
```c
{ int x=0;
  int y=x+1;
      { int z=(x+y)*(x-y);
      };
}
```
Este código estará escrito en código maquina, traducido por el compilador, y guardado en la parte de la memoria en la que se guarda el programa. El PC recorrerá cada una de sus instrucciones, y realizara las acciones que indiquen en memoria, tal como sigue.
Cuando se introduce el bloque exterior, se pone en la parte superior de la pila un Activation Record que contiene espacio para las variables `x` e `y`. A continuación se ejecutan las declaraciones que establecen valores de `x` e `y`, y los valores de `x` e `y` se almacenan en las direcciones de memoria del Activation Record.
Cuando se introduce el bloque interior, se apila un nuevo Activation Record que son direcciones en memoria para `z`. Después de establecer el valor de `z`, el Activation Record que contiene este valor se retira de la pila y el puntero de entorno deja de referirse a este Activation Record y pasa a referirse al Activation Record que esta ahora en la cabeza de la pila. Por ultimo, cuando se sale del bloque exterior, el Activation Record que contiene `x` e `y` se retira de la pila y el puntero de entorno se refiere al Activation Record que este en  la cabeza de la pila o a ninguno si no hay mas Activation Record.
![](SecuenciaActivationRecord.png)
![](ControlLinkYPuunteroDeEntorno.png)
El numero de direcciones en memoria que va a necesitar un Activation Record en tiempo de ejecución depende del numero de variables declaradas en el bloque y sus tipos. Debido a que estas cantidades se conocen en tiempo de compilación, el compilador puede determinar el formato de cada Activation Record y almacenar esta información como parte del código compilado.

---
