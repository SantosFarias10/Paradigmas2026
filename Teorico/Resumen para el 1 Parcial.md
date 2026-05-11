Resumen saca directamente de la guía de lectura.
# Temas
* Estructura de las variables (L-valor, R-valor, identificador textual).
* Pasaje de parámetros.
* Alcance estático (léxico) y alcance dinámico.
* Excepciones.
* Tipado (seguridad, sobrecarga y polimorfismo).
* Programación declarativa, características de las componentes declarativas.
* Programación asistida por IA.
# Temas que NO entran
* Gramáticas
* Compiladores.
* Algoritmos de Inferencia de Tipos.
* Recolección de Basura.
# Capitulo 2: Qué es y qué puede hacer un lenguaje de programación
## Sintaxis y Semántica
Los lenguajes son sistemas que se sirven de una **Forma** para comunicar un **Significado**. Lo que tiene que ver con la forma recibe el nombre de **Sintaxis** y lo que tiene que ver con el significado recibe el nombre de **Semántica**.
En los lenguajes de programación (*lenguajes formales*), la forma son los **Programas** y el significado es **Lo Que Los Programas Hacen**.
Un lenguaje de programación se describe con su sintaxis (qué es lo que se puede escribir legalmente en ese lenguaje) y su semántica (qué efectos tiene en la máquina lo que se escribe en ese lenguaje).
## Alcance de los lenguajes de programación
(Mitchell 2) Leer
Para "controla el comportamiento físico y lógico de una máquina" usamos **Algoritmos** un conjunto de instrucciones bien definidas, ordenadas y finitas que permite realizar algo de forma inambigua mediante pasos.
Las **Funciones Computables** son la formalización de la noción intuitiva de algoritmo, en el sentido de que una función es computable si existe un algoritmo que pueda hacer el trabajo de la función.
El concepto de **Función Computable** es intuitiva, se usa para hablar de computabilidad sin hacer referencia a ningún modelo concreto ed computación. Cualquier definición, sin embargo, debe hacer referencia a algún modelo específico de computación, como por ejemplo la Máquina de Turing, las Funciones µ recursivas, el Lambda Cálculo o las Máquinas de Registro.
## Sintaxis a través de gramáticas
Nuestro objetivo con respecto a la sintaxis de los lenguajes de programación es describir de forma compacta e inambigua el conjunto de los programas válidos en ese lenguaje. El **instrumento formal** básico **para describir la sintaxis** de los lenguajes de programación son las **Gramáticas Independiente de Contexto**.
Una gramática de ejemplo es la que genera los `String` que representan expresiones aritméticas con los cuatro operadores $+, -, *, /$ y los números como operando:
```
<expresion> --> numero
<expresion> --> ( <espresion> )
<expresion> --> <espresion> + <espresion>
<expresion> --> <espresion> - <espresion>
<expresion> --> <espresion> * <espresion>
<expresion> --> <espresion> / <espresion>
```
El único símbolo no terminal en esta gramática es `expresion`, que también es el símbolo inicial. Los terminales son ${+, -, *, /, (, ), numero}$, donde $numero$ representa cualquier número válido.
* La primera regla (o producción) dice que una `<expresion>` se puede reescribir como (o ser reemplazada por) un número. Por lo tanto, un número es una expresión válida. 
* La segunda regla dice que una expresión entre paréntesis es una expresión válida, usando una definición recursiva de expresión.
* El resto de reglas dicen que la suma, resta, producto o división de dos expresiones también son expresiones válidas.
## Semántica operacional vs. lambda cálculo o de-notacional
Hay diferentes maneras de describir y manipular formalmente la semántica de un programa. Algunas de ellas son el **Lambda Cálculo** de Church, la **Semántica de-notacional** de Strachey & Scott y diferentes tipos de **Semántica Operacional**. Nosotros vamos a usar solo un tipo de semántica operacional.
La Semántica Operacional (de pequeños pasos) describe formalmente cómo se llevan a cabo cada uno de los pasos de un cálculo en un sistema computacional.
Cuando describimos la semántica de un programa mediante semánticas operacional, describimos cómo un programa válido se interpreta como secuencias de pasos computacionales. Estas secuencias son el significado del programa.

