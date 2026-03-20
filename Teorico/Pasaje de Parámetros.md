### Argumentos y Parámetros
Los **Argumentos** son las expresiones que aparece en una **Llamada** a función.
Los **Parámetros** son los identificadores que aparecen en la **Declaración** de una función.
##### Ejemplo
```
int h, i;
void B(int w) { <-----------{Parametro}
	int j, k;
	i = 2 * w;
	w = w + 1;
}

void A(int x, int y){ <-------{Parametro}
	bool i, j;
	B(h); <----------------{Argumento}
}

int main() {
	int a, b;
	h = 5; a = 3; b = 2;
	a(a , b); <-------------{Argumento}
}
```
## Mecanismo de Pasaje de Parámetros
* Por valor.
* Por referencia.
* Por valor-resultado.
* Por nombre.
* Por necesidad.
#### Pasaje por Valor
La función que llama pasa el **r-valor** del argumento a la función que es llamada. Se computa el valor del argumento en la llamada. No hay "*aliasing*" (o sea, dos identificadores para una sola ubicación en memoria).
La función no puede cambiar el valor de la variable de la función que llama.
#### Pasaje por Referencia
La función que llama pasa el **l-valor** del argumento a la función que es llamada. Se asigna la dirección de memoria del argumento al parámetro. Causa "*aliasing*".
La función puede modificar la variable de la función que llama.
### Comparación Valor - Referencia
Si estamos trabajando con estructuras de datos grandes.
* al pasar por referencia no vamos a estar copiando las estructuras de datos grandes, por lo que ahorramos espacio en la memoria, pero el efecto secundario es que vamos a estar modificando una variable que esta fuera de un determinado bloque.
En los lenguajes funcionales no hay diferencia entre el pasaje por valor y por referencia, ya que los lenguajes funcionales no hay asignación destructiva, por lo que nunca vamos a estar modificando una variable que esta fuera del bloque que se esta ejecutando.
#### Pasaje por Valor-Resultado
Este tipo de pasaje trata de tener los beneficios de llamada por referencia sin los problemas de aliasing. El beneficio es el poder modificar una variable que esta fuera de la función que es llamada.
Hace una copia en los argumentos al principio, copia las variables locales a los propios argumentos al final del procedimiento, de forma que se modifican los argumentos.
#### Pasaje por Nombre
Tiene la ventaja de la **Evaluación Perezosa**, nos permite no evaluar algo hasta que no es estrictamente necesario, de esta forma se ahorra calculo.
En el cuerpo de la función se sustituye Textualmente el argumento para cada instancia de su parámetro. La evaluación del argumento se posterga hasta que se ejecuta en el cuerpo de la función.
#### Pasaje por Necesidad
Es una variación del **Pasaje por Nombre** donde se guarda la evaluación del parámetro después del primer uso.
![](ResumenDeLosPasajesDeParametros.png)