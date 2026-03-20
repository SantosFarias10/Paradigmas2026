Hay dos Tipos de Alcance:
* **Alcance Estático**: El valor de las variables globales se obtiene del bloque inmediatamente contenedor
* **Alcance Dinámico**: El valor de las variables globales se obtiene del activation record mas reciente.
#### Ejemplo
```
val x = 4; <------------{Alcance Estatico}
fun f(y) = x*y;
fun g(h) =
	let
		val x = 7; <------{Alcance Dinamico}
	in
		h(3) + x;
g(f);
```
Cuando se ejecuta `g(f)`, al entrar en la función de `g(h)`, cuando se ejecuta `h(3) + x` con alcance dinámico va a tomar `x = 7` porque al subir por la pila del activation record es el primero que encuentra. Y si tenemos alcance estático, tomara `x = 3` porque se obtiene del bloque inmediatamente contenedor.
### Activation Record para Alcance Estático
Tenemos el:
* ***Control Link***: Es el link al activation record del bloque anterior (el que llama al actual) y depende del comportamiento dinámico del programa.
* ***Access Link***: Es el link al activation record del bloque que incluye de mas cerca al actual, lexicamente, en el texto del programa y depende del texto estático del programa.
![](ActivationRecordParaAlcanceEstatico.png)
# Clausura (Estáticas)
El valor de una función es el par `clausura = <entorno, codigo>`. La idea es que una función con alcance estático lleva un link a su *environment* estático.
### Alcance Estático con Clausura
```
val x = 4; <------------{Alcance Estatico}
fun f(y) = x*y;
fun g(h) =
	let
		val x = 7; <------{Alcance Dinamico}
	in
		h(3) + x;
g(f);
```
![](AlcanceEstaticoConClausura.png)