### ¿Qué es un tipo y para qué sirve?
Un Tipo se define como una colección de valores computables que comparten alguna propiedad estructural (por ejemplo, Int, String, funciones de Int -> Bool).
#### Funciones Principales de los tipos
* Organización y Documentación: Representan los conceptos del dominio del problema y se pueden verificar automáticamente.
* Prevención de errores: Evitan cálculos sin sentido.
* Optimización: Permiten optimizar el uso de la memoria porque el compilador puede calcular de antemano el tamaño de los datos.
## Clasificación del Tipado
### Estático vs. Dinámico
* Estático: Los tipos de las variables se fijan durante la compilación.
* Dinámico: El tipo de una variable depende de su valor y solo se conoce en tiempo de ejecución.
### Fuerte vs. Débil
* Fuerte: Detecta todos los errores de tipo (ya sea al compilar o al ejecutar) y obliga a que las conversaciones de tipos sean explícitas.
* Débil: Permite flexibilidad pero es propenso a errores que pasan desapercibidos.
## Chequeo de Tipos: Expresividad vs. Seguridad
* Tiempo de Comprobación: Comprobar en tiempo de ejecución hace que el programa sea más lento, mientras que hacerlo en tiempo de compilación restringe la flexibilidad (es conservador).
	* Por ejemplo, en lenguajes dinámicos como JS se pueden escribir expresiones muy flexibles (`x < 10 ? x : x()`) que un lenguaje estático rechazaría  al compilar por miedo a un error de ejecución.
## Jerarquía y composición de Tipos
* Primitivos: Tipos atómicos o indivisibles como booleanos, enteros, reales y caracteres.
* Compuestos: Estructuras formadas por partes, como listas, arreglos tuplas, diccionarios, clases y funciones.
* Subtipos: Tipos que aplican restricciones adicionales a sus valores u operaciones, siendo un concepto fundamenta para la Orientación a Objetos.
* Tipos Definidos por el Usuario: Permite modelar estructuras propias del negocio (como crear un tipo `Shape` en Haskell).
## Conceptos Avanzados de Resolución
El apunte cierra distinguiendo tres conceptos clave sobre cómo los lenguajes manejan los tipos:
* Inferencia de Tipos: El lenguaje determina automáticamente cuál es el mejor tipo para una expresión según cómo se usan sus símbolos.
* Polimorfismo: Un único algoritmo genérico que puede trabajar con datos de diferentes tipos.
* Sobrecarga (Overloading): Un mismo nombre o símbolo que agrupa múltiples algoritmos distintos, y el compilador decide cuál usar basándose en el tipo de los argumentos.