---
# Capitulo 3: Como funcionan los lenguajes de programación
## Estructura de un compilador
(Mitchell 4.1.1) Leer
## Estructura de datos de bajo nivel
### Variables
Una **Variable** esta formada por una ubicación de memoria y un identificador asociado a esa ubicación. Esa ubicación contiene un valor, que es una cantidad o información conocida o desconocida. Esta separación entre nombre y contenido permite que el nombre sea usado independientemente de la información exacta que representa.
Los compiladores reemplazan los nombres simbólicos de las variables con la real ubicación de los datos. Mientras que el nombre, tipo y ubicación de una variable permanecen fijos. Los datos almacenados en la ubicación puede ser cambiados durante la ejecución.
Diferentes identificadores del código pueden referirse a una misma ubicación en memoria, lo cual se conoce como ***Aliasing***.
* La ubicación de una variable se llama **L-valor**.
* Y el valor almacenado en una ubicación de memoria se llama **R-valor** de la variable.
---
# Capitulo 4: Tipos de datos
(Mitchell 6.1, 6.2, 6.3, 6.4 y 13.3) Leer

---
# Capitulo 5: Estructura de bloques
(Mitchell 8.1) Leer
## Código estructurado vs. código *spaghetti*
En lenguajes como ensamblador o Fortran es muy fácil escribir programas que resulten incomprensibles porque al que lee le cuesta entender la estructura de control del programa. A este tipo de código se le llama **Código Spaghetti**. Por ejemplo:
```
10  IF (X .GT. 0.000001) GO TO 20
    X = -X
11  Y = X*X - SIN(Y)/X+1
    IF (X .LT. 0.000001) GO TO 50
20  IF (X*Y .LT. 0.000001) GO TO 30
	X = X-Y-Y
30  X = X+Y
	...
50  CONTINUE
	X = A
	Y = B-A + C*C
	GO TO 11
```
Para evitarlo, la mayoría de los lenguajes de programación modernos proporcionan alguna forma de bloque. Un bloque es una región del texto del programa con inicio y fin explícitos e inambiguos. Esta región del texto permite organizar de forma explícita la lógica del programa. Pero además, posibilita sucesivas abstracciones sobre el flujo de ejecución.
1. Los bloques nos permite hacer declaraciones de variables locales. Por ejemplo:
```
{ int x = 2;
	{ int y = 3;
		x = y + 2;
	}
}
```
En esta sección hay 2 bloques. Cada bloque comienza con una llave izquierda y termina con una llave derecha.
La variable `x` se declara en el bloque exterior y la variable `y` se declara en el bloque interior. Una variable declarada dentro de un bloque se dice que es una **Variable Local** para ese bloque. Las variables que no están declaradas en un bloque, si no en algún otro bloque que lo contiene, se dice que es una **Variable Global** para el bloque más interno. En el ejemplo, `x` es local en el bloque exterior, `y` es local para el bloque interior, y `x` es global para el bloque interior.
## Estructura de bloque
(Mitchell 7.1) Leer
Los lenguajes con estructura de bloque se caracterizan por tener las siguientes propiedades:
* Las nuevas variables se pueden declarar en varios puntos de un programa.
* Cada declaración es visible dentro de una determinada region de texto del programa, llamada bloque. Los bloques pueden ser anidados, pero no pueden superponerse parcialmente. O sea, si dos bloques contiene expresiones o declaraciones en común, entonces un bloque debe estar enteramente contenida dentro del otro.
* Cuando un programa inicia la ejecución de las instrucciones contenidas en un bloque en tiempo de ejecución, se asigna memoria a las variables declaradas en ese bloque.
* Cuando un programa sale de un bloque, parte o toda la memoria asignada a las variables declaradas en ese bloque se libera.
* Un identificador de variable que no está declarado en el bloque actual se considera a ese bloque, y su referencia es la entidad con el mismo identificador nombre que se encuentra en el bloque mas cercano que contiene al bloque actual.

