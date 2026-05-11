# Problemas de la concurrencia
Condiciones de carrera en las componentes no declarativas.
## solución
Locks
Pero:
* Si tenemos pocos locks, aun hay condiciones de carrera.
* Si hay demasiados locks, es poco eficiente.
# Solución posta: Declaratividad
## Programación reactiva
Paradigma de programación declarativa
Data streams: estático (arreglos) o dinámico (generadores de eventos).
Propagación del cambio: Dependencia entre componentes.
## ¿Cómo es la propagación del cambio?
`a:= b+c`
*  imperativo: `a` recibe el resultado de `b+c` en el momento en que se evalúa la expresión, si después cambian los valores de `b` o `c`, no tiene efecto en `a`.
* Reactivo: El valor de `a` se actualiza automáticamente cada vez que cambian los valores de `b` o `c`, sin re-evaluación constante.
### Ejemplo de Uso
* Descripción de hardware (verilog), propagación de los cambios en circuito.
* Arquitectura model-view-controller (MVC): Se propagan los cambios del modelo a la vista.
# Modelo de actores
Modelo matemático de cálculo concurrente
Actor: Primitiva de cálculo concurrente.
Cuando un actor recibe un mensaje, reacciona con:
* Decisión local.
* Crear más actores.
* Enviar más mensajes.
* Determinar cómo responder al siguiente mensaje.
# Propiedades de los actores
* Componentes de software livianas
	* Muy buen rendimiento
* Cada actor se encarga de una sola tarea simple (composicional vs. monolítico)
	* Mas fácil de razonar
* Determinística: Se comunican a través de mensajes.
* La lógica más compleja emerge de la interacción entre actores: delegar tareas, pasar mensajes.
# Los actores son declarativos, pero
Los actores pueden modificar su estado  interno (privado) de forma explícita.
Sólo pueden afectar a otros actores a través de mensajes.
* Determinísticos.
* No hay sección crítica.
* No se necesitan locks.
