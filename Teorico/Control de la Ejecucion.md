## Funciones y Procedimientos
Esta en el Mitchell 7.3
## Pasaje de Parámetros
Los **Nombres de los Parámetros** que se usan cuando se declara una función se llaman **Parámetros Formales**. Cuando se llama a una función, se usan los llamados **Parámetros Reales**, que son los valores efectivos de los parámetros formales en una llamada concreta a la función. Se ilustran la distinción entre los parámetros formales y los reales se ilustra en el código:
```
proc p (int x, int y) { <-------------- { Nombres de los Parametros }
	if (x > y) then ... else ...;
	...
	x := y * 2 + 3;
	...
}
p(z, 4 * z + 1); <--------------------- { Parametros Reales }
```
Los identificadores `x` e `y` son parámetros del procedimiento `p`. Los parámetros reales en la llamada a `p` son `z` y `4*z+1`.
La forma en que los parámetros se evalúan y se pasan a la función depende del lenguaje de programación y el tipo de mecanismo de **Pasaje de Parámetros** que utiliza. Los distintos mecanismos de pasaje de parámetros se diferencian por el momento en el que se evalúa el parámetro real (**Evaluación Estricta**, en el momento de pasar el parámetro, o **Perezosa**, cuando se necesita) y por la ubicación de memoria donde se almacena el valor del parámetro (la misma ubicación del bloque que hace la llamada a función o una nueva ubicación especifica de la función).
Entre los mecanismos que evalúan el parámetro real antes de ejecutar el cuerpo de la función, los más comunes son el **Pasaje de Referencia** y el **Pasaje por Valor**. Estos dos mecanismos se diferencian entre ellos por la ubicación de memoria donde se almacena el valor del parámetro. El **Pasaje por Referencia** pasa el **L-valor**, o sea la **Ubicación en Memoria** del parámetro real, mientras que el **Pasaje por Valor** pasa el **R-valor**, o sea, **el valor que hay en la ubicación de memoria**.
##### La diferencia entre el pasaje por valor y el pasaje por referencia es importantes por varias razones:
* **Efectos Secundarios**: Las asignaciones de valor en el interior del cuerpo de la función pueden tener diferentes efectos.
* ***Aliasing***: Ocurre cuando dos identificadores de variable se refieren al mismo objeto o ubicación. Esto puede ocurrir cuando dos parámetros se pasan por referencia o un parámetro pasado por referencia tiene la misma ubicación que la variable global del procedimiento.
* **Eficiencia**: Pasar por valor puede ser ineficaz para grandes estructuras de datos, si hay que copiar ese valor. Pasar por referencia puede ser menos eficiente que pasar por valor pequeñas estructuras que se adapten bien en la pila, ya que cuando los parámetros se pasan por referencia, debemos resolver la referencia de un puntero para obtener su valor xd.
### Semántica de Pasaje por Valor
Es una forma estructura de pasaje de parámetros, o sea, que el argumento se evalúa, y el valor que se obtiene **(R-valor**) se liga a una variable local de la función, en general copiando el valor a una nueva ubicación de memoria en el *activation record* de la llamada a la función. El valor que se encuentra en el *activation record* que llama a la función no es modificado en ningún momento.
Por ejemplo:
```
f(x) = { x := x + 1; volver x };
... f(y) ...;
```
Si el parámetro que pasa por valor e `y` es una variable entera, entonces este código tiene el mismo significado que el siguiente código (en ML):
```ml
fun f(z: int) = let x = ref z in x :=! x + 1; !x end;
... f(!y) ...;
```
Como se puede ver en el tipo, el valor pasado a la función `f` es un numero entero. el entero es el r-valor del parámetro real `y`, como lo indica la expresión `!y` en la llamada. En el cuerpo de `f`, se asigna y se inicializa una nueva ubicación para el r-valor de `y`.
Si el valor de `y` es 0 antes de la llamada, entonces el valor de `f(!y)` es 1 porque la función `f` incrementa el parámetro y devuelve su valor. Sin embargo, el valor de `y` después de la llamada es aún 0, porque la asignación dentro del cuerpo de `f` solamente cambia el contenido de una ubicación temporal.
### Semántica de Pasaje por Referencia
Este pasaje se liga al l-valor del parámetro real al parámetro formal. En este caso se tiene un único valor referenciado (o apuntado) desde dos puntos diferentes, el programa principal y la función a la que se le pasa el argumento, por lo que cualquier acción sobre el parámetro se realiza sobre la misma posición de memoria (*aliasing*).
Esto significa que la función que es llamada puede modificar la variable con efecto en el bloque que llama a la función. De esta manera, el pasaje por referencia se puede usar como un canal de comunicación entre la función que llama y la que es llamada, pero a la vez resulta mas difícil seguir el rastro de los efectos de una llamada a función.
Vemos el detalle de la semántica de pasaje por referencia.
Consideremos la misma definición de la función y de llamadas utilizadas en la explicación de pasaje por valor:
```
f(x) = { x := x + 1; volver x };
... f(y) ...;
```
Si el parámetro se pasa por referencia e `y` es una variable entera, entonces este código tiene el mismo significado que el siguiente código:
```
fun f(x: int ref) = (x := !x + 1; !x);
... f(y)
```
Como se ve en el tipo, el valor pasado a la función `f` es una referencia, un l-valor. Si el valor de `y` es 0 antes de la llamada, entonces el valor de `f(!y)` es 1 ya que la función `f` incrementa el parámetro y devuelve su valor. Sin embargo, a diferencia de la situación para el pasaje por valor, el valor de `y` es 1 también después de la llamada, debido a que la asignación dentro del cuerpo de `f` también cambia después del parámetro real.
Veamos otro ejemplo, escrito en Algol, combina pasaje por referencia y pasaje por valor:
```Algol
fun f(pass-by-ref x : int, pass-by-value y : int)
	begin
		x := 2;
		y := 1;
		if x = 1 then return 1 else return 2;
	end;
var z : int;
z := 0;
print f(z, z);
```
Si lo traducimos en pseudo-Algol a ML nos da:
```ml
fun f(x : int ref, y : int) =
	let val yy = ref y in
		x := 2;
		yy := 1;
		if (!x = 1) then 1 else 2
	end;
val z = ref 0;
f(z, !z);
```
Este código, que trata a los r-valores y l-valores explícitamente, muestra  que para el pasaje por referencia se pasa un l-valor, la referencia `z`. Para pasaje por valor, se pasa un r-valor, el contenido `!z` de `z`, y se le asigna una nueva ubicación temporal.
Si se pasa `y` por valor tal como se ve, a `z` se le asigna el valor 2. En cambio, si `y` se hubiera pasado por referencia, entonces `x` e `y` habrían sido alias de la misma variable y a `z` se le habría asignado el valor 1.
En otro ejemplo tenemos una función que comprueba si sus dos parámetros son alias:
```
function (y, z) {
	y := 0;
	z := 0;
	y := 1;
	if z=1 then y := 0; return 1 else y := 0; return 0
}
```
Si `y` y `z` son alias, entonces asignar 1 a `y` asignará a `z` también, y la función devolverá 1. De lo contrario, la función devolverá 0. Por lo tanto, una llamada `f(x, x)` tendrá diferentes resultados si los parámetros se pasan por valor o por referencia.