**Variables Locales**: Se almacenan en la pila de ejecución, en el activation record asociado al bloque.
**Parámetros de Función**: También se almacenan en el activation record asociado con el bloque.
**Variables Globales**: Se declaran en algún bloque actual y por lo tanto hay que acceder a ellos desde un activation record que se colocó en la pila de ejecución antes del bloque actual.
## *Activation records*
Para describir la semántica de los lenguajes de programación usamos una semántica operacional, o sea, describimos los efectos de las diferentes expresiones del lenguaje sobre una máquina. Para eso usamos un modelo simplificado de la computadora:
![Modelo Simplificado de la Computadora, sin los registros y el heap](ModeloSimplificadoDeLaComputadora.png)
Vemos como el modelo de máquina separa la memoria en la que se guarda el código de la memoria en la que se guardan los datos. Usamos dos variables para saber a qué parte de la memoria necesitamos acceder en cada momento de la ejecución del programa: el **Contador del Programa** (PC, *Program Counter*) y el **Puntero de Entorno**. El PC es una dirección en la parte de la memoria donde se guarda el código, en especifico, la dirección donde encuentra la instrucción de programa que se está ejecutando actualmente.
El Puntero de Entorno nos sirve para saber cuáles son los valores que se asignan a las variables que se están usando en una parte determinada del código. En los **Lenguajes No Estructurados** por bloques, la memoria de datos es No Estructurada, todo el espacio es *heap*, y por lo tanto los valores que se asignan a las variables son visibles desde cualquier parte del código. En cambio, en los **Lenguajes Estructurados** por bloques, se guardan en el ***heap*** algunos datos para los  que necesitamos persistencia (por ejemplo las variables globales), en los registros guardamos algunos datos para los que queremos rápido acceso, y en la pila de ejecución o ***Stack*** se guardan los datos estructurados en forma de pila, lo cual hace posible que se instancien variables locales y argumentos distintos para cada llamada de función. Esto posibilita que las funciones puedan ser más abstractas y que se puedan hacer llamadas recursivas.
### Como funciona el stack
Cuando el programa entra en un nuevo bloque, se agrega a la pila una estructura de datos que se llama ***Activation Record***, que contiene el espacio para las variables locales declaradas en el bloque, normalmente, por la parte de arriba de la pila. Entonces, el puntero de entorno apunta al nuevo activation record. Cuando el programa sale del bloque, se retira el activation record de la pila y el puntero de entorno se restablece a su ubicación anterior, o sea, al puntero de entorno correspondiente a la función que llamaba a la función que ha sido desapilada.

![](EsquemaDeLosContenidosDeUnActivationRecord.png)
En la figura se observa la información que hay en un activation record. Vemos que hay, además de espacio para variables locales, también espacio para los argumentos que puede recibir una función, que funcionan como variables locales con respecto a la función pero que son escritos por la función que la llama. También encontramos espacio para guardar resultados intermedios, en el caso de que sea necesario. Podemos ver que hay dos direcciones de memoria donde se guardan datos importantes: Una, el ***Control Link***, contiene el que será el puntero de entorno cuando se desapile el activation record actual, o sea, el puntero del activation record actual. La otra dirección de memoria distinguida es la llamada **Dirección de Retorno**, que es donde se va a guardar el resultado de la ejecución de la función, si es que lo hay.
Los activation records pueden tener tamaño variable, por lo que, las operaciones que ponen y sacan activation records de la pila guardan también en cada activation record una variable con la dirección anterior. Esta variable o puntero (porque apunta a una dirección de memoria) se llama ***Control Link***, porque es el enlace que se sigue cuando se devuelve el control a la instrucción en el bloque precedente.
### Detalles de ejecución de un activation record
(Mitchell 7.2) Leer
Primero describimos el comportamiento de los bloques ***In Line***, ya que estos son más simples que los bloques asociados a las llamadas de función. Un bloque *in line* es un bloque que no es el cuerpo de una función o procedimiento.
Cuando un programa en ejecución entra en un bloque *in line*, se asigna espacio en memoria para las variables que se declaran en el bloque, un conjunto de posiciones de memoria en la pila, el activation record.
Por ejemplo:
```
{   int x=0;
	int y=x+1;
		{ int z=(x+y)*(x-y);
		};
}
```
Este código estará escrito en código de máquina, traducido por el compilador, y guardado en la parte de la memoria en la que se guarda el programa. El PC recorrerá cada una de sus instrucciones, y realizará las acciones que indiquen en memoria tal como sigue.
Cuando se introduce el bloque exterior, se pone en la parte superior de la pila un activation record que contiene espacio para las variables `x` e `y`. Luego se ejecutan las declaraciones que establecen valores de `x` e `y`, y los valores de `x` e `y` se almacenan en las direcciones de memoria del activation record.
Cuando se introduce el bloque interior, se apila un nuevo activation record con direcciones en memoria para `z`. Después de establecer el valor de `z`, el activation record que contiene este valor se retira de la pila y el puntero de torno deja de referirse a este activation record y pasa a referirse al activation record que está ahora en la cabeza de la pila. Por último, cuando se sale del bloque exterior, el activation record que esté en la cabeza de la pila o a ninguno si no hay más activation record en la pila.
Se puede visualizar esta ejecución usando una secuencia de gráficos que describen la secuencia de estados por los que pasa la pila:
![](SecuenciaActivationRecord.png)
Un activation record también puede contener espacio para resultado intermedios. Estos son valores que no reciben un identificador de variable explícito en el código, pero se guardan temporalmente para facilitar algún cálculo.
Por ejemplo, el activation record para este bloque,
```
{ int z = (x + y) * (x - y); }
```
puede tener espacio para asignar valor a la variable `z` pero también para los valores `x+y` y `x-y`, porque los valores de estas subexpresiones pueden tener que ser evaluados y almacenados en algún lugar antes de multiplicarse. Si hacemos zoom en el tercer estado de la pila, veremos algo como
![](ControlLinkYPuunteroDeEntorno.png)
con espacio para valores temporales.

---
# Capitulo 6: Control de la ejecución
## Funciones y procedimientos
(Mitchell 7.3) Leer
## Pasaje de parámetros
(Mitchell 7.3.2) Leer
Los nombres de los parámetros que se usan cuando se declara una función se llaman **Parámetros Formales**. Cuando se llama a una función, se usan los llamados **Parámetros Reales**, que son valores efectivos de los parámetros formales en una llamada concreta a la función. Por ejemplo:
```
proc p (int x, int y) {
	if (x > y) then ... else ... ;
	...
	x := y*2 + 3;
	...
}
p (z, 4*z+1);
```
Los identificadores  `x` e `y` son parámetros formales del procedimiento `p`. Los parámetros reales en la llamada `p` son `z`  y `4*z+1`.
La forma en que los parámetros reales se evalúan y se pasan a la función depende del lenguaje de programación y el tipo de mecanismo de **Pasaje de Parámetros** que utiliza. Los distintos mecanismos de pasaje de parámetros se diferencian por el momento en el que se evalúa el parámetro real (**Evaluación Estricta**, en el momento de pasar el parámetro, o **Perezosa**, cuando se necesita) y por la ubicación de memoria donde se almacena el valor del parámetro (la misma ubicación del bloque que hace la llamada a función o una nueva ubicación especifica de la función).
La mayoría de los lenguajes de programación evalúan los parámetros reales de forma estricta, (Una de las razones para que un lenguaje podría querer retrasar la evaluación de un parámetro real es que la evaluación podría ser costosa). Entre los mecanismos que evalúan el parámetro real antes de ejecutar el cuerpo de la función, loas más comunes son el **Pasaje por Referencia** y el **Pasaje por Valor**. Estos mecanismos se diferencian entre ellos por la ubicación de memoria donde se almacena el valor del parámetro. El **Pasaje por Referencia pasa el L-valor**, o seam la ubicación en memoria, del parámetro real, mientras que el **Pasaje por Valor pasa el R-valor**, o sea, el valor que hay en la ubicación de memoria.
### Importancia de la diferencia del pasaje por valor y por referencia
* **Efectos Secundarios**: Las asignaciones de valor en el interior del cuerpo de la función pueden tener diferentes efectos.
* ***Aliasing***: El *aliasing* ocurre cuando dos identificadores de variables se refieren al mismo objeto o ubicación. Esto puede ocurrir cuando dos parámetros se pasan por referencia o un parámetro pasado por referencia tiene la misma ubicación que la variable global del procedimiento.
* **Eficiencia**: Pasar por valor puede ser ineficaz para grandes estructuras de datos, si hay que copiar ese valor. Pasar por referencia puede ser menos eficiente que pasar por valor pequeñas estructuras que se adapten bien en la pila, ya que cuando los parámetros se pasa por referencia, debemos resolver la referencia de un puntero  para obtener su valor.
### Semántica de pasaje por valor
El pasaje por valor es la estrategia de evaluación más común en los lenguajes de programación. Es una forma estricta de pasaje de parámetros, o sea, que el argumento se evalúa, y el valor que se obtiene (su R-valor) se liga a una variable local de la función, en general copiando el valor a una nueva ubicación de memoria en el activation record de la llamada a función. El valor que se encuentra en el activation record que llama a la función no es modificado en ningún momento.
### Semántica de pasaje por referencia
En el pasaje por referencia se liga el L-valor del parámetro real al parámetro formal. En este caso se tiene un único valor referenciado (o apuntado) desde dos puntos diferentes, el programa principal y la función a la que se le pasa el argumento, por lo que cualquier acción sobre el parámetro se realiza sobre la misma posición de memoria (*aliasing*).
Esto quiere decir que la función es llamada puede modificar la variable con efecto en el bloque que llama a la función. De esta manera, el pasaje por referencia se puede usar como un canal de comunicación entre la función que llama y la que es llamada, pero a la vez resulta más difícil seguir el rastro de los efectos de una llamada a función, y se pueden introducir errores sutiles.
### Sutilezas entre pasaje por valor y pasaje por referencia
En los lenguajes funcionales típicamente no hay diferencia semántica entre pasaje por valor y pasaje por referencia, porque sus estructuras de datos son inmutables, por lo que es imposible que una función pueda modificar sus argumentos. Por esta razón normalmente se describen como de pasaje por valor, a pesar de que las implementaciones utilizan normalmente pasaje por referencia internamente porque es más eficiente.
En algunos casos de pasaje por valor el valor que se pasa no es el habitual de la variable, sino una referencia a una ubicación de memoria. El efecto es que lo que sintácticamente parece llamada por valor puede terminar los efectos de un pasaje por referencia.
Hay varias razones por las que se puede pasar una referencia. Una razón puede ser que el lenguaje no permita el pasaje por valor de estructuras de datos complejas, y se pase una referencia para preservar esa estructura. También se usa de forma sistemática en lenguajes con orientación a objetos, lo que se suele llamar *call-by-sharing* (llamado compartido), *call-by-object* (llamada por objecto) o *call-by.object-sharing* (llamada mediante compartición de objetos).
* En *call-by-sharing*, el pasaje de parámetros es por valor, pero se pasa una referencia a un objeto. Por lo que, se hace una copia local del argumento que se pasa, pero esa copia es una referencia a un objeto que es visible tanto desde la función llamada como desde la función que llama.
	* La semántica de *call-by-sharing* difiere del pasaje de referencia en que las asignaciones a los argumentos dentro de la función no son visibles a la función que llama, por lo que, por ejemplo, si se pasa una variable, no es posible simular una asignación en esa variable en el alcance de la función que llama. Por lo tanto, no se puede hacer cambios a la referencia que se ha pasado por valor.
* Sin embargo, a través de esa referencia de la que la función llamada ha hecho una copia, ambas funciones, la llamada y la que llama, tienen acceso al mismo objeto, no se hace ninguna copia. Si ese objeto es mutable, los cambios que se hagan el objeto dentro de la función llamada son visibles para la función que llama, a diferencia del pasaje por valor.
### Semántica de pasaje por valor-resultado
Es un tipo de pasaje de parámetros con evaluación estricta (NO perezosa) poco usado en los lenguajes de programación actuales. Se basa en que dentro de la función se trabaja como si los argumentos hubieran sido pasados por valor, pero al acabar la función los valores que tengan los argumentos serán copiados a la ubicación de memoria en la que se ubicaba el valor copiado inicialmente.
### Pasaje de parámetros no estricto
En la evaluación perezosa, los argumentos de una función no se evalúan a menos que se utilicen efectivamente en la evaluación del cuerpo de la función.
#### Pasaje por nombre
En el pasaje *call-by-name*, los argumentos de una función no se evalúan antes de la llamada a la función, sino que se sustituyen sus nombres directamente en el cuerpo de la función y luego se dejan pasar ser evaluados cada vez que aparecen en la función. Si un argumento no se utiliza en el cuerpo de la función, el argumento no se evalúa; si se utiliza varias veces, se re-evalúa cada vez que aparece.
En ocasiones, *call-by-name* tiene ventajas sobre pasaje por valor, por ejemplo, si el argumento no siempre se usa, porque ahorramos la evaluación del argumento. Si el argumento es un cálculo no termina, la ventaja es enorme. Sin embargo, cuando se utiliza el argumento en la función, el pasaje por nombre es a menudo más lento.
#### Pasaje por necesidad
El pasaje por necesidad *call-by-need*, es una versión memorizada del pasaje por nombre donde, si se evalúa el argumento de la función, ese valor se almacena para usos posteriores. En un entorno de "puro" (sin efectos secundarios), esto produce los mismos resultados que el pasaje por nombre; cuando el argumento de la función se utiliza dos o más veces, el pasaje por necesidad es casi siempre más rápido.
## Alcance estático vs. alcance dinámico
(Mitchell 7.3.3) Leer
Si un identificador `x` aparece en el cuerpo de una función, pero `x` no se declara dentro de la función, entonces el valor de `x` depende de alguna declaración fuera de la función. En esta situación, la ubicación de `x` está fuera del activation record para la función y es una **Variable Libre** o **Variable Global** respecto a esa función. Debido a que  `x` ha sido declarada en otro bloque, el acceso a una `x` libre o global consiste en encontrar el activation record pertinente en la pila.
Hay dos políticas principales para buscar la declaración adecuada de un identificador de variable libre:
* **Alcance Estático** (alcance léxico). Un identificador global se refiere al identificador con ese nombre que se encuentre en el bloque contenedor más cercano en el texto del programa.
* **Alcance Dinámico**. Un identificador se refiere al identificador que se encuentre en el activation record más reciente en la pila de ejecución.
Por lo tanto, la búsqueda de una declaración usando la política de alcance estático utiliza la relación estática e inmutable entre bloques en el texto del programa. Por el contrario, el alcance dinámico utiliza la secuencia efectiva de las llamadas que se ejecutan en la ejecución del programa, que es dinámica y puede cambiar.
### Naturalidad y overhead del alcance estático
El alcance estático es la semántica más intuitiva para la mayor parte de programadores, por esta razón los lenguajes de programación proveen los mecanismos necesarios para poder mantener esta semántica. Hay diferentes opciones para hacerlo.
La opción más sencilla es prohibir los contextos en los que el alcance estático daría un resultado distinto al alcance dinámico. Esta es la política que implementan las versiones mayoritarias de C. en las que no se puede definir una función dentro de un bloque anidado u otra función, sino siempre se tienen que definir en el nivel más bajo de anidación del texto del programa.
Sin embargo, en la mayoría de lenguajes se usan *access link* para mantener la semántica de alcance estático.
un ***Access Link*** es una dirección de memoria que se guarda en el activation record de una función y que apunta el activation record del bloque más cercano que lo contiene en el texto del programa. Para los bloques anidados in-line esta dirección de memoria es redundante con el control link, ya que el bloque más cercano que lo contiene siempre se corresponde con el activation record que se ha apilado inmediatamente antes.
Para las funciones, sin embargo, el bloque más cercano que la contiene viene determinado por el lugar donde se declara la función. Debido a que el punto de declaración a menudo es diferente del punto en el que se llama a una función, el access link generalmente apunta a un activation record diferente al enlace de control.
## Recursión a la cola
(Mitchell 7.2.4)
En este apartado nos fijamos en una optimization del compilador que se llama eliminación de la **Recursión a la Cola**. Para las funciones recursivas a la cola, que describimos a continuación, se puede reutilizar un activation record para una llamada recursiva a la función. Esto reduce la cantidad de espacio de la pila que usa una función recursiva, y evita llegar a problemas por límites de hardware como el llamado *stack overflow*, en el que la ejecución de un programa requiere más espacio del que hay disponible en la pila.
El principal concepto de lenguajes de programación que necesitamos entender es el concepto de la **Llamada a la cola**. Supongamos que la función `f` llama a la función `g`. `f` y `g` podrían ser diferentes funciones o la misma, haciendo una llamada recursiva. Una llamada a `f` en el cuerpo de `g` es una llamada de la cola si `g` devuelve el resultado de la llamada `f` sin ningún cálculo adicional. Por ejemplo, en la función:
```
fun g(x) = if (x = 0) then f(x) else f(x) * 2
```
la primera llamada a `f` en el cuerpo de `g` es una llamada de la cola, ya que el valor de retorno de `g` es exactamente el valor de retorno de la llamada a `f`. La segunda llamada a `f` en el cuerpo de `g` no es una llamada de la cola ya que `g` realiza un cálculo que involucra el valor de retorno de `f` antes de que `g` retorne.
Una función `f` es recursiva de cola si todas las llamadas recursivas en el cuerpo de `f` son llamadas a la cola a `f`.
Por ejemplo la función recursiva a la cola que calcula el factorial:
```
fun factcola(n, a) = if (n <= 1) then a else factcola(n-1, n * a);
```
Para cualquier entero positivo `n`, `factcola(n, a)` devuelve `n!`. Podemos ver que `factcola` es una función recursiva de cola porque la única llamada recursiva en el cuerpo de `factcola` es una llamada a la cola.
La ventaja de la recursion de cola es que podemos utilizar el mismo activation record para todas las llamadas recursivas. Consideremos la llamada `factcola(3, 1)` vemos las partes de cada activation record en el cómputo que son relevantes para la discusión.
![](factcola.png)
* Estado de la pila después de tres llamadas a `factcola` sin Optimización del compilador.
## Alto Orden
¿Entra?
## Excepciones
(Mitchell 8.2)
Las **Excepciones** proporcionan una forma estructurada de salto que se pueden utilizar para salir de una construcción tal como un bloque o llamada a función. El nombre de excepción sugiere que las excepciones deben ser utilizados en circunstancias excepcionales. Sin embargo, es muy habitual usarlas de formas muy pocas excepcionales, como por ejemplo:
* Salir de un bloque o llamada de función.
* Pasar datos como parte de un salto.
* Volver a un punto de programa fijado para continuar la computación.
Además de saltar de un punto a otro del programa, también se asocia con las excepciones parte del manejo de memoria. Más en concreto, se pueden desapilar activation record que ya resulten innecesarios como resultado del salto.
Todo mecanismo de excepción incluye dos construcciones lingüísticas:
* **Levantar**, ***Rise*** o ***Throw***, una construcción para ejecutar una excepción, que corta parte del cómputo actual y provoca un salto (transferencia de control).
* un **Controlador**, ***Handle*** o ***Catch***, un mecanismo de control, que permite ciertas declaraciones, expresiones o llamadas  a función provistas de código para responder a las excepciones que se lanzan la ejecución.
Hay varias razones por las que las excepciones se han convertido en construcciones muy aceptadas en la mayor parte de lenguajes. Muchos lenguajes no tienen ningún otro mecanismo limpio para saltar fuera de una llamada de función, por ejemplo, abortando la llamada. En lugar de utilizar instrucciones del tipo `go to`, que permiten crear código totalmente desestructurado, muchos programadores prefieren usar excepciones, que se pueden utilizar para saltar sólo hacia la parte del programa que ya se ha cargado en la pila, no hacia alguna parte del programa que no se ha ejecutado todavía.
Las excepciones también permiten a un programador pasar datos como parte del salto, lo cual resulta muy útil si el programa trata de recuperarse de algún tipo de condición de error. Las excepciones proporcionan un método dinámico y útil de determinar hasta donde llega un salto. Si se declara más de un mecanismo de control, se establece cuál es el controlador adecuado en cada caso siguiendo una semántica de alcance dinámico, lo cual no sería fácil de lograr con otras formas de saltos de control.

