# Machine Learning
* La programación asistida siempre ha existido, pero era determinista y no lograba capturar las "causas latentes" de los problemas.
* Los sistemas de IA antiguos se basaban en **sistemas expertos** (reglas explicitas de "si pasa X, entonces Y"), pero fracasaron porque los problemas humanos son demasiados complejos.
* El **Aprendizaje Automático** invierte la lógica: En lugar de darle a la computadora los datos y el programa para obtener una salida, le damos los datos y las salidas esperadas para que la computadora aprenda el programa.
## Tipos de Aprendizaje Automático
El documento clasifica cómo aprenden los modelos en base a distintas tareas:
- **Supervisado**: Se usa para predecir a partir de datos etiquetados. Incluye la regresión (predecir valores continuos) y la clasificación (separar en categorías).
- **No Supervisados**: Busca entender la estructura interna de los datos, como en el *clustering* (agrupamiento).
- **Auto-Supervisado**: El modelo genera sus propios datos de entrenamiento mediante "tareas pretexto" (por ejemplo, predecir la siguiente palabra en un texto o ocultar una parte de una imagen y adivinarla). Esto permite con cantidades de datos sin etiquetar.
- **Por Refuerzo**: Un agente aprende a tomar secuencias de acciones en un entorno basándose en un sistema de recompensas (*rewards*).
# Redes Neuronales y Embeddings
Una red neuronal está formada por múltiples clasificadores simples (como el perceptrón, que hace una regresión lineal básica) conectados entre si y se entrena mediante un algoritmo llamado *backpropagation*.
* *Embeddings*: Al entrenar una red con tareas pretexto y luego quitarle la capa final de predicción, la capa anterior se convierte en una nueva forma de representar los datos. Estos embedding capturan características profundas (causas latentes) y permiten entender la información de forma más rica.
# Modelos de Lenguaje y Prompt Engineering
Los modelos de lenguaje como GPT aprenden, fundamentalmente, jugando a predecir la siguiente palabra.
¿Por qué inventan (alucinan)? Porque están diseñados para generar el texto estadísticamente más probable dado un contexto, independientemente de si es un hecho real o no.
El *Prompt Engineering* consiste en darles el contexto adecuado (instrucciones, personalidad o documentos externos mediante RAG) para guiar la generación de ese contenido y reducir errores.
# Asistentes de Programación
Tienen fortalezas y debilidades muy claras:
* **Capacidades**: Aumentan enormemente la velocidad para escribir código, tienen una excelente atención al detalle sintáctico y detectan problemas frecuentes rápidamente.
* **Limitaciones**: Son malos resolviendo casos poco frecuentes, carecen de conciencia sobre sus propios errores (son muy complacientes), pueden ser demasiado verbosos y tienen un altísimo costo económico y energético.
# El Futuro: Agentes de IA
Más allá de un simple chatbot, un **agente** es un programa que funciona de forma autónoma. La IA actual se está moviendo hacia flujos de trabajo "agentivos" (*Agentic Workflows*) que aplican patrones de diseño complejos: Reflexionar sobre sus propias respuestas, usar herramientas externas (APIs, búsqueda web), planificar tareas por pasos y colaborar en sistemas multi-agente.
