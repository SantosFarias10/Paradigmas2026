## ¿Qué es un Lenguaje de Scripting?
Un lenguaje de scripting es un lenguaje diseñado principalmente para actuar como "código bisagra" o "lenguaje pegamento" (glue language), cuya función esencial es conectar, coordinar y facilitar la comunicación entre diferentes componentes de software o las salidas y entradas de programas independientes.
A diferencia de los lenguajes de sistemas convencionales diseñados para crear aplicaciones totalmente autónomas, los scripts están pensados para ser rápidos de aprender y escribir. Se caracterizan por ejecutarse típicamente de principio a fin (como un "guión") sin requerir un punto de entrada explícito (como la función `main` en C++ o Java), y suelen ser interpretados directamente a partir del código fuente o de un bytecode intermedio.
### Características Comunes
Los lenguajes de este paradigma comparten una serie de decisiones de diseño enfocadas en la flexibilidad y en minimizar el esfuerzo de escritura del programador:
- Bajo nivel de verbosidad:
	- Se escribe sustancialmente menos código que en lenguajes estructurados tradicionales.
- Sin declaraciones obligatorias y con reglas de alcance simples:
	- No exigen definir las variables antes de usarlas, y sus ámbitos de visibilidad suelen ser automáticos (por ejemplo, en Python las variables son locales por defecto al bloque donde se les asigna un valor).
- Tipado dinámico flexible:
	- El tipo de una variable no se restringe estáticamente, sino que se comprueba en tiempo de ejecución justo antes de su uso. Suelen permitir conversiones implícitas de tipos (casteos muy dinámicos).
- Tipos de datos de alto nivel nativos:
	- Incorporan estructuras de datos complejas como listas, conjuntos, tuplas y diccionarios (tablas de hash optimizadas) de manera nativa en el lenguaje, sin necesidad de importar módulos externos.
- Procesamiento de texto y coincidencia de patrones (pattern matching) avanzado:
	- Cuentan con un soporte extremadamente sofisticado y eficiente para manipular cadenas de caracteres basado en expresiones regulares.
- Gestión automática de memoria:
	- Utilizan recolección de basura (Garbage Collection) para liberar la memoria de forma transparente al usuario.
- Abstracción del bajo nivel:
	- Ocultan deliberadamente los detalles técnicos de la representación de datos físicos y el direccionamiento en memoria, permitiendo que el programador se concentre puramente en la lógica del sistema.
