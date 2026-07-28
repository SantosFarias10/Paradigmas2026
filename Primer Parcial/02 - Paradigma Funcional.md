## Declarativo vs. Imperativo
Las construcciones más primitivas son **Imperativas**.
Las **Declarativas** describen un hecho.
Las construcciones imperativas **Cambian** un valor y las declarativas **Crean** un nuevo valor.
Los lenguajes declarativos tratan de definir el **Qué** sin explicar el **Cómo**.
### Imperativo
La asignación imperativa puede introducir *efectos secundarios*: Puede destruir el valor anterior de una variable.
En Programación Funcional se lo llama **Asignación Destructiva**.
### Operaciones Declarativo
Una operación es declarativa si siempre que la llamamos con los mismos argumentos devuelve los mismos resultados, independientemente del estado de la computación.
La composición de dos operaciones declarativas es declarativa, por lo que, podemos crear grandes programas declarativos por composición de operaciones declarativas.
Una **operación Declarativa** es:
* **Independiente**: Depende sólo de sus argumentos.
* **Sin Estado**: No recuerda ningún estado entre llamados.
* **Determinística**: Los llamados con los mismos argumentos siempre dan los mismos resultados.
#### Ventajas de los componentes declarativos
* **Programación a pequeña escala**: Es más fácil razonar sobre programas declarativos porque podemos usar técnicas algebraicas y lógicas.
* **Programación a gran escala**: Una componente declarativa se puede escribir, testear y verificar independientemente de otras componentes.
* Como las componentes declarativas son funciones matemáticas, se puede aplicar razonamiento algebraico, sustituyendo iguales por iguales.
* Se puede escribir componentes declarativas en modelos que permiten tipos de datos con estado, pero perdemos las garantías de mantener declaratividad.
## Transparencia Referencial
Una expresión **Transparente Referencialmente** se puede sustituir por su valor sin cambiar la semántica del programa.
Todas las componentes declarativas independientemente de su estructura, se pueden usar como valores:
* Como argumentos de función,
* Como resultados de función,
* Como partes de estructura de datos.
## Estado
### Estado Explícito
* El estado de la computación está siempre, también en un programa funcional (se puede diagramar la computación con los diferentes activation records que se apilan y desapilan).
* Los programas imperativos integran el estado de forma explícita: Variables globales, resultados temporales.
* Es más adecuado hablar de componentes o programas imperativos y no lenguajes imperativos.
* Los lenguajes funcionales también incluyen formas de referirse al estado.
	* Como mónadas o pasar el estado como parámetros.
#### ¿Cuándo queremos usar el estado explícito?
* Cuando queremos representar memoria.
* Cuando el entorno es determinante para el comportamiento de las componentes.
* Cuando la asignación destructiva convierte un problema en tratable.
* Cuando queremos guardar resultados temporales (como por ejemplo, en Programación Dinámica).
	* Encontrar el camino mas corto,
	* Fibonacci,
	* Alineamiento de secuencias,
	* Torres de Hanoi,
	* Multiplicación de matrices.
### Mónadas
Muchos lenguajes funcionales "puros" proveen algún tipo de construcción lingüística para poder expresar instrucciones imperativas: Las **Mónadas**.
* Crear un alcance aislado del resto del programa.
* Se permiten ciertas operaciones con efectos secundarios: Variables globales, asignación destructiva.
* "Punto y coma programable", que transportan datos entre unidades funcionales.
## Concurrencia Declarativa
Paralelizar programas declarativos es trivial: Las componentes declarativas se pueden ejecutar de forma concurrente sin que se den condiciones de carrera.
Algunas paralelizaciones son absurdas como:
* Existe dependencia entre resultados,
* El overhead es demasiado alto para la ganancia obtenida.