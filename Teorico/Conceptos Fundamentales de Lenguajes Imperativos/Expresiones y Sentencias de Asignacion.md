# Variables y Asignación
En la parte derecha de una asignación esta el r-valor de la variable, en la parte izquierda esta su l-valor.
Por ejemplo:
* `x:= 1`: significa "guardemos `1` como r-valor de la ubicación señalada por el l-valor ligado a `x`".
* `x:= x+1`: Significa "obtengamos el r-valor que encontramos en el l-valor ligado al identificador de la variable `x`, sumémosle 1, y guardemos el resultado como r-valor de la ubicación señalada por el l-valor ligado a `x`".
Una expresión que no tenga un l-valor no puede aparecer en la parte izquierda de una asignación. Como por ejemplo:
* `1=x+1`,
* `++x++`.
El r-valor de un puntero es el l-valor de otra variable (o sea el valor de un puntero es otra dirección).
Las constantes solo tienen r-valor y las funciones solo tienen l-valor.

