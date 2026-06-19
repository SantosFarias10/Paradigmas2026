# Programación Lógica
En los lenguajes que vimos hasta ahora la primicia básica es la función (método, procedimientos)
```
F(x) = y
```
La Función `F` toma `x` y devuelve `y`.
En **programación lógica**, la primitiva básica es la **Relación** (Predicado).
```
R(x,y)
```
Se da la relación `R` entre `x` e `y`.
## Prolog
La idea es que el programa declara los objetivos de la computación, no la forma de obtenerlos.
## Ejemplo: Base de datos lógica
![](EjemploProlog.png)
### Consulta a una base de datos lógica
* ¿A dónde podemos volar desde Austin?
	* SQL:
		* `SELECT dest FROM nonstop WHERE source="aus";`
	* Prolog:
		* `?- nonstop(aus, X)`.
		* Es más poderoso que SQL porque se puede usar recursion.
### Planificación de Vuelos
![](EjemploPrologPlanificacionDeVuelos.png)
Relación: `nonstop(X, Y)` si hay un vuelo desde `X` hasta `Y`.
### Consultas
![](EjemploPrologConsultas.png)
* `?- nostop(aus, dal)`
	* Yes.
* `?- nostop(dal, okc)`
	* Yes.
* `?- nostop(aus, okc)`
	* No.
### Variable lógica
* `?- nonstop(okc), X`.  Lo que quiere decir es que si hay algún `X` tal que `nonstop(okc, X)`.
	* `X=phx;`
		* No
* `?- nonstop(Y, dal)`
	* `Y=aus`
		* No
### No-determinismo
Los predicados pueden devolverse múltiples respuestas o ninguna.
* `?- nonstop(dal, X)`.
	* `X=hou;`.
	* `X=okc;`
		* No.
* `?- nonstop(phx, X)`
	* No.
### Conjunción lógica
Combinar condiciones múltiples en una sola consulta
* `?- nonstop(aus, X), nonstop(X, oks)`.
	* `X=dal;`.
	* `X=hou;`
		* No
### Predicados derivados
Se pueden definir nuevos predicados con reglas:
* `conclusión :- premisas`.
La conclusión es cierta si las premisas son ciertas.
```prolog
volar_via(Desde, Hacia, Via) :-
	nostop(Desde, Via),
	nonstop(Via, Hacia).
```
*  `?- volar_via(aus, okc, Via).
	* `Via=dal;`.
	* `Via=hou;`.
		* No
## Elementos de un programa Prolog
* Los programas en Prolog tienen términos.
	* Como variables, constantes, estructuras
* Las **variables** empiezan en mayúscula: `Harry`.
* Las **constantes** son enteros o átomos: `24`, `zebra`, `'bob'`, `'.'`.
* Las **estructuras** son predicados con argumentos: `n(zebra)`, `habla(Y, Castellano)`.
## Cláusulas de Horn
Una **cláusula de Horn** tiene una cabeza `h` que es un predicado y un cuerpo, que es una lista de predicados `p1, p2, ..., pn`.
* se escribe `h <- p1, p2, ..., pn`.
* Significa "h es cierto si p1, p2, ..., pn son ciertos simultáneamente".
Ejemplo:
```
nieva(C) <- precipitación(C), hiela(C)
```
"Nieva en la ciudad `C` si hay precipitación en `C` y hiela en `C`".
## Hechos, reglas y programas
* Un hecho en Prolog es una cláusula de Horn sin parte derecha (o con parte derecha `true`).
* Una regla Prolog es una cláusula de Horn con una parte derecha (`:-` es `<-`).
	* `term :- term1`, `term2`, ..., `termn`.
	* La parte izquierda se llama **cabeza**.
* Un programa Prolog es un conjunto de hechos y reglas.
## Cláusulas de Horn y predicados
* Cualquier cláusula de Horn `h <- p1`, `p2`, ..., `pn` se puede escribir como un predicado `p1 ∧ p2 ∧ ... ∧ pn ⊃ h`, o, de forma equivalente `¬(p1 ∧ p2 ∧ ... ∧ pn) v h`
* No todo predicado se puede escribir como una cláusula de Horn.
	* Por ejemplo: `literato(x) ⊃ lee(x) v escribe(x)`.
## Listas
Una **Lista** es una serie de términos separados por comas y entre corchetes.
* Una lista vacía se representa como `[]`.
* Elementos sin restricciones de `_: [_, X, Y]`.
* También se puede escribir `[Cabeza | Cola]`.
### Añadir a una lista
```
append([], X, X).
append([Head | Tail], Y, [Head | Z]) :- append(Tail, Y, Z).
```
El último parámetro va a contener el resultado de la función, pasamos como argumento la variable que va a contener el resultado.
* Esta definición dice:
	* Añadir `X` a la lista vacía devuelve `X`.
	* Si añadimos `Y` a `Tail` para obtener `Z`, entonces `Y` se puede añadir a una lista un elemento más larga `[Head | Tail]` para obtener `[Head | Z]`.
### Estar en una lista (existe)
```
member(X, [X | _]).
member(X, [_ | Y]) :- member (X, Y).
```
* El predicado de la cabeza será cierto si:
	* `X` es la cabeza de la lista `[X | _]`.
	* `X` no es la cabeza de la lista `[_ | Y]`, pero es un miembro de la cola `Y`.
* Se comprueba con pattern matching.
* Los elementos "sin restricciones" se marcan con `_`, y muestran elementos que no son importantes para la regla.
## Contestar consultas Prolog
* La computación en Prolog (contestar una consulta) es esencialmente buscar una prueba lógica.
* Dirigido por el objetivo, por backtracking, búsqueda en profundidad (vs. en anchura), con estrategia:
	* Si `h` es la cabeza de una cláusula de Horn
		* `h <- términos`.
	* Y hace pattern matching con uno de los términos de otra cláusula de Horn.
		* `t <- t1, h, t2`
	* Entonces ese término se puede reemplazar por los términos de `h`:
		* `t <- t1, términos, t2`.
### Ejemplo planificación de vuelos: Búsqueda de prueba
![](EjemploPrologPlanificacionDeVuelosBusquedaDePrueba.png)
## Unificación
* Dos términos son unificables si hay una sustitución de variables que hace que puedan llegar a ser el mismo.
	* Por ejemplo, `f(x)` y `f(3)` se unifican con `[X=3]`
		* `f(f(Y))` y `f(X)` se unifican con `[X=f(Y)]`.
* La asignación de valores a las variables durante la resolución se llama **instanciación**.
	* Es un proceso de pattern matching que determina qué instanciaciones se pueden hacer a las variables durante una serie de resolución.
### Ejemplo: Está en la lista
![](EjemploPrologEstaEnLaLista.png)
## Completitud
El procedimiento de búsqueda de Prolog devuelve cosas que son todas ciertas, pero no se puede probar todo lo que es cierto (es incompleto).
## El operador `Is`
* `Is` instancia una variable temporal, comparable a una variable local en lenguajes tipo Algol
	* Ejemplo:
```
?- factorial(0, 1).
?- factorial(N, Result) :-
	N > 0,
	M is N - 1
	factorial(M, SubRes),
	Result is N * SubRes.
```
