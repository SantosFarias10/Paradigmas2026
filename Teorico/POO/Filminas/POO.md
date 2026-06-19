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
### Lookup Dinámico
El **lookup dinámico** (o búsqueda dinámica) es el mecanismo fundamental de la programación orientada a objetos donde, cuando se le envía un mensaje a un objeto, el código (método) exacto que se va a ejecutar se determina en **tiempo de ejecución** basándose en la implementación real (la clase) de ese objeto, y no en el tipo estático de la variable que lo apunta.

En la práctica, esto significa que el objeto mismo "elige" cómo responder al mensaje. Si en un programa tenemos una instrucción `x -> add(y)`, y `x` es un número entero, el método invocado realizará una suma matemática; pero si `x` resulta ser un conjunto (_set_), el método agregará el elemento al conjunto. Ambos métodos se llaman igual, pero el comportamiento varía dinámicamente según la naturaleza de `x` al momento de pasar por esa línea de código.
- **En Smalltalk:** Este mecanismo es el comportamiento por defecto para todo. Se implementa buscando el método en el diccionario de métodos de la clase del objeto y sus superclases.
- **En C++ y Java:** Se aplica mediante **funciones virtuales** (`virtual` en C++) usando una tabla de indirección llamada _vtable_ para resolver la dirección de la función en tiempo de ejecución.
### Sobrecarga
La **sobrecarga** ocurre cuando un mismo identificador o nombre de función tiene asociadas dos o más implementaciones distintas, las cuales se diferencian estrictamente por la cantidad y los **tipos estáticos de sus parámetros**.

A diferencia del lookup dinámico, la sobrecarga se resuelve enteramente en **tiempo de compilación**. El compilador analiza los tipos de los argumentos pasados en la invocación y decide de antemano qué bloque de código debe enlazarse. Un ejemplo clásico es el operador de suma `+`:

- Si escribimos `3 + 2`, el compilador detecta que ambos operandos son enteros y enlaza la instrucción a nivel de hardware para suma de enteros binarios.
- Si escribimos `3.0 + 2.0`, el compilador enlaza la instrucción matemática para suma de punto flotante. Ambas operaciones se llaman `+`, pero usan algoritmos completamente distintos determinados antes de que el programa corra.

>[!warning] Trampa clásica de Parcial (Interacción en C++)
> En C++, una función no-virtual puede reescribirse sintácticamente en una clase derivada con el mismo nombre pero distintos parámetros. ¡Esto es **sobrecarga**, no lookup dinámico!. Como no es `virtual`, el compilador usará el tipo estático del puntero (por ejemplo, `Base* p`) para decidir qué función llamar en tiempo de compilación, ignorando el tipo real del objeto en memoria.

### Diferencias Fundamentales para la Resolución de Ejercicios
Para identificar de qué mecanismo se trata al analizar un código en el parcial, fijate en estos tres ejes:

1. **Momento de Resolución:** La diferencia más crı́tica es que la sobrecarga la resuelve el compilador (tiempo de compilación) usando reglas estáticas, mientras que el lookup dinámico lo resuelve el entorno (tiempo de ejecución) evaluando el objeto real.
2. **Criterio de Selección:** La sobrecarga utiliza la _firma completa de la función_ (los tipos de todos los argumentos que le pasaste) para decidir qué algoritmo usar. El lookup dinámico tradicional (como en Java, C++ o Smalltalk) se basa **únicamente en el objeto receptor** (el objeto a la izquierda del punto, ej. `objeto.metodo(args)`), ignorando el tipo dinámico de los argumentos.
3. **Naturaleza del Algoritmo:** En el lookup dinámico, un mismo algoritmo lógico puede aplicarse polimórficamente a varios subtipos. En la sobrecarga, estás atando un mismo nombre a algoritmos que por debajo no tienen nada que ver el uno con el otro (como sumar ints vs concatenar strings)
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
