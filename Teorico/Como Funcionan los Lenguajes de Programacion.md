## Estructura de un Compilador
Leer el Mitchell 4.1.1

---
## Estructura de Datos de Bajo Nivel
### Variables
Una variable esta formada por una ubicación en la memoria y un identificador asociado a esa ubicación. Esa ubicación contiene un valor, que es una cantidad o información conocida o desconocida. El nombre de la variable es la forma usual de referirse al valor almacenado; esta separación entre nombre y contenido permite que el nombre sea usado independientemente de la información exacta que representa.
Los compiladores reemplazan los nombres simbólicos de las variables con la real ubicación de los datos.
El identificador en el código fuente puede estar ligado a un valor durante el tiempo de ejecución y el valor de la variable puede, por lo tanto, cambiar durante el curso de la ejecución del programa. De esta forma, es muy fácil usar la variable en un proceso repetitivo.
Diferentes identificadores del código pueden referirse a una misma ubicación en memoria, lo cual se conoce como ***aliasing***. En esta configuración, si asignamos un valor a una variable utilizando uno de los identificadores también cambiara el valor al que se puede acceder a través de los otros identificadores.
Por ejemplo:
```c
x; int;
y: int;
x = y + 3;
```
En la asignación, el valor almacenado en la variable `y` se añade a 3 y el resultado se almacena en la ubicación para `x`. Notar que las dos variables se utilizan de manera diferentes; usamos el valor almacenado en `y`, independientemente de su ubicación, pero en cambio usamos la ubicación de `x`, independientemente del valor almacenado en `x` antes de que ocurra la asignación.
La ubicación de una variable se llama su **l-valor** y el valor almacenado en esta ubicación se llama el **r-valor** de la variable.