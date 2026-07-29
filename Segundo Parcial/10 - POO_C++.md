## Objetivos de Diseño
* Proveer características de orientación a Objetos en un lenguaje tipo C, sin renunciar a la eficiencia.
	* Retrocompatible con C.
	* Mejorando el cheque de tipos estático.
	* Con abstracción de datos, objetos, clases.
	* Priorizando código compilado.
* Si no se usa una característica orientada a objetos, el código compilado debería ser igual de eficiente que C sin orientación a objetos.
### ¿Que TAN bien salió?
Tiene un diseño muy complicado:
* Muchas características con interacciones complejas, difíciles de predecir a partir de los principios básicos.
* La mayoría de usuarios serios usan sólo un subconjunto del lenguaje, porque el lenguaje completo es complejo e impredecible.
* Muchas propiedades dependientes de implementación.
## Restricciones
* C tiene un modelo de máquina específico (no abstracto), porque tiene acceso al bajo nivel (por herencia de BCPL).
* No hay recolección de basura, por eficiencia, así que hay que manejar la memoria de objetos explícitamente.
* Las variables locales se guardan en los activation record.
	* Los objetos se tratan como generalizaciones de `structs`
	* Se los puede alojar en el stack y tratarlos como `l-valores`.
	* El programador puede acceder a la diferencia entre stack y heap.
## Añadidos NO Orientados a Objetos
* Templates de función (programación genérica), en la STL.
* Pasaje por referencia.
* Sobrecarga definida por el usuario.
* Tipo booleano.
## Sistemas de Objetos
* Clases
* Objetos
	* Con consulta dinámica (dynamic lookup) de funciones virtuales.
* Herencia
	* Simple y múltiple.
	* Clases base públicas y privadas.
* Subtipado
	* Ligado al mecanismo de herencia.
* Encapsulación.
## Buenas Decisiones
Niveles de Visibilidad
* `Public`: Visible en todos lados.
* `Protected`: En las declaraciones de clase y sus subclases
* `Private`: Visible solamente en la clase donde se clara.
Se permite herencia sin subtipado
* Clases base privadas y protegidas.
## Áreas Problemáticas
* Cast
	* Es irregular ya que a veces se fuerzan y a veces no.
* Sin garbage collection.
* Los objetos se alojan en el stack.
	* Mejor eficiencia, interacción con las excepciones.
	* Pero la asignación funciona mal, posiblemente con punteros colgantes.
* Sobrecarga
	* Demasiados mecanismos de selección de código.
* Herencia Múltiple
	* Como se busca eficiencia, el comportamiento es complicado.
## Funciones Virtuales
* Se acceden a través de un puntero en el objeto.
* Se pueden redefinir en subclases derivadas.
* La función exacta que se llama se determina dinámicamente.
Las funciones NO virtuales son funciones comunes, no se pueden redefinir pero se pueden sobrecargar.
Las funciones son virtuales si se declaran explícitamente o se heredan como virtuales, si no, son no-virtuales.
Se paga overhead sólo si se usan funciones virtuales.
## Representación en Tiempo de Ejecución
![](Screenshot_2026-07-29-17-11-27_8870.png)
## ¿Por qué el lookup en C++ es más simple?
Smalltalk no tiene sistema de tipo estático
* El código `p message:params` puede referirse a cualquier objeto.
* Necesitamos encontrar un método que use el puntero del objeto.
* Diferentes clases ponen los métodos en diferentes lugares en el diccionario de métodos.
C++ le dá al compilador una superclase
* El offset de los datos y los punteros a funciones son los mismos en la subclase y la superclase, se conocen en tiempo de compilación.
* El código `p->move(x)` compila al equivalente de `(*(p->vptr[0]))(p,x)` si `move` es la primera función en la vtable.
## Puntero "this"
El código se compila de forma que la función miembro toma al objeto mismo como primer argumento
```
codigo    -> int A::f(int x) { ... g(i) ... }
compilado -> int A::f(A *this, int x) { ... this->g(i) ... }
```
El puntero "this" se puede usar en la función miembro, para devolver el puntero del objeto, pasar el puntero del objeto a otra función, etc.
Igual al "self" de Smalltalk.
## Funciones NO Virtuales
El código para funciones NO virtuales se encuentra igual que para las funciones comunes
* El compilador genera el código de la función y le asigna una dirección.
* La dirección se ubica en la tabla de símbolos.
* En el lugar de llamada, se obtiene la dirección de la tabla y se ubica en el código compilado.
* Pero en el caso de clases aplican algunas reglas especiales sobre alcance.
* La sobrecarga se resuelve en tiempo de compilación, a diferencia de la consulta de una función virtual en tiempo de ejecución.
## Reglas de Alcance
Clasificadores de Alcance:
* `::`: `class::member`
* `->`: `ptr->member`
* `.`: `object.member`
Global (objeto, función, enumerador, tipo): Nombre fuera de una función o clase no prefijado por `::` unario y no calificado.
Alcance de clase: Nombre después de `X::`, `ptr->` o `obj.`, se refiere a un miembro de la clase `X`, asumiendo `ptr` es un puntero a la clase `X` y `obj` es un objeto de la clase `X`.
## Subtipado
Subtipado en principio: `A` es un subtipo de `B` (`A<:B`) si todo objeto `A` se puede usar en un contexto en el que se necesitaba `B` sin errores de tipo
Pero en C++: `A` es un subtipo de `B` si la clase `A` tiene como clase base publica a `B`
* Esto es más débil de lo que sería necesario.
### ¿Por qué esta decisión de diseño?
* El código depende sólo de la interfaz pública.
* Si NO funciona ligada a la herencia, el subtipado lleva a pérdida de eficiencia.
* También por encapsulación
	* Si el subtipado basado en herencia se preserva si hacemos modificaciones en la clase base.