---
# Practico
##  Confusiones con asistentes de programación
### R-valor y L-valor
![](ConfusionesConAsistentesDeProgramacion.png)Los errores que encontramos en la explicación de la IA es que primero nos dice se pasa la **referencia** del L-valor a la función `increment`, y luego dice que recibe una **copia** de la variable original `a`. No queda claro si es una referencia o una copia.

### Tipado seguro vs. inseguro
#### ¿Cuál de estas dos variantes del mismo código en TypeScript tiene inseguridades de tipos?
Analizar ambos fragmentos y marcar cada afirmación como Verdadero o Falso
##### Variante A
```TypeScript
function processRequest(handler: (data: any) => any, data: any): any {
  return handler(data);
}

  

function login(data: any): any {
  if (data.username && data.password) {
    return { token: "abc123", role: "user" };
  }
  return null;
}

  

function pay(data: any): any {
  if (data.token && data.amount) {
    return { status: "ok" };
  }
  return { status: "error" };
}

  

// Uso
const authResult = processRequest(login, { username: "u", password: "p" });
const paymentResult = processRequest(pay, authResult);
```
##### Variante B
```TypeScript
type AuthRequest = {
  username: string;
  password: string;
};
  
type AuthResponse = {
  token: string;
  role: string;
};

type PaymentRequest = {
  token: string;
  amount: number;
};

type PaymentResponse = 
  status: "ok" | "error";
};

function login(data: AuthRequest): AuthResponse | null {
  if (data.username && data.password) {
    return { token: "abc123", role: "user" };
  }
  return null;
} 

function pay(data: PaymentRequest): PaymentResponse {
  if (data.token && data.amount > 0) {
    return { status: "ok" };
  }
  return { status: "error" };
}
```
1. La **Variante A** presenta inseguridades de tipos.
	* Verdadero, ya que al tener todas las variables en `any` esto produce que sea mas impreciso.
