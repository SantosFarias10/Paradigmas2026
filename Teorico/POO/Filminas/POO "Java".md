# Objetivos de diseño
* **Portabilidad** a todo el internet
* **Confiabilidad**, evitar crashes y mensajes de error.
* **Seguridad**, programadores maliciosos.
* **Simplicidad y Familiaridad**, atractivo para programadores, más sencillo que C++.
* **Eficiencia**, importante pero secundaria.
# Decisiones de diseño generales
* Simplicidad
	* Casi todo es un objeto.
	* Los objetos están en el heap, y se acceden a través de punteros.
	* No hay funciones, ni herencia múltiple, ni `go to`, ni sobrecarga de operadores y pocas coerciones automáticas de tipo.
* Portabilidad
	* El intérprete de bytecode está en muchas plataformas.
* Confiabilidad y seguridad
	* Código fuente tipado y lenguaje bytecode tipado
	* Tipado en ejecución.
	* Recolección de basura.
# El sistema Java
* Compilador y sistema de ejecución
	* El programador compila el código.
	* El código compilado se transmite por la red.
	* El receptor lo ejecuta en el intérprete (JVM).
	* Comprobaciones de seguirdad antes y durante la ejecución.
* Biblioteca, incluyendo gráficos, seguridad, etc.
	* Una biblioteca extensa que hace fácil adoptar Java para proyectos.
	* Interoperabilidad.
# Terminología
* Clase, objeto; Como en los otros lenguajes.
* Campo, miembro datos.
* Métodos, miembro función.
* Miembros estáticos, campos y métodos de la clase.
* this, self.
* Paquete, conjunto de clases en un mismo espacio de nombres (*namespace*).
* Método nativo, método escrito en otro lenguaje.
# Objetos y clases
* Objeto
	* Tiene campos y métodos
	* Alojado en el heap, no en la pila de ejecución.
	* Se accede a través de referencia (es la única asignación de puntero).
	* Con recolección de basura.
* Lookup dinámico.
	* Comportamiento semejante a otros lenguajes.
	* tipado estático => más eficiente que Smalltalk.
	* Linkeado dinámico, interfaces => más lento que C++.
