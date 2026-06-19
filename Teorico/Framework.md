## ¿Qué es una Framework?
* Es una abstracción más.
* Una abstracción de software que provee **Funcionalidades Genéricas** que se pueden **instanciar** en software específico para una aplicación.
* Un método **Estándar** para desplegar aplicaciones.
* Un **Entorno** de software reusable que provee un estándar para crear aplicaciones (evolución de IDE).
* Pueden tener todos los **Componentes** necesarios para desarrollar un proyecto o sistema: Programas de apoyo, compiladores, librerías de código, herramientas, APIs.
En un entorno OO, un framework tiene clases abstractas y concretas. Cuando se instancia se componen esas clases y se crean subclases.
### Frozen spots
Definen la arquitectura del sistema (componentes básicos y sus relaciones), inalterables.
### Hot spots
Son las partes donde los programadores añaden el código específico del proyecto.
## Decisiones de diseño
* Reducir el tiempo de desarrollo de aplicaciones con un núcleo común (webs para acceso a servicios, home banking, etc).
* Consolidar un núcleo de procesamiento básico.
* Dedicar más tiempo a los requisitos de la aplicación específica, y no a los detalles de bajo nivel, que son muy estándares.
* Reducir errores de bajo nivel.
## Desventajas
* Código muy voluminoso para aplicaciones potencialmente chicas (code bloat).
	* Se mantienen funcionalidades deprecadas.
* Tiempo de aprendizaje largo, sólo rinde si se usa para más de un desarrollo.
## Diferencias con librerías
* **Inversión de Control**:
	* El flujo del programa está determinado por el framework, no por el programador (principio de Hollywood: no nos llame, nosotros lo llamaremos a usted)
* **Extensibilidad**:
	* Se puede extender por sobreescritura selectiva (*override*), o con código añadido para funcionalidades específicas (add-on).
* **No se puede modificar** el código del framework.
## Arquitectura MVC
MVC = Model - view - controller
* Separar el modelo de datos (*model*) de interfaz de usuario (*view*) de las reglas de negocio (*controller*).
* Código modularizado.
* Reuso.
* Diferentes interfaces: Usuario (página html), web services, ...
### Arquitectura MVC basadas en acciones o componentes
* Arquitectura basada en **acciones** (*push-based*):
	* Las acciones realizan el procesamiento necesario, y después empujan (push) los datos a la capa de visualización para mostrar los resultados.
* Arquitectura basada en **Componentes** (*pull-based*):
	* La capa de visualización toma resultados de diferentes controladores a medida que los necesita. Se pueden manejar múltiples controladores con una sola vista.
## Asistentes de programación y frameworks
* Confiabilidad estadística:
	* Existen muchos ejemplo de programas hechos con frameworks de los que un LLM puede inferir estadísticas de uso.
* Los frameworks tienen muy poca variabilidad, y muy bien contextualizada (hot spots - frozen spots).
