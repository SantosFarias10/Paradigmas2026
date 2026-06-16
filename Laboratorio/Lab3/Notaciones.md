Un framework de computación distribuida como **Spark** impone una inversión de control: 
* El desarrollador no escribe el código que coordina los workers ni decide cómo se distribuyen los datos. En cambio, expresa el cómputo usando las abstracciones que el framework provee (`map`, `flatMap`, `reduceByKey`, etc.) y Spark se encarga del resto. 
* El punto caliente (hot spot) donde el **desarrollador interviene** es la función que se pasa a cada transformación; todo lo demás (scheduling, serialización, tolerancia a fallos) queda bajo control del framework.

# Cositas

## Spark
Spark es un framework de computación en clúster unificada. Su magia es que te permite escribir código que parece que se ejecuta en una sola máquina, pero por detrás, Spark distribuye el trabajo de forma automática entre múltiples núcleos o computadoras (nodos).

### El modelo Driver / Worker
* Driver (El Director de Orquesta): Es el programa principal que vos ejecutás (donde definís la SparkSession). Centraliza la lógica, crea el plan de ejecución y distribuye las tareas. En tu ejercicio, al poner master("local[*]"), le estás diciendo al Driver que use todos los núcleos de tu procesador local como si fueran distintas máquinas de un clúster.
* Workers / Executors (Los Obreros): Son los encargados de recibir las tareas del Driver, procesar los fragmentos de datos asignados y devolver los resultados.

## ¿Qué es un RDD?
RDD "estructura de datos inmutable". Sus siglas significan Resilient Distributed Dataset (Conjunto de Datos Distribuido y Resiliente):
* Distributed (Distribuido): Si tenés un RDD con 1000 URLs, Spark no las guarda juntas. Rompe esa lista en pedazos llamados particiones y manda algunas a un núcleo, otras a otro.
* Resilient (Resiliente / Tolerante a fallos): Si un nodo falla a mitad de una descarga, Spark sabe exactamente de dónde venían esos datos y reconstruye la partición perdida sin tener que reiniciar todo el programa.
* Inmutable: No podés modificar un RDD existente. Cuando le aplicás un filtro o una transformación, Spark genera un nuevo RDD con los cambios.

## ¿Cómo funciona Spark? Transformaciones vs. Acciones
La evaluación perezosa (Lazy Evaluation). Spark no hace nada con los datos hasta que no se lo pedís explícitamente. Las operaciones se dividen en dos bandos:
* **Transformaciones** (Son perezosas)
    * Crean un nuevo RDD a partir de uno anterior. No computan nada en el momento, solo anotan la receta de lo que tienen que hacer en un gráfico dirigido (llamado DAG).
* **Acciones** (Son ansiosas)
    * Fuerzan a Spark a ejecutar todas las transformaciones anotadas para devolver un resultado final al Driver o escribirlo en un archivo.

## Entendiendo flatMap vs map
Como vas a usar flatMap para procesar el RDD de URLs, es vital ver la diferencia con el map tradicional de la programación funcional:
* map: Transforma cada elemento uno a uno. Si tenés una entrada de $N$ elementos, la salida tendrá exactamente $N$ elementos.
* flatMap: Transforma cada elemento en una colección (un iterador, una lista, etc.) y luego aplana (flattens) los resultados en un único RDD continuo.
