## Funciones y Procedimientos
Esta en el Mitchell 7.3
## Pasaje de Parámetros
Los nombres de los parámetros que se usan cuando se declara una función se llaman **Parámetros Formales**. Cuando se llama a una función, se usan los llamados **Parámetros Reales**, que son los valores efectivos de los parámetros formales en una llamada concreta a la función. Se ilustran la distinción entre los parámetros formales y los reales se ilustra en el código:
```
proc p (int x, int y) {
	if (x > y) then ... else ...;
	...
	x := y * 2 + 3;
	...
}
p(z, 4 * z + 1);
```
Los identificadores `x` e `y` son parámetros del procedimiento `p`. Los parámetros reales en la llamada a `p` son `z` y `4*z+1`.
La forma en que los parámetros se evalúan y se pasan a la función depende del lenguaje de programación y el tipo de mecanismo de **Pasaje de Parámetros** que utiliza. Los distintos mecanismos de pasaje de parámetros se diferencian por el momento en el que se evalúa el parámetro real (evaluación estricta, en el momento de pasar el parámetro, o perezosa, cuando se necesita) y por la ubicación de memoria donde se almacena el valor del parámetro (la misma ubicación del bloque que hace la llamada a función o una nueva ubicación especifica de la función).
Entre los mecanismos que evalúan el parámetro real antes de ejecutar el cuerpo de la función, los más comunes son el **Pasaje de Referencia** y el **Pasaje por Valor**. Estos dos mecanismos se diferencian entre ellos por la ubicación de memoria donde se almacena el valor del parámetro. El pasaje por referencia pasa el l-valor, o sea la ubicación en memoria, del parámetro real, mientras que el pasaje por valor pasa el r-valor, o sea, el valor que hay en la ubicación de memoria.
La diferencia entre el pasaje por valor y el pasaje por referencia es importantes por varias razones:
* **Efectos Secundarios**: Las asignaciones de valor en el interior del cuerpo de la función pueden tener diferentes efectos.
* ***Aliasing***:Ocurre cuando dos identificadores de variable se refieren al mismo objeto o ubicación. Esto puede ocurrir cuando dos parámetros se pasan por referencia o un parámetro pasado por referencia tiene la misma ubicación que la variable global del procedimiento.
* **Eficiencia**: Pasar por valor puede ser ineficaz para grandes estructuras de datos, si hay que copiar ese valor. Pasar por referencia puede ser menos eficiente que pasar por valor pequeñas estructuras que se adapten bien en la pila, ya que cuando los parámetros se pasan por referencia, debemos resolver la referencia de un puntero para obtener su valor xd.
### Semántica de Pasaje por Valor
Este pasaje es la estrategia de evaluación más común en los lenguajes de programación. Es una forma estructura de pasaje de parámetros, o sea, que el argumento se evalúa, y el valor que se obtiene (r-valor) se liga a una variable local de la función, en general copiando el valor a una nueva ubicación de memoria en el *activation record* de la llamada a la función. El valor que se encuentra en el *activation record* que llama a la función no es modificado en ningún momento.
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
Pag 30 me quede xd
