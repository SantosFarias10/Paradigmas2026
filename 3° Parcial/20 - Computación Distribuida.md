## El Problema de Escala y la Coordinación
### Necesidad
Cuando el cómputo de tareas masivas tarda días o los datos no caben en una sola máquina, la solución es repartir los datos y el cómputo entre muchos nodos.
### Desafío
A diferencia de la concurrencia tradicional en una sola máquina (basada en memoria compartida, locks y condiciones de carrera), el cómputo distribuido carece de memoria compartida. Cada nodo posee su propia RAM y disco, la comunicación se realiza por red (con latencias variables) y el framework debe coordinar máquinas que fallan o se desconectan continuamente.
## Dividir y Conquistar sobre Datos: Map y Reduce
Para resolver la complejidad de la coordinación de forma automática, se utilizan abstracciones funcionales basadas en la independencia de las operaciones sobre los elementos de las colecciones:
### Map (Transformación)
Aplica una función f a cada elemento de una colección de manera independiente. Como no existen dependencias entre los elementos, la operación es paralelizable de forma trivial (cada nodo procesa su fragmento local sin comunicarse con los demás).
### Reduce (Agregación)
Combina todos los elementos en un único resultado aplicando una función acumulativa f. Para que el orden de combinación entre nodos no altere el resultado (permitiendo la reducción local en cada nodo antes de combinar resultados parciales), la función debe ser asociativa y conmutativa.
## El Patrón Map-Reduce
Es una estructura de cómputo fija organizada en tres fases secuenciales administradas por un framework:
1. Map
	* Cada elemento de entrada produce uno o más pares de tipo `(clave, valor)`.
2. Shuffle (Mezcla)
	* El framework agrupa todos los pares intermedios que comparten la misma clave y los envía al nodo encargado de procesarlos.
3. Reduce
	* Para cada clave única, se combinan todos sus valores asociados para obtener el resultado consolidado.

El desarrollador solo se encarga de escribir las funciones específicas de `map` y `reduce`, mientras que la distribución de tareas, fallos y balanceo de red quedan delegados al framework.
## Hadoop y sus Limitaciones
- Arquitectura
	- Hadoop fue el primer framework Map-Reduce a gran escala. Se apoya sobre dos pilares: HDFS (un sistema de archivos distribuido que divide archivos en bloques y los replica por defecto en 3 nodos distintos para tolerar fallos físicos bajo la coordinación de un _NameNode_ y múltiples _DataNodes_) y MapReduce (el motor de ejecución).
- Principio de diseño
	- Su premisa es "mover el cómputo hacia los datos" (procesar allí donde el bloque de HDFS ya está almacenado físicamente) y no al revés, reduciendo el tráfico de red.
- Limitaciones
	- Hadoop es un modelo de dos fases muy rígido. Obliga a que cada paso intermedio del pipeline escriba y lea sus resultados de disco antes de avanzar al siguiente, lo que genera cuellos de botella de latencia física insostenibles para algoritmos iterativos complejos (como grafos o Machine Learning).
## Apache Spark: Cómputo en Memoria y RDDs
- La evolución
	- Apache Spark surge para solventar la limitación de Hadoop manteniendo los datos intermedios directamente en la memoria RAM entre los distintos pasos del pipeline, logrando ser hasta 100 veces más rápido.
- El RDD (Resilient Distributed Dataset)
	- Es la abstracción central de Spark. Consiste en una colección de elementos inmutable, particionada y distribuida en clúster.
- Evaluación Perezosa (Lazy)
	- Las operaciones sobre un RDD se dividen en Transformaciones (construyen un nuevo RDD a partir de otro de forma perezosa: `map`, `filter`, `flatMap`, `reduceByKey`) y Acciones (disparan el grafo de ejecución DAG y devuelven el resultado al programa controlador o _driver_: `collect`, `count`, `take(n)`, `saveAsTextFile`). Nada se ejecuta físicamente en el clúster hasta que se invoca una acción.
- Tolerancia a fallos por linaje (Lineage):
	- En lugar de duplicar los datos escribiendo a disco de manera redundante, cada RDD mantiene guardada la información de su "linaje" (las transformaciones exactas que lo crearon a partir del almacenamiento estable). Si un nodo falla y se pierde una partición de memoria, Spark simplemente vuelve a ejecutar las transformaciones necesarias para reconstruir esa partición específica.
## Inversión de Control y Criterios de Uso
- Inversión de Control
	- El programador suministra funciones puras que deben ser serializables y sin estado compartido, mientras que el framework asume la responsabilidad de particionar los datos, programar las tareas en los ejecutores libres (_scheduling_), orquestar la fase de _shuffle_ y manejar la tolerancia a fallos.
- Cuándo usar Spark
	- Es ideal cuando el volumen de datos excede la RAM de una sola máquina física y el cómputo puede particionarse en transformaciones funcionales independientes sobre colecciones.
- Cuándo evitarlo
	- No es óptimo si los datos caben cómodamente en una sola máquina (el overhead de red y serialización haría el programa más lento), si el algoritmo tiene fuertes dependencias secuenciales estrictas que impiden la paralelización, o si se requiere latencia de respuesta en microsegundos.