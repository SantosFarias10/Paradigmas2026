## ¿Qué es un agente?
- Un agente es una componente de software mínima y simple que separa claramente la interfaz de la implementación, de modo que la interfaz externa se mantiene sencilla mientras que el desarrollo interno puede ser tan complejo como sea necesario.
- Posee un comportamiento autónomo y muy especializado, programado directamente para actuar. Se utiliza para automatizar, delegar decisiones intermedias y agilizar o agenciar acciones humanas como comprar o comunicar.
- En tiempo de ejecución se representa como un proceso liviano (un hilo o *thread*) dentro de un entorno concurrente. Funciona de manera independiente al resto de la computación bajo un esquema declarativo, lo que le otorga la robustez necesaria para recuperarse de eventos imprevistos.
- Su construcción constituye una evolución o abstracción de mecanismos computacionales conocidos, tales como modelos de lenguaje, modelos predictivos o heurísticas, los cuales le dan la capacidad de razonar mediante analogías sin caer en una antropomorfización directa.
## ¿Cómo funciona un sistema multiagente?
- Comunicación basada en mensajes
	- Los agentes interactúan con el entorno y con otros agentes exclusivamente mediante mensajes que operan sobre sus interfaces sin acceder a su implementación interna, respetando estrictamente el encapsulamiento.
	- Estos mensajes pueden interpretarse conceptualmente como parámetros.
- Programación reactiva e inversión de control
	- Siguen el principio de la programación reactiva a través de la inversión de control y la inyección de dependencias (el _principio de Hollywood_: "no nos llames, nosotros te llamaremos"), lo que permite que el entorno determine diferentes modos y efectos sobre el comportamiento del sistema.
## Eficacia del paradigma (Reducción del esfuerzo humano)
- Proporciona una abstracción sumamente valiosa para modelar sistemas complejos, facilitando la representación de problemas que no se pueden describir fácilmente de forma determinística.
- Las primitivas nativas de concurrencia declarativa y encapsulamiento proveen un marco de seguridad matemática sobre el comportamiento final del sistema.
## Eficiencia (Optimización de recursos computacionales)
- Es nativamente distribuido.
- Es elástico, puesto que tiene la capacidad de ajustar dinámicamente el uso de recursos de hardware para satisfacer diferentes demandas de carga (pudiendo escalar a muchos o pocos procesos concurrentes según se necesite).
- Es resiliente, ya que está diseñado para tolerar y recuperarse de todo tipo de fallos, incluyendo fallos a nivel de software (procesos individuales) o fallos a nivel físico (hardware).
## Ecosistemas de programación agentiva
- La arquitectura para soportar agentes requiere definir un protocolo de comunicación común, los servicios disponibles, los marcos de orquestación y un lenguaje de programación agentiva, destacándose en este ámbito el uso de Scala con la biblioteca Akka.