La primitiva básica en los lenguajes vistos hasta ahora es la función (método, procedimiento)
* `F(x)=y` La función `F` toma `x` y devuelve `y`.
En programación lógica, la primitiva básica es la relación (predicado)
* `R(x,y)` se da la relación `R` entre `x` e `y`.
### Idea Básica
El programa declara los objetivos de la computación, no la forma de obtenerlos.
## Predicados Derivados
Se pueden definir nuevos predicados con reglas: `conclusión :- premisas`
La conclusión es cierta si las premisas son ciertas:
```
volar_via(Desde, Hacia, Via) :-
	nonstop(Desde, Via)
	nonstop(Via, Hacia)
```
## Recursión
Los predicados se pueden definir recursivamente
```
llegar(X, X).
llegar(X, Z) :-
	nonstop(X, Y), llegar(Y, Z)
```
## Elementos de un Programa Prolog
Los programas en Prolog tienen Términos
* Variables,
* Constantes,
* Estructuras.
Las variables empiezan en mayúscula
Las constantes son enteros o átomos.
Las estructuras son predicados con argumentos
## Cláusulas de Horn
Una Cláusula de Horn tiene una cabeza `h` que es un predicado y un cuerpo, que es una lista de predicados `p1`, ..., `pn`
* Se escribe `h ← p1, ..., pn`
* Significa que `h` es cierto si `p1, ..., pn` son ciertos simultáneamente.
## Hechos, Reglas y Programas
Un **Hecho** en Prolog es una cláusula de Horn sin parte derecha (o con parte derecha `true`)
Una **Regla** en Prolog es una cláusula de horn con una parte derecha (`:-` es `←`)
* `term :- term1, ..., termn`.
Un **Programa** en Prolog es un conjunto de hechos y reglas.
## Cláusulas de Horn y Predicados
Cualquier cláusula de horn `h1 ← p1, ..., pn` se puede escribir como un predicado `p1 ^ ... ^ pn ⊃ h` o de forma equivalente `¬(p1 ^ ... ^ pn) ∨ h`.
No todo predicado se puede escribir como una cláusula de horn.
## Listas
Una Lista es una serie de términos separados por comas y entre corchetes.
* Lista vacía: `[]`
* Elementos son restricciones de `_`: `[_, x, y]`.
* También se puede escribir `[Cabeza | Cola]`.
### Añadir a una lista
```
append([], X, X)
append([Head | Tail], Y, [Head | Z]) :-
	append(Tail, Y, Z)
```
El último parámetro va a contener el resultado de la función, pasamos como argumento la variable que va a contener el resultado.
Esta definición dice:
* Añadir `X` a la lista vacía devuelve `X`.
* Si añadimos `Y` a `Tail` para obtener  `Z`, entonces `Y` se puede añadir a una lista un elemento más larga `[Head | Tail]` para obtener `[Head | Z]`.
### Estar en una lista (existe)
```
member(X, [X | _]).
member(X, [_ | Y]) :-
	member(X, Y)
```
El predicado de la cabeza será cierto si:
* `X` es la cabeza de la lista `[X | _]`
* `X` es es la cabeza de la lista `[_ | y]`, pero es un miembro de la cola `Y`.
Se comprueba con pattern matching
Los elementos "Sin restricciones" se marcan con `_`, y muestran elementos que no son importantes para la regla.
### + funciones sobre listas
`X` es un **prefijo** de `Z` si hay una lista `Y` que se puede añadir a `X` para hacer `Z`.
```
prefix(X, Z) :- append(X, Y, Z)
suffix(Y, Z) :- append(X, Y, Z)
```
encontrar todos los prefijos (o sufijos) de una lista:
```
?- prefix(X, [my, dog, has, fleas])
X = [];
X = [my];
X = [my, dog];
...
```
## Contestar Consultas
La computación en Prolog (contestar una consulta) es esencialmente buscar una prueba lógica.
Dirigido por el objetivo, por backtracking, búsqueda en profundidad (vs. en anchura), con estrategia:
* Si `h` es la cabeza de una cláusula de horn
	* `h ← términos`
* Y hace pattern matching con uno de los términos de otra cláusula de horn
	* `t ← t1, h, t2`
* Entonces ese término se puede reemplazar por los términos de `h`:
	* `t ← t1, términos, t2`
## Unificación
Dos términos son unificables si hay una sustitución de variables que hace que puedan llegar a ser el mismo.
* Por ejemplo, `f(x)`  y `f(3)` se unifican con `[x = 3]`.
La asignación de valores a las variables durante la resolución se llama **instanciación**.
* Es un proceso de pattern matching que determina qué instanciaciones se pueden hacer a las variables durante una serie de resoluciones.
## Operador `Is`
`is` instancia una variable temporal, comparable a una variable local en lenguajes tipo Algol
## Traza
La traza sirve para que el programador pueda ver cómo funciona una búsqueda de prueba.
## El cut
Cuando se inserta en la parte derecha de la regla, el operador cut `!` fuerza a que no se revisiten los sub-objetivos si la parte derecha encuentra un resultado una vez.
## Negación
El operador `not` se implementa como fallo del objetivo
```
not(G) :- G, !, fail
```
* `fail` es un objetivo especial que siempre falla.