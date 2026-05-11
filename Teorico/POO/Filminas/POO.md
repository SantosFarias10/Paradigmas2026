# Modularidad
## Conceptos Básicos
* **Componente**: Unidad de programa con sentido; Función, estructura de datos, módulos, etc.
* **Interfaz**: Tipo y operaciones definidos dentro de una componente que son visibles fuera del componente.
* **Especificación**: Comportamiento esperado de un componente, expresado como una propiedad observable a través de la interfaz.
* **Implementación**: Estructura de datos y funciones dentro del componente.
### Ejemplo: Complemento función
* Componente: Función que calcula la raíz cuadrada.
* Interfaz: `float sqroot (float x)`.
* Especificación: Si `x>1`, entonces `sqrt(x)*sqrt(x) = x`.
* Implementación:
* ```
  float sqroot (float x){
	  float y = x/2;
	  for (i=0; i<20; i++){
		  if ((y*y)<x){
			  y = y+step;
		  } else {
			  y = y-step;
		  }
		  step/2
	  }
	  return y;
  }
  ```
# TAD
## Idea 1
Separar la interfaz de la implementación
## Idea 2
Usar comprobación de tipos para forzar la separación.
El programa cliente solo tiene acceso a las operaciones de la interfaz.
La implementación encapsulada en el constructor TAD.
# Modulo
Construcción general para ocultar información.
* Interfaz: Conjunto de nombres y sus tipos.
* Implementación: 
	* Declaración para cada entrada en la interfaz.
	* Declaraciones extra que están ocultas.
# Abstracciones genéricas
Parametrizan los módulos por tipos
* Implementaciones generales, que se pueden instanciar de muchas formas: La misma implementación para múltiples tipos.
# Templates de C++
Mecanismo de parametrización de tipos `template <class T>`. Indica el parámetro de tipo `T`.
* C++ tiene templates de clases y de función.
Se instancian en tiempo de ligado.
* Se crea una copia del template generado para cada tipo.
* ¿Porque duplicar código?
	* Tamaño de variables locales en el activation record.
	* Ligado a las operaciones del tipado instanciado.
## Ejemplo de template
```c++
template <typename T>
void swap(T& x, T& y) {
	T tmp = x;
	x = y;
	y = tmp;
}
```
# Diferencia entre ML y C++
* ML
	* `swap` se compila a una función, y el *typechecker* (comprobador de tipos) determina cómo se puede usar.
* C++
	* `swap` se compila a formato linkeable, y el linker duplica el código para cada tipo con el que se usa.
* Porque la diferencia?
	* Mientras ML la `x` local es un puntero a un valor en el heap, con tamaño constante. C++ la `x` local es un puntero a un valor en el stack, su tamaño depende del tipo.
# Propiedades importantes de la OO
* Objetos.
* Lookup dinámico.
* Encapsulación.
* Herencia.
* Subtipado.
# Objetos
Un objeto consiste de:
* Datos ocultos: 
	* Variables de la instancia (datos del miembro).
	* Posiblemente funciones ocultas.
* Operaciones Publicas:
	* Métodos (funciones del miembro).
	* Puede tener variables públicas en algunos mensajes.
	* Construcción de encapsulación universal
		* Se puede usar para estructuras de datos, file system, bases de datos, etc.
Un programa OO envía mensajes a los objetos
# Lookup dinámico
En programación convencional, el significado de una operación con los mismos operandos es siempre el mismo.
En POO el código depende del objeto y el mensaje
## Sobrecarga vs. lookup dinámico
En programación convencional `add (x, y)` la función `add` tiene significado fijo.
Para sumar dos números `x -> add (y)` tenemos un `add` distinto si `x` es entero, complejo, etc.
Semejante a la sobrecarga, con una diferencia crítica: La sobrecarga se resuelve en tiempo de compilación, mientras que el lookup dinámico se resuelve en tiempo de ejecución.
# Encapsulación
El programador de un concepto tiene una vista detallada. 
El usuario de un concepto tiene una vista abstracta.
La encapsulación separa estas dos vistas, de forma que el código de cliente opera con un conjunto fijo de operaciones que provee el implementador de la abstracción.
# Subtipado y Herencia
La interfaz es la vista externa de un objeto.
El subtipado es una relación entre interfaces.
La implementación es la representación interna de un objeto.
La herencia es una relación entre implementaciones, de forma que nuevos objetos se puedan definir reusando implementaciones de otros 
## Interfaces de Objetos
* La interfaz de un objeto es su tipoobjetos.
## Subtipado
Si la interfaz `A` contiene todos los elementos de la interfaz `B`, entonces los objetos de tipo `A` también se pueden usar como objetos de tipo `B`
# Estructura de un programa orientado a objetos
* Agrupar datos y funciones
* Clase: Define el comportamiento de todos los objetos que son instancias de la clase.
* Subtipado: Organiza datos semejantes en clases relacionadas.
* Herencia: Evita reimplementar funciones ya definidas.
