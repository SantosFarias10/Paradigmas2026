# Pasaje de Parámetros
## Argumentos y Parámetros
* **Argumento**: Expresión que aparece en una **Llamada a Función**.
* **Parámetro**: Identificador que aparece en la **Declaración de una Función**.
La correspondencia entre parámetro y argumentos es por número y posición.
## Mecanismos de Pasaje de Parámetros
### Pasaje por Valor
La función que llama pasa el **r-valor** del argumento a la función que es llamada.
* Se computa el valor del argumento en la llamada.
* No hay "*aliasing*" (o sea, dos identificadores para una sola ubicación en memoria).
La función no puede cambiar el valor de la variable de la función que llama.
### Pasaje por Referencia
La función que llama pasa el **l-valor** del argumento a la función que es llamada.
* Se asigna la dirección de memoria del argumento al parámetro.
* Causa "*aliasing*".
La función puede modificar la variable de la función que llama.
### Pasaje por Valor - Resultado
Intenta tener beneficios de llamada por referencia (efectos secundarios en los argumentos) sin los problemas de aliasing.
Hace una copia en los argumentos al principio, copia las variables locales a los propios argumentos al final del procedimiento, de forma que se modifican los argumentos.
* Tener en cuenta: El comportamiento depende del orden en que se copian las variables locales.
### Pasaje por Nombre
En el cuerpo de la función se sustituye textualmente el argumento para cada instancia de su parámetro.
La evaluación del argumento se posterga hasta que efectivamente se ejecuta en el cuerpo de la función.
Asociado a evaluación perezosa (*lazy*) en el cuerpo de la función.
### Pasaje por Necesidad
Variación del Pasaje por Nombre donde se guarda la evaluación del parámetro después del primer uso.
El resultado es Idéntico al pasaje por nombre, es más eficiente, si no hay efectos secundarios.
Mismo concepto que *lazy evaluation*.
# Alcance y Clausura
## Reglas de Alcance
* **Alcance Estático**: El valor de las variables globales se obtiene del bloque inmediatamente contenedor.
* **Alcance Dinámico**: El valor de las variables globales se obtiene del activation record más reciente.
## Clausuras
El valor de una función es el par: `clausura = <Entorno, Código>`
* La idea es que un función con alcance estático lleve un link a su environment estático.
Llamada a una función con clausura
* Aloja el activation record para la llamada.
* Fijar el access link del activation record usando el puntero de la clausura.
## Función de Alto Orden
Una función puede ser argumento o resultado de otra función
* Se necesita un puntero al activation record más arriba en el stack.
* Puede surgir problemas especialmente al pasar una función como argumento.
## Devolver Funciones como Resultado
No todos los lenguajes tienen esta posibilidad.
* Se pueden crear funciones de forma dinámica, con valores instanciados en tiempo de ejecución (lo que conocíamos como generalización).
* El valor de una función es la `clausura = <env, código>`.
* El código no se compila dinámicamente en casi ningún lenguaje.
* Necesitamos mantener el entorno de la función que generó la función dinámica.
# Recursión a la Cola
La función `g` hace una **Llamada a la Cola** a la función `f` si el valor de retorno de la función `f` es el valor de retorno de `g`.
Por ejemplo:
```
fun g(x) = 
	if x > 0
		then f(x)        <-- Llamada a la cola
		else f(x)*2   <-- NO LLamada a la cola
```
