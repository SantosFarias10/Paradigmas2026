## ¿Qué es la Concurrencia?
Dos o más secuencias de eventos ocurren en paralelo
### Multiprogramación
* Una computadora ejecuta varios programas a la vez.
* Cada programa funciona secuencialmente.
* Las acciones de un programa pueden suceder entre dos pasos de otro.
### Multiprocesos
* Dos o más procesadores conectados.
* Los programas de un procesador se comunican con los del otro.
* Las acciones pueden suceder en simultáneo.
## La Promesa de la Concurrencia
* Velocidad
* Disponibilidad
	* Si un proceso está ocupado, otro podrá ayudar.
* Distribución
	* Los procesadores de diferentes lugares pueden colaborar en solucionar un problema o trabajar juntos.
## Retos
* Los programas concurrentes son más difíciles de programar sin comportamiento inesperados.
* Algunos problemas son inherente secuenciales, y requieren mucha coordinación y comunicación entre sub-problemas si los paralelizamos.
* Problemas a resolver:
	* Comunicación: Como recibir o enviar información
	* Sincronización: Cómo esperar a otro proceso,
	* Atomicidad: Garantizar que no se va a parar en la mitad y dejar todo embrollado.
## ¿Qué esperamos de un lenguaje concurrente? Construcciones
* Hilos (threads) como valores de expresiones
	* Se pueden pasar hilos a funciones, crear hilos, como resultado de una llamada a función.
* Abstracciones de comunicación
	* Comunicación síncrona
	* Canales con búfer asíncronos que preservan el orden de los mensajes.
* Control de concurrencia
	* Exclusión mútua
	* Alguna forma de cerrojo (lock)
	* La atomicidad no es tan común en los lenguajes.
## Resolver Conflictos entre Procesos
* Sección Crítica
	* Dos procesos que acceden a un recurso compartido
	* Comportamiento inconsistente si se intercalan dos acciones
	* Permitimos un solo proceso en la sección crítica.
* Deadlock
	* Un proceso puede estar reteniendo locks mientras está esperando a otros.
	* El deadlock ocurre cuando ningún proceso puede continuar.
## Primitivas de Exclusión Mútua
* Test-and-set atómico
	* Una instrucción lee y escribe de forma atómica
	* Es una instrucción de hardware común
	* Se combina con el bucle ocupa-en espera para implementar mutex.
* Semáforos
	* Se evita el bucle ocupado-en espera
	* se mantiene una cola de procesos en espera.
	* El scheduler tiene acceso al semáforo, el proceso duerme.
	* Se inhabilitan interrupciones durante las operaciones de semáforos.
## Estado de la Cuestión
* La programación concurrente es difícil
	* Difícil darse cuenta de cuándo se van a dar condiciones de carrera o deadlocks.
* Los lenguajes deberían ayudar con patrones, abstracciones, paradigmas útiles.
* Se necesitan otras herramientas.
	* El testing es difícil por la explosión combinatoria.
	* Hay detectores de condiciones de carrera.
		* Estático: conservadores, quizás demasiado restrictivos.
		* En ejecución: Más práctico por el momento.
## `cobegin`/`coend`
Es una primitiva de concurrencia limitada.
Sirve para delimitar un bloque de código donde varios procesos o secuencias de instrucciones secuenciales se ejecutan de manera concurrente (en paralelo)
### Propiedades de `cobegin` / `coend`
* Ventajas
	* Crea procesos concurrentes
	* Comunicación mediante variables compartidas.
* Limitaciones
	* Sin exclusión mútua.
	* Sin atomicidad.
	* El número de procesos está fijo por la estructura del programa.
	* No se pueden abortar procesos, todos se tienen que completar para que el padre pueda seguir.
## Actores
* Cada actor (objeto) tiene un script en respuesta a un input, el actor atómicamente
	* Crea nuevos actores
	* Inicia una comunicación
	* Cambia su estado interno.
* La comunicación es
	* En búfer, sin pérdida
	* No se garantiza el orden de llegada.
		* El orden es más difícil de implementar
		* El programador puede reconstruir el orden
		* La comunicación ordenada es ineficiente.
### Concurrencia
Muchos actores pueden operar concurrentemente
* No es necesario controlar explícitamente la concurrencia.
* Los mensajes enviados por un actor pueden ser recibidos y procesados por otros secuencialmente o concurrentemente.
### Pros y Contras del modelo de actores
* Lenguajes de programación de alto nivel
	* Comunicación por mensajes
	* Exclusión mútua
		* Si se envían dos mensajes, los actores reaccionan atómicamente al primero que reciben antes de ver el segundo.
	* La concurrencia es implícita.
## Concurrencia en Java
* Threads
	* Crean un Proceso creando un objeto thread o implementando una interfaz
* Comunicación
	* Variables compartidas
	* Llamadas a métodos
* Exclusión mútua y sincronización
	* Cada objeto tiene un lock
		* Método y bloques sincronizados
	* Operaciones de sincronización
		* `wait`
		* `notify`
### Threads de Java
* Un thread es un conjunto de instrucciones que se ejecutan secuencialmente
* En Java, son objetos
	* De la clase `Threads`
	* Métodos que se heredan:
		* `start`
		* `suspend`
		* `interrupt`
		* `stop`
## Interacción entre threads
* Variables compartidas
	* Dos threads pueden asignar/leer a la misma variable.
	* Responsabilidad del programador: Hay que evitar condiciones de carrera explícitamente.
* Llamadas a métodos
	* Dos threads pueden llamar a métodos del mismo objeto, y ahí se ponen en funcionamiento los locks propios del objeto.
	* Cada objeto tiene un lock interno, heredado de `Object`
	* Las primitivas de sincronización se basan en ese lock.
## Sincronización
Provee exclusión mútua
* Dos threads pueden tener acceso al mismo objeto
* Si un thread llama a un método sincronizado, el objeto se cierra (lock)
* Si otro thread llama a un método sincronizado del mismo objeto, el thread se bloquea hasta que el objeto se abre (unlock).
### Métodos sincronizados
* Se marcan con una palabra clave `synchronized`
* Máximo un método sincronizado puede estar activo
* Los métodos no sincronizados se puede llamar
* No es parte de la signatura del método
	* Un método sincronizado es equivalente a uno no sincronizado con el mismo cuerpo
	* Una subclase puede sustituir un método sincronizado con uno no sincronizado.