# Inicialización de objetos
Java garantiza la llamada al constructor para cada objeto
* Se aloja memoria
* Se llama al constructor para inicializar memoria.
Los campos estáticos de la clase se inicializan en tiempo de carga.
# Garbage collection y finalize
Los objetos pasan por la recolección de basura.
* No hay una instrucción free explícita.
* Evita punteros colgantes.
Problema, que pasa si un objeto ha abierto un archivo o tiene un lock?
solución, método ***finalize***, llamado por el garbage collector.
# Encapsulación y packages
Todos los campos y métodos pertenecen a una clase.
Cada clase es parte de algún package.
* Puede ser un package por defecto, sin nombre.
* El archivo declara a qué package pertenece el código.
# Visibilidad y acceso
Cuatro distinciones de visibilidad, `public, private, protected, package`.
Un método se puede referir a:
* Los miembros privados de la clase a la que pertenece.
* Miembros no privados de todas las clases del mismo package.
* Miembros protected de superclases, en distintos packages
* Miembros public de clases en packages visibles, donde la visibilidad está determinada por el file system.
Nombre calificados (o usando import)
![](NombresCalificadosJava.png)
# Herencia
Semejante a Smalltalk y C++.
Las subclases heredan de las superclases.
* Herencia simple únicamente, pero Java tiene interfaces.
Algunas características adicionales.
* Clases y métodos final (no se pueden heredar).
# Clase Object
Todas las clases extienden otras clases
* Si no se explicita otra clase, la superclase es Object
Método de una clase Object
* GetClass, devuelve el objeto Class que representa la clase del objeto.
* ToString, devuelve la representación en string del objeto.
* equals, equivalencia de objetos por defecto (no de punteros).
* hashCode
* Clone, hace un duplicado de un objeto.
* wait, notify, notifyAll, para concurrencia.
* finalize.
# Constructores y Super
Java garantiza una llamada a constructor para cada objeto.
La herencia tiene que preservar esta propiedad
* El constructor de subclase tiene que llamar al constructor de superclase.
	* Si el primer statement no es una llamada a super, el compilador inserta la llamada a super() automáticamente.
	* Si la superclase no tiene un constructor sin argumento, causa un error de compilación.
	* Excepción: Si un constructor llama a otro, entonces el segundo constructor es el responsable de llamar a super
		* `ColorPoint() { ColorPoint(0, blue);}
		* se compila sin insertar la llamada a super.
# Clases y métodos finales
* Restringen herencia
	* Las clases y métodos finales no se pueden redefinir, por ejemplo `java.lang.String`
* para qué sirve
	* Importante para seguridad
		* El programador controla el comportamiento de todas las subclases, crítico porque las subclases producen subtipados.
	* Si lo comparamos con virtual/non-virtual en C++, todo método es virtual hasta que se hace final.
# Tipos
* Dos clases generales de tipos
	* Tipos Primitivos que no son objetos: Enteros, booleanos.
	* Tipos de referencia, clases, interfaces, arrays.
* Chequeo estático de tipos.
	* Toda expresión tiene tipo, determinado por sus partes
	* Algunas conversiones automáticas, muchos casteos se comprueban en tiempo de ejecución.
# Subtipado
* Tipos primitivos
	* Conversiones: `int -> long`, `double -> long`
* Subtipado de clase semejante a C++
	* Una subclase produce un subtipado.
* Interfaces
	* Clases completamente abstracta, sin implementación.
	* Subtipado múltiple: Una interfaz puede tener múltiples subtipos, que la implementan, la extienden.
## Subtipado en interfaces: Ejemplo
```Java
interface Shape {
	public float center();
	public void rotate(float degrees);
}
interfaces Drawable {
	public void setColor(Color c);
	public void draw();
}
Class Circle implements Shape, Drawable {
	//No hereda ninguna implementación
	//Pero tiene que definir los métodos de Shape y Drawable
}
```
# Propiedades de las interfaces
* Flexibilidad
	* Permite un grafo de subtipado, en lugar de un árbol.
	* Evita problemas con herencia múltiple de implementaciones (como la herencia en diamante de C++).
* Coste
	* No se conoce el offset en la tabla de consulta de métodos (method lookup table) en tiempo de compilación.
	* Hay diferentes bytecode para consulta de métodos:
		* Uno cuando se conoce la clase
		* Otro cuando sólo se conoce la interfaz.
# Excepciones
* Funcionalidad semejante a otros lenguajes
	* Construcciones para `throw` y `catch`.
	* alcance dinámico.
* Algunas diferencias
	* Una excepción es un objeto de una clase excepción.
	* Subtipado entre clases excepción.
		* Se usa subtipado para matchear el tipo de una excepción o pasarlo (semejante a ML).
	* El tipo de cada método incluye las excepciones que puede lanzar, todas subclases de `Exception`.
## Bloque try/finally
Las excepciones se capturan en bloques `try`
```java
try {
	statements
} catch (ex-type1 identifier1) {
	statements
} catch (ex-type2 identifier2) {
	statements
} finally {
	statements
}
```
## ¿Por qué nuevos tipos de excepción?
* Las excepciones pueden contener datos
	* La clase `Throwable` incluye un campo string para describir la causa de la excepción.
	* Se pasan otros datos declarando campos o métodos adicionales.
* La jerarquía de subtipos se usa para capturar excepciones
	* `catch <exception-type> <identifier> {...}`
	* Captura cualquier excepción de cualquier subtipo y la liga al identificador.
# Programación genérica
La clase `Object` es supertipo de todos los tipos objetos
* Esto permite polimorfismo en objetos, porque se pueden aplicar las operaciones de la clase `T` a toda subclase `S<:T`.
## Ejemplo de construcción genérica: Pila
Se pueden hacer pilas para cualquier tipo de objeto, y las operaciones asociadas a pila pila funcionan para cualquier tipo
En C++ tendríamos la clase genérica `stack`
```java
template <type t> class Stack {
	private: t data; Stack<t> * next;
	public: void push(t* x) {...}
				   t* pop (  ) {...}
}
```
# Los genéricos de Java tienen comprobación de tipos
Una clase genérica usa operaciones en un tipo de parámetros
* `PriorityQueue<T> ... if x.less(y) then...`
Dos posibles soluciones:
* C++: Linkear y fijarse si todas las operaciones se pueden resolver.
* Java: Chequea tipos y compila los genéricos sin linkear.
# Implementación
Compilador y máquina virtual.
* El compilador produce bytecode.
* La máquina virtual carga clases a demanda, verifica propiedades del bytecode e interpreta el bytecode.
Por qué este diseño?
* Ya se habían usado intérpretes/compiladores de bytecode antes: Pascal, Smalltalk.
* Minimizan la parte de la implementación dependiente de máquina.
	* La optimización se hace en el bytecode.
	* Se mantiene muy simple el intérprete de bytecode.
* Para Java, también aporta portabilidad.
	* Se puede transmitir el bytecode por la red.
# Arquitectura de la JVM
![](ArquitecturaJVM.png)
## Áreas de memoria de la JVM
El programa en Java tiene uno o más threads.
Cada thread tiene su propio stack.
Todos los threads comparten el heap.
![](AreasDeMemoriaJVM.png)
## Carga de clases
El sistema de ejecución carga las clases a medida que se necesita.
* Cuando se referencia una clase, el sistema de carga busca el archivo de instrucciones de bytecode compiladas.
El mecanismo de carga por defecto se puede sustituir definiendo otro objeto `ClassLoader`.
* Se extiende la clase `ClassLoader`.
* `ClassLoader` no implementa el método abstracto `loadClass`, sino que tiene métodos que pueden usarse para implementar `loadClass`.
* Se pueden obtener bytecodes de otra fuente.
## Linker y Verificador de la JVM
Linker
* Añade la clase o interfaz compiladas al sistema de ejecución.
* Crea los campos estáticos y los inicializa.
* Resuelve nombres, reemplazándolos con referencias directas.
Verificador:
* Comprueba el bytecode de una clase o interfaz antes de que se cargue.
* Lanza la excepción `VerifyError`.
# Seguridad en Java
Seguridad
* Evitar uso no autorizado de recursos computacionales.
Seguridad en Java
* El código Java puede leer input de usuarios despistados o atacantes maliciosos.
* El código Java se puede transmitir por la red.
Java está diseñado para reducir riesgos de seguridad.
# Mecanismos de seguridad
* Sand-boxing (jugar en el arenero)
	* El programa se ejecuta en un entorno restringido.
	* Se aplica a:
		* Características del loader, verificador, e intérprete que restringen al programa.
		* Java Security Manager, un objeto especial que ejerce control de acceso.
* Firma de código
	* Se usan principios criptográficos para establecer el origen de un archivo de clase.
	* La usa el security manager.
# Java Sandbox
* Class loader
	* Namespaces distintos para distintos class loaders.
	* Asocia un protection domain con cada clase.
* Test en tiempo de ejecución del verifier y JVM.
	* No se permiten casteos sin comprobación de tipos ni otros errores de tipo, no se permite array overflow.
	* Preserva los niveles de visibilidad private y protected.
* Security Manager
	* Lo llaman las funciones para decidir si deben hacer lugar a un pedido.
	* Usa el protection domain asociado el código y política de usuario.
# Security Manager
Las funciones de la biblioteca de Java llaman al security manager
Respuesta en tiempo de ejecución
* Decide si el código que llama tiene permiso para hacer la operación.
* Examinar el dominio de protección de la clase que llama.
	* Signer: Organización que firmó el código antes de cargarlo.
	* Ubicación: URL de donde vienen las clases.
* Da permiso de acceso según la política del sistema.
# Inspección del stack
El permiso depende de:
* Permiso del método que llama
* Permiso de todos los métodos por encima de él en el stack, hasta llegar a un método confiable
# Resumen
* Objetos:
	* Tienen campos y métodos
	* Alojados en el heap, se acceden con punteros, con recoleeción de basura.
* Clases:
	* Public, private, protected, packages.
	* Pueden tener miembros estáticos (propios de la clase).
	* Constructores y métodos finalize.
* Herencia:
	* Herencia simple
	* Métodos y clases finales (no pueden tener hijas).
* Subtipado
	* Determinado por la jerarquía de herencia.
	* Una clase puede implementar muchas interfaces.
* Virtual machine
	* Carga bytecode para clases en tiempo de ejecución.
	* El verificador comprueba el bytecode.
	* El intérprete también hace comprobaciones en tiempo de ejecución.
		* Casteos
		* Límites de arreglos.
* Portabilidad y seguridad
