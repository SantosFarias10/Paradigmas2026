# ¿Que es un agente?
* Una componente de software **simple**
	* Se representa como un proceso liviano (hilo, thread).
	* Tareas que requieren elasticidad.
* Que se ejecuta en un entorno concurrente
	* Independiente del resto de la computación (declarativa)
* Separan claramente **interfaz** de implementación
	* La interfaz es simple, la implementación puede ser tan compleja como sea necesario.
* Con comportamiento **autónomo** y muy especializado.
# ¿Cómo es un sistema multiagente?
Comunicación con otros agentes y el entorno
* Por mensajes que interactúan con las interfaces sin entrar en la implementación (ejecución de métodos).
* Respetando encapsulamiento.
Los mensajes pueden pensarse como parámetros.

Programación **Reactiva**.
Inversión de control, inyección de dependencia (principio de Hollywood).
El entorno puede tener diferentes modos y efectos.
# Eficacia del paradigma de agentes
Reducir el esfuerzo de la persona para escribir el programa.
* Abstracción útil para sistemas complejos: Facilita representar problemas muy complejos que no podemos describir fácilmente de forma determinística.
* Las primitivas de concurrencia declarativa y encapsulamiento proveen cierta seguridad sobre el comportamiento del sistema.
## Eficiencia
Optimizar el uso de recursos computacionales
* Nativo Distribuido
* **Elástico**: Usa solamente los recursos necesarios para diferentes demandas (pequeñas o grandes)
* **Resiliente**: Maneja todo tipo de fallos, incluyendo fallos a nivel de proceso o de hardware.
# Ecosistemas de programación agentiva
Protocolo de comunicación.
Servicios disponibles.
Marcos de orquestación.
Lenguaje de programación agentiva - scala (akka).