## Clases Abstractas
Una clase abstracta es una clase sin implementación completa
* Se declara con `=0`.
Útil porque puede tener clases derivadas
* Como el subtipado se sigue de la herencia en C++, se pueden usar las clases abstractas para construir jerarquías de subtipos.
Establece la disposición de la vtable (o sea, la tabla de funciones virtuales).

## Herencia Múltiple
La herencia múltiple en C++ permite que una clase derivada herede las especificaciones e implementaciones de más de una clase base independiente.
### Names Clashes
Un problema muy común es cuando dos clases base independientes definen miembros (atributos o métodos) con el mismo nombre y firmas idénticas, causando ambigüedad en la clase derivada.
#### Solución C++
C++ exige una resolución explícita por parte del programador. Para solucionar la ambigüedad, se debe:
1. Calificar el llamado en el punto de uso con el operador de resolución de alcance `::`
2. Redefinir el método en la clase derivada C para delegar explícitamente el comportamiento deseado.
### Herencia Diamante
La complicación más severa de la herencia múltiple se da cuando dos clases base (por ejemplo, `A` y `B`) heredan a su vez de una clase base común `D` (el típico grafo con forma de diamante).
```
      D (Window)
     /  \
    A    B  (TextWindow, GraphicsWindow)
     \  /
      C (TextGraphicsWindow)
```
Por defecto el objeto final `C` duplicará los miembros de `D` en memoria.
#### Solución C++, Clases Base Virtuales (Virtual Base Classes)
Para evitar la duplicación de la clase base común, C++ implementa la herencia virtual mediante el uso de la palabra clave `virtual` en las clases intermedias.
- ¿Cómo funciona?
	- Cuando `D` se declara como base virtual, el compilador altera la representación interna de los objetos `A` y `B`. En lugar de almacenar los datos de `D` de manera estática y directa dentro del layout, el acceso a la parte de `D` se realiza por indirección mediante un puntero almacenado en el objeto.
- Resultado:
	- Al compilar `C`, el compilador organiza la estructura de memoria de modo que tanto la parte de `A` como la de `B` utilicen sus respectivos punteros de indirección para apuntar y compartir una única instancia física de la parte `D` al final del objeto. Esto elimina de raíz la redundancia de datos y la ambigüedad semántica.
## Resumen
* Objeto
	* Creados por clases
	* Contienen datos del miembro
* Clases
	* Tabla de funciones virtuales.
* Herencia
	* Clases base públicas y privadas, herencia múltiple
* Subtipado
	* Sólo con clases base pública.
* Encapsulación
	* Un miembro se puede declarar público, privado o protegido.
	* La inicialización de los objetos se puede forzar parcialmente.
