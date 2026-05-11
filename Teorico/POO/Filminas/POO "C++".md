# Objetivo de diseño
Proveer características de orientación a objetos en un lenguaje tipo C, sin renunciar a la eficiencia.
* Retrocompatible con C.
* Mejorando el chequeo de tipos estático.
* Con abstracción de datos, objetos, clases.
* Priorizando código compilado.
Principio importante: Si no se usa una característica orientada a objetos, el código compilado debería ser igual de eficiente que C sin orientación a objetos.
## ¿Que tan bien les salio?
* Muy popular.
* Dadas las restricciones y los objetivos, muy buen diseño.
* Pero con diseño muy complicado:
	* Muchas características con interacciones complejas, difíciles de predecir a partir de los principios básicos.
	* La mayoría de usuarios serios usan sólo un subconjunto del lenguaje, porque el lenguaje completo es complejo e impredecible.
	* Muchas propiedades dependientes de implementación.
# Restricciones importantes
* C tiene un modelo de máquinas específicos (no abstracto), porque tiene acceso al bajo nivel (por herencia de BCPL).
* No hay recolección de basura, por eficiencia, así que hay que manejar la memoria de objetos explícitamente.
* Las variables locales se guardan en los activation record.
	* Los objetos se tratan como generalizaciones de `structs`.
	* Se los puede alojar en el stack y tratarlos como `l-valores`.
	* El programador puede acceder a la diferencia entre stack y heap.
## Añadidos no orientados a objetos
* templates de función (programación genérica), en la STL.
* pasaje por referencia.
* Sobrecarga definida por el usuario.
* tipo booleano.
# Sistemas de objetos de C++
* **Clases**.
* **Objetos**: Con consulta dinámico (*dynamic lookup*) de funciones virtuales.
* **Herencia**: Simple y múltiple. Clases base públicas y privadas.
* **Subtipado**: Ligado al mecanismo de herencia.
* **Encapsulación**.
## Buenas decisiones
Niveles de visibilidad:
* `Public`: Visible en todos lados.
* `Protected`: En las declaraciones de clase y sus subclase.
* `Private`: Visible solamente en la clase donde se declara.
Se permite herencia sin subtipado:
* Clases base privada y protegida.
# Áreas Problemáticas
* Casts
	* Irregular: A veces se fuerzan y a veces no.
* Sin garbage collection
* Los objetos se alojan en el stack
	* Mejor eficiencia, interacción con las excepciones.
	* Pero la asignación funciona mal, posiblemente con punteros colgantes.
* Sobrecarga:
	* Demasiados mecanismos de selección de código?
* Herencia múltiple
	* Como se busca eficiencia, el comportamiento es complicado.
# Funciones Virtuales
* Se acceden a través de un puntero en el objeto.
* Se pueden redefinir en subclases derivadas.
* La función exacta que se llama se determina dinámicamente.
Las funciones no virtuales son funciones comunes: No se pueden redefinir pero se pueden sobrecargar.
Las funciones son virtuales si se declaran explícitamente o se heredan como virtuales, si no, son no-virtuales.
Se paga overhead sólo si se usan funciones virtuales.
# ¿Por qué el lookup en C++ es mas simple?
* Smalltalk no tiene sistema de tipos estático
	* El código `p messaje:params` puede referirse a cualquier objeto.
	* Necesitamos encontrar un método que use el puntero del objeto.
	* Diferentes clases ponen los métodos en diferentes lugares en el diccionario de métodos.
* C++ le dá al compilador una superclase
	* El offset de los datos y los punteros a funciones son los mismos en la subclase y la superclase, se conocen en tiempo de compilación.
	* el código `p->move(x)` compila al equivalente de `(*(p->vptr[0]))(p,x)` si `move` es la primera función en la `vtable`.
# El puntero "this"
* El código se compila de forma que la función miembro toma al objeto mismo como primer argumento
	* Código          `int A::f(int x) { ... g(i) ...;}
	* Compilado   `int A::f(A *this, int x) { ... this->g(i) ...;}
* El puntero "***this***" se puede usar en la función miembro, para devolver el puntero del objeto, pasar el puntero del objeto a otra función, etc.
* igual al "self" de Smalltalk.
# Funciones NO virtuales
El código para funciones no virtuales se encuentra igual que para las funciones comunes.
* El compilador genera el código de la función y le asigna una dirección.
* La dirección se ubica en la tabla de símbolos.
* En el lugar de llamada, se obtiene la dirección de la tabla y se ubica en el código compilado.
* Pero en el caso de clases aplican algunas reglas especiales sobre alcance.
* La sobrecarga se resuelve en tiempo de compilación, a diferencia ed la consulta de una función virtual en tiempo de ejecución.
# Reglas de alcance en C++
## Calificadores de alcance
`::`, `->` y `.`
`class::member`, `ptr-> member` y `object.member`.
* global (objeto, función, enumerador, tipo): Nombre fuera de una función o clase no prefijado por `::` unario y no calificado.
* alcance de clase: Nombre después de `X::`, `ptr->` o `obj.`, se refiere a un miembro de la clase `X` o a la clase base de `X`, asumiendo `ptr` es un puntero a la clase `X` y `obj` es un objeto de la clase `X`.
# Subtipado
Subtipado en principio: A es un subtipado de B (`A<:B`) si todo objeto `A` se puede usar en un contexto en el que se necesitaba `B` sin errores de tipo.
En C++ A es un subtipado de B si la clase A tiene como clase base pública a B, esto es más débil de lo que sería necesario.
# No hay tipado sin herencia
```
class Point {
	public:
		int getX();
		void move(int);
	protected:...
	private:...
}

class ColorPoint {
	public:
		int getX)=;
		void move(int);
		int getColor();
		void darken(int);
	protected:...
	private:...
}
```
C++ no trata este `ColorPoint` como subtipado de `Point`, pero Smalltalk sí lo haría.
## ¿porque esta decisión?
El código depende sólo de la interfaz pública
* En principio, si la interfaz de `CollorPoint` contiene a la interfaz de `Point` los clientes podrían usar `ColorPoint` en lugar de `Point` (como en Smalltalk).
* Pero el offset en la tabla de funciones virtuales puede ser distinta, y de esta forma perder eficiencia (como en Smalltalk).
Si no funciona ligada a la herencia, el subtipado lleva a pérdida de eficiencia.
También por encapsulación: El subtipado basado en herencia se preserva si hacemos modificaciones en la clase base.
# Clase abstracta
Una clase abstracta es una clase sin implementación completa.
Se declara con `=0`.
Útil porque puede tener clases derivadas
* Como el subtipado se sigue de la herencia en C++, se pueden usar las clases abstracta para construir jerarquías de subtipado.
Establece la disposición de la `vtable` (tabla de funciones virtuales).
# Como resolver el choque de nombre (*Name Clashes*)
![](NamesClashes.png)
Resolución Implícita: Con reglas arbitrarias.
Resolución Explícita: El programador debe resolver los conflictos explícitamente. **Esta usa C++**.
## Resolución explícita
Reescribir la Clase `C` para llamar a `A::f` explícitamente
```c++
class C: public A, public B {
	public:
		void virtual f() {
			A::f(); //llama A::f(), no B::f()
		}
}
```
Elimina ambigüedad.
Preserva la dependencia de A, los cambios de `A::f` cambiarán `C::f`.
# Herencia diamante en C++
![](HerenciaMultipleDiamante.png)
Problema: Clases base estándares, los miembros de D ocurren dos veces en C
Solución: Clases base virtuales
`class A: public virtual D { ... }`
* Se evita el duplicado de los miembros de la clase base.
* Se requieren punteros adicionales para compartir la parte D de A y B.
La herencia múltiple en C++ es complicada en parte porque quiere mantener lookup eficiente.
![](CajitaHerenciaDiamante.png)
# Resumen
* Objeto
	* Creados por clases
	* Contienen datos del miembro y un puntero a la clase
* Clases: Tabla de funciones virtuales
* Herencia
	* Clases base públicas y privadas, herencia múltiple.
* Subtipado: Sólo con clases base públicas.
* Encapsulación
	* Un miembro se puede declarar público, privado o protegido.
	* La inicialización de los objetos se puede forzar parcialmente.