---
### Semántica de Pasaje por Valor-Resultado
Es un tipo de pasaje de parámetro con **Evaluación Estricta** (o sea, en el momento de pasar el parámetro). Se basa en que dentro de la función se trabaja como si los argumentos hubieran sido pasados por valor, pero al acabar la función los valores que tengan los argumentos serán copiados a la ubicación de memoria en la que se ubicaba el valor copiado inicialmente.

---
#### Pasaje de Parámetros No Estricto (Perezoso)
En la evaluación perezosa, los argumentos de una función no se evalúan a menos que se utilicen efectivamente en la evaluación  del cuerpo de la función.

---
### Pasaje por Nombre
Los argumentos de una función no se evalúan antes de la llamada a la función, sino que se sustituyen sus nombres directamente en el cuerpo de la función y luego se dejan para ser evaluados cada  vez que aparecen en la función. Si un argumento no se utiliza en el cuerpo de la función, el argumento no se evalúa; su se utiliza varias veces, se re-evalúa cada vez que aparece.
En algunas ocasiones tiene ventajas sobre el pasaje por valor, por ejemplo, si el argumento no siempre se usa, porque nos ahorramos la evaluación del argumento. Si el argumento es un cálculo no termina, la ventaja es enorme. Sin embargo, cuando se utiliza el argumento en la función, el pasaje por nombre es a menudo más lento.

---
### Pasaje por Necesidad
Es una versión memorizada del pasaje por nombre donde, si se evalúa el argumento de la función, ese valor se almacena para usos posteriores. Es un entorno "puro" (sin efectos secundarios), esto produce los mismo resultados que el pasaje por nombre; cuando el argumento de la función se utiliza dos o mas veces, el pasaje por necesidad es casi siempre más rápido.

---
## Alcance Estático vs. Alcance Dinámico
Si un identificador `x` aparece en el cuerpo de una función, pero `x` no se declara dentro de la función, entonces el valor de `x` depende de alguna declaración fuera de la función. En esta situación, la ubicación de `x` esta fuera del registro de *activation record* para la función y es una **Variable Libre** o **Variable Global** respecto a esa función. Debido a que `x` ha sido declarada en otro bloque, el acceso a una `x` libre o global consiste en encontrar el *activation record* pertinente en la pila.
Para buscar la declaración adecuada de un identificador de variable libre existen dos políticas:
* **Alcance Estático** (También llamado **Alcance Léxico**):  Un identificador global se refiere al identificador con ese nombre que se encuentre en el bloque contenedor mas cercano en el texto del programa.
* **Alcance Dinámico**: Un identificador se refiere al identificador que se encuentre en el *activation record* mas reciente en la pila.

