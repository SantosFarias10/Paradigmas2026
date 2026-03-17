Un programa es la descripción de un proceso dinámico, donde:
* La Sintaxis es el texto del programa
* La Semántica son las cosas que hace.
La Implementación de un lenguaje de programación debe transformar la sintaxis de un programa en instrucciones de maquina que se pueden ejecutar para que suceda la secuencia de acciones que se pretendía.

### Transformación de Sintaxis a Semántica
Un lenguaje de programación es un conjunto de abstracciones y empaquetamientos quizás sin correspondencia directa con la maquina.
Es necesario Traducir lenguajes de programación a instrucciones de maquinas, y es el Compilador el que hace esa traducción. Un Intérprete puede combinar traducción y ejecución.

### Compilador (o Intérprete)
El compilador se encarga de procesar la sintaxis de los lenguajes de programación. Un Intérprete incluye un compilador y la ejecución.
##### ¿Qué es un Compilador?
Un programa que lee un programa escrito en un lenguaje origen y lo traduce a un programa equivalente (con el mismo significado) en un lenguaje destino.
Tiene 2 componentes:
* Entender el programa (asegurarse de que es correcto).
* Reescribir el programa.
Normalmente, el lenguaje origen es de alto nivel y el destino es de bajo nivel.
#### Fases de un Compilador
* Análisis Léxico.
* Análisis Sintáctico.
* Análisis Semántico.
* Generación de Código Intermedio (independiente de maquina).
* Optimización de Código Intermedio.
* Generación de Código Destino (dependiente de maquina).
* Optimización de Código destino.
### Proceso de Compilación
![](ProcesoDeCompilacion.png)

## Scanner: Análisis Léxico
Se divide un programa en palabras (*tokens*).
![](Scanner.png)

## Parser: Análisis Sintáctico
Comprueba si la secuencia de tokens conforma a la especificación gramatical del lenguaje y genera el árbol sintáctico. La especificación gramatical suele representarse con una gramática independiente de contexto (*context free grammar*), que también le da forma al árbol sintáctico.
##### Ejemplo de Análisis Sintáctico
![](EjemploAnalisisSintactico.png)
#### Gramáticas Independientes de Contexto
Se definen categorías de construcciones del lenguaje, por ejemplo:
* Sentencias (`<statements>`).
* Expresiones (`<expression>`).
* Declaraciones (`declarations`).
![](GramaticasIndependientesDeContexto.png)

## Análisis Semántico
El compilador trata de ver si un programa Tiene Sentido analizando su árbol sintáctico.
Un programa sin errores gramaticales no siempre es correcto, puede haber problemas de tipo.
Por ejemplo
```
pos = init + rate * 60
```
¿Que pasa si `pos` es una clase y `init` y `rate` son enteros? El parser no puede encontrar este tipo de errores, el análisis semántico es el que los encuentra.

El compilador hace comprobaciones **Semánticas Estáticas**:
* Comprobación de tipos.
* Declaración de variables antes de su uso.
* Se usan los identificadores en contextos adecuados.
* Comprueba argumentos.
* Si hay un fallo en compilación, se genera un **Error**.

En **Tiempo de Ejecución** se comprueba:
* Que los valores de los arreglos estén dentro de los limites.
* Errores aritméticos.
* No se desreferencian los punteros si no apuntan a un objeto valido.
* Se usan variables sin inicialización.
* Si hay un fallo en ejecución, se levanta una **Excepción**.

#### Tipado Fuerte