2. El uso de `any` en la **Variante A** impide que el compilador detecte comportamientos inesperados en funciones.  
	* Verdadero, por lo que ya explique xd.
3. En la **Variante A**, `authResult` siempre cumple el contrato esperado por `pay`.
	* Verdadero, Why?
4. La **Variante B** define contratos explícitos entre componentes.
	* Verdadero, Why?
5. En la **Variante B**, el compilador puede detectar si falta la propiedad `amount`.
	* Verdadero, ya que al definir el tipo `PaymentRequest` es mas fácil para el compilador verificar que no haya errores.
6. La **Variante A** es más segura porque es más flexible.
	* Falso, ya que es insegura por lo que es mas flexible. A cuanta mas seguridad menos flexible es el código.
7. En la **Variante A**, errores de tipo pueden aparecer solo en tiempo de ejecución.
	* Verdadero, Why?
8. La **Variante B** elimina completamente cualquier posible error lógico.
	* Falso, Why?
9. En la **Variante A**, los componentes están acoplados mediante estructuras implícitas no tipadas.
	* Verdadero, Why?
10. La **Variante B** mejora la mantenibilidad del código.
	* Verdadero, xd.
### Pasaje por nombre
```scala
def doble(x: => Int): Int = {
	x + x
}

var i = 1

val resultado = doble({ i += 1; i})
println(resultado)
```
Encuentre los fallos a la siguiente explicación:
"El parámetro `x` se pasa por nombre, lo que significa que se evalúa una sola vez cuando entra a la función `doble`, y luego ese valor se reutiliza. En este caso, el bloque `{ i += 1; i }` se ejecuta al inicio, incrementando `i` a 2. Luego, `x + x` equivale a `2 + 2`, por lo que el `resultado` es 4.
Además, como `x` es un parámetro, su valor queda fijado dentro de la función, evitando efectos secundarios adicionales. Por eso, `i` solo se incrementa una vez."
#### Explicación
Al pasar por nombre nos quedaría los activation record
```
| doble |clausura|
|   i   |   1    |

=> se llama doble({ i += 1; i})
por lo que el codigo quedaria de la siguiente manera
def doble(...)... {
	i + 1 + i + 1
}
Por lo que quedaria 1 + 1 + 2 + 1 = 5
```

---
# Temas para repasar
## Componentes Declarativas
### Propiedades
* Tienen **Transparencia Referencial**: Esta es una de las propiedades que tienen las componentes declarativas. Significa que una componente declarativa, junto con una asignación de valor a sus parámetros, equivale semánticamente al resultado de su ejecución
* Ausencia de **efectos secundarios**: Las operaciones declarativas deben producir siempre los mismo resultados para los mismo argumentos y no alteran la memoria externa.
* No requieren variables exclusivamente locales: En el paradigma declarativo, no es obligatorio que todas las variables sean locales, pero la asignación de valor que interactúe con el programa de ninguna manera puede ser una asignación destructiva que mute datos existentes
## Componentes Imperativas (no declarativas)
### Propiedades
* **Asignación Destructiva**: Ocurre cuando cambiamos el valor de una variable ya declarada, destruyendo su valor anterior en memoria para sustituirlo por uno nuevo.
* **Efectos Secundarios**: Al usar asignación destructiva o al modificar variables globales dentro de una función, las expresiones imperativas producen cambios visibles en el estado del programa mas allá de simplemente retornar un resultado. Esto provoca directamente la pérdida de la transparencia referencial, ya que si una expresión tiene efectos secundarios o depende de ellos, evaluarla varias veces con los mismos argumentos puede dar valores completamente distintos.
* **Uso de estructuras de control con estado interno**: O sea bucles (`for` o `while`). Estos bucles no declarativos porque dependen de un estado interno (como variables iteradoras) que se modifica secuencialemente en cada paso de la iteración.
* **Ejecución estrictamente secuencial**: Dado que las instrucciones imperativas alteran el estado, y una instrucción puede depender del estado que acaba de modificar la anterior, sus argumentos deben evaluarse en un orden fijo y secuencial.
## Alcance Estático
## Alcance Dinámico