Por lo tanto, la búsqueda de una declaración usando la política de alcance estático utiliza la relación estática e inmutable entre bloques en el texto del programa. En cambio, el alcance dinámico utiliza la secuencia efectiva de las llamadas que se ejecutan en la ejecución del programa, que es dinámica y puede cambiar.

### Ejemplo de Diferencia entre los Dos Tipos de Alcance
La diferencia entre los dos tipos de alcance se ilustra mediante el siguiente código que contiene dos declaraciones de `x`:
```
int x=1;
function g(z) = x+z;
function f(y) = {
	int x = y+1;
	return g(y*x)
};
f(3);
```
La llamada a `f(3)` lleva a una llamada a `g(12)` dentro de la función `f`. Esto provoca que se evalué la expresión `x + z` en el cuerpo de `g`. Después de la llamada a `g`, la pila de ejecución tendrá *activation record* para la declaración externa de `x`, la invocación de `f`, y la invocación de `g`, como se muestra en la siguiente ilustración.
![](EjemploAlcance.png)
En este punto se almacenan en la pila dos identificadores de variable con el nombre de `x`, uno en el bloque exterior y uno dentro de `f`. Con **Alcance Dinámico**, el identificador de `x` en la expresión `x + z` sera interpretado como la del *activation record* creado mas recientemente, o sea, `x = 4`. Con **Alcance Estático**, el identificador de `x` en `x + z` se referirá a la declaración de `x` del bloque del texto de programa mas cercano que la contiene, mirando hacia arriba desde el lugar que aparece `x + z` en el texto del programa. Con alcance estático, la declaración correspondiente de `x` es la del bloque exterior, o sea, `x = 1`.

### Naturalidad y Overhead del Alcance Estático
El alcance estático es la semántica mas intuitiva, por esta razón los lenguajes de programación proveen mecanismos necesarios para poder mantener esta semántica. Hay diferentes opciones para hacerlo.
La opción mas sencilla es prohibir los contexto en los que el alcance estático daría un  resultado distinto al alcance dinámico.
Sin embargo, en la mayoría de los lenguajes se usan ***Access Links*** para mantener la semántica del alcance estático.
Un ***Access Link*** es una dirección de memoria que se guarda en el *activation record* de una función y que apunta al *activation record* del bloque mas cercano que lo contiene en el texto del programa. Para los bloques anidados *in-line* esta dirección de memoria es redundante con el *control link*, ya que el bloque mas cercano que lo contiene siempre se corresponde con el *activation record* que se ha apilado inmediatamente antes.
Para las funciones, sin embargo, el bloque mas cercano que la contiene viene determinado por el lugar donde se declara la función. Debido a que el punto de declaración a menudo es diferente del punte en el que se llama a una función, el *access link* generalmente apunta a un *activation record* diferente al *control link*.
Por ejemplo, los *activation records* y el *access link* para el código del ejemplo anterior:
```
int x=1;
function g(z) = x+z;
function f(y) = {
	int x = y+1;
	return g(y*x)
};
f(3);
```
![](ActivationRecordDelEjemploAlcance.png)
Vemos la pila pila de ejecución después de la llamada a la función `g` dentro del cuerpo de `f`.
Como sabes, los *control links* apuntan al *activation record* previo en la pila, y los dibujamos a la izquierda para dejar espacio para los *access links* a la derecha. el *access link* para cada bloque apunta al *activation record* del bloque mas cercano que lo contiene en el texto del programa.
Algunos puntos importantes acerca del gráfico:
* La declaración de `g` se produce dentro del alcance de la declaración de `x`. Por lo tanto, el access link para la declaración de `g` apunta al activation record para la declaración de `x`.
* La declaración de `f` esta dentro del alcance de la declaración de `f`. Por lo tanto, el access link para la declaración de `f` apunta al activation record para la declaración de `g`.
* La llamada `f(3)` crea un activation record asociado al alcance del cuerpo de `f`. El cuerpo de `f` ocurre dentro del alcance de la declaración de `f`. Por lo tanto, el access link para `f(3)` apunta al activation record para la declaración de `f`.
* La llamada `g(12)` crea un *activation record* asociado al alcance del cuerpo de `g`. El cuerpo de `g` ocurre dentro del alcance de la declaración de `g`. Por lo tanto, el access link para `g(12)` apunta al activation record para la declaración de `g`.
* Evaluamos la expresión `x + z` sumando el valor del parámetro `z`, almacenado localmente en el activation record de `g`, y el valor de la variable global `x`. Encontramos la ubicación de la variable global `x` siguiendo el access link de la activación de `g` al activation record asociado a la declaración de `g`. Luego seguimos el access link en ese activation record para encontrar el activation record que contiene la variable `x`.
