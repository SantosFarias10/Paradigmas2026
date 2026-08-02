## Programación Reactiva
La programación reactiva es un paradigma de programación declarativa basado en flujos de datos (_data streams_), los cuales pueden ser estáticos (como los arreglos) o dinámicos (como los generadores de eventos de usuario). Su principio fundamental es la propagación del cambio, estructurando el programa como un flujo de datos orientado (_data flow_).
### Comparación de la propagación (a := b + c):
- En el modelo imperativo: `a` recibe el resultado de la suma únicamente en el momento en que se evalúa la expresión; si más adelante cambian los valores de `b` o `c`, el valor de `a` no se ve afectado.
    - En el modelo reactivo: El valor de `a` se actualiza automáticamente cada vez que se modifican los valores de `b` o `c`, sin necesidad de realizar reevaluaciones constantes ni reejecutar código explícitamente.
## El Modelo de Actores
Es un modelo matemático de cálculo concurrente. En este modelo, el actor es la primitiva fundamental de cómputo. Los actores son entidades puramente reactivas que permanecen dormidas hasta que reciben un mensaje.
Cuando un actor recibe un mensaje, reacciona ejecutando su script local, el cual le permite realizar únicamente las siguientes acciones:
* Tomar una decisión local.
* Crear nuevos actores.
* Enviar más mensajes (a otros actores o a sí mismo).
* Especificar un comportamiento de reemplazo, que define cómo se comportará el actor al recibir el siguiente mensaje (lo que equivale a un cambio de estado atómico sin asignación destructiva local).
### Propiedades de los Actores
- Livianos y eficientes
	- Son componentes de software de bajo peso (_light-weight_) con un excelente rendimiento.
- Composición simple
	- Cada actor se encarga de una única tarea sencilla, lo que facilita razonar sobre la lógica del sistema.
- Ausencia de sección crítica
	- Al comunicarse exclusivamente mediante mensajes, no requieren locks ni mecanismos de sincronización explícitos, eliminando de raíz condiciones de carrera y bloqueos.
- Mensajería asíncrona
	- El pasaje de mensajes es asíncrono, no bloqueante, almacenado en buffers (_mailbox_) y sin garantías sobre el orden de llegada.
- Casos de aplicación
	- Son ideales para sistemas de mensajería masiva y servicios web.
## La Plataforma Akka
Akka es una biblioteca (_toolkit_) y entorno de ejecución diseñado para simplificar la construcción de aplicaciones concurrentes y distribuidas sobre la Máquina Virtual de Java (JVM), utilizando bindings para **Java y Scala**.
Akka aporta características exclusivas al modelo de actores tradicional:
- Transparencia de localización
	- La interacción entre actores es idéntica independientemente de si residen en el mismo host físico, en servidores remotos distintos, o bajo diferentes configuraciones de hilos. 
	- Esto permite escalar la infraestructura horizontal y verticalmente mediante configuraciones externas sin modificar el código fuente del programa.
- Supervision parental obligatoria
	- En Akka los actores se organizan en una jerarquía estricta donde cada actor es supervisado directamente por el actor "padre" que lo creó.
	- Las fallas de un actor hijo se tratan como eventos que debe resolver su supervisor directo.
- Estructura modular
	- Además de la biblioteca de actores principal, incluye módulos especializados para distribución en red, soporte de clusters, persistencia de comandos y eventos (_Command and Event Sourcing_), integración con sistemas de terceros y soporte para otros modelos concurrentes como _Futures_ y _Agents_.