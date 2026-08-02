## ¿Qué es un Framework?
* Es una abstracción más
* La evolución de los boilerplate (no los diseñados específicamente como framework)
* Una abstracción de software que provee funcionalidades genéricas que se pueden instanciar en software específico para una aplicación.
* Un método estándar para desplegar aplicaciones.
* Un entorno de software reusable que provee un estándar para crear aplicaciones.
* Pueden tener todos los componentes necesarios para desarrollar un proyecto o sistema: Programa de apoyo, compiladores, librerías de código, herramientas, APIs.
### Frozen Spots
Definen la arquitectura del sistema (componentes básicos y sus relaciones), inalterables.
### Hot Spots
Son las partes donde los programadores añaden el código específico del proyecto.
### Clases
En un entorno orientado a objetos, un framework tiene clases abstractas y concretas. Cuando se instancia se componen esas clases y se crean subclases.
## Decisiones de Diseño
* Reducir el tiempo de desarrollo de aplicaciones con un núcleo común.
* Consolidar un núcleo de procesamiento básico.
* Dedicar más tiempo a los requisitos de la aplicación específica, y no a los detalles de bajo nivel, que son muy estándares.
* Reducir errores de bajo nivel.
## Desventajas
Códigos muy voluminoso para aplicaciones potencialmente chicas (*code bloat*).
* Se mantiene funcionalidades deprecadas.
Tiempo de aprendizaje largo, sólo rinde si se usa para más de un desarrollo.
## Diferencias con Librerías
* Inversión de control
	* El flujo del programa está determinado por el framework, no por el programador (principio de Hollywood)
* Extesibilidad
	* Se puede extender por sobreescritura selectiva (override), o con código añadido para funcionalidades específicas (add-ons).
* No se puede modificar el código del framework
## Arquitectura MVC
MVC = Model - View - Controller
* Separar el modelo de datos (model) de interfaz de usuario (view) de las reglas de negocio (controller).
* Código modularizado.
* Reuso.
* Diferentes interfaces
### Arquitectura MVC basada en acciones o componentes
* Arquitectura basada en **Acciones** (push-based)
	* Las acciones realizan el procesamiento necesario, y después empujan (push) los datos a la capa de visualización para mostrar los resultados.
* Arquitectura basada en **Componentes** (pull-based)
	* La capa de visualización toma resultados de diferentes controladores a medida que los necesita. Se pueden manejar múltiples controladores con una sola vista.
## Asistentes de Programación y Framework
* Confiabilidad Estadística
	* Existen muchos ejemplos de programas hechos con framework de los que un LLM puede inferir estadística de uso.
* Los frameworks tienen muy poca variabilidad y muy bien contextualizada.
