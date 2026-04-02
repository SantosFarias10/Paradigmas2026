# Expresiones Imperativas vs. Expresiones Funcionales
La distincion entre construcciones imperativas y declarativas se basa en que las imperativas cambian un valor y las declarativas declaran un nuevo valor. Por ejemplo, en el fragmento del programa:
```
{ int x = 1;
	x = x+1;
	{ int y = x+1
		{ int x = y+1;
}}}
```
solo la segunda linea es una operacion impeartiva, las otras contienne declaraciones de nuevas variables.
Un punto sutil es que la ultima linea declara una nueva variable con el mismo nombre que el de una variable declarada anteriormente. La forma mas sencilla de entender la diferencia entre declarar una nueva variable y cambiar el valor de una variable ya existente es cambiando el nombre de la variable. Las variables ligadas, que no son libres en una expresion (que estan definidas dentro del alcance de la expresion) pueden cambiar de nombre sin cambiar el significado de la expresion. En particular, podemos cambiar el nombre de las variables ligadas en el fragmento de programa anterior de la siguiente manera:
```
{ int x = 1;
	x = x+1;
	{ int y = x+1
		{ int z = y+1;
}}}
```

La asignacion imperativa puede introducri **Efectos Secundarios** porque puede destruir el valor anterior de una variable, sustituyendo por uno nuevo, de forma que este no este disponible mas adelante. En programacion funcional la asignacion imperativa se ocnoce como asignacion o actualizacion destructiva.
Decimos que una operacion computacional es **Declarativa** si, cada vez que se invoca con los mismos argumentos, devuelve los mismo resultados independientemente de cualquier otro estado de computacion. Una operacion declarativa es:
* **Independiente**, no depende de ningun estado de la ejecucion por fuera de si misma.
* **Sin Estado**, no tiene estados de ejecucion internos que sean recordados entre invocaciones.
* **Deterministica**, siempre produce los mismos resultados para los mismos argumentos y, por extension, ejecutar la operacion no tendra efectos secundarios.