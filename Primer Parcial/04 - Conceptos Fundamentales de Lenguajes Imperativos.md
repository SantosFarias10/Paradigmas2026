# Paradigma Imperativo
Un **Paradigma de Programación** es una Configuración Frecuente de característica de lenguajes de programación.
El **Paradigma Imperativo** es el más antiguo y el que estuvo siempre más pegado a la máquina.
Tradicionalmente se ha opuesto al **Paradigma Funcional**, pero la mayor parte de lenguajes integran ideas de ambos paradigmas.
## Conceptos Fundamentales
* Operación básica: **Asignación**
	* La asignación tiene efectos secundarios: Cambia el estado de la máquina.
* Sentencias de **Control de Flujo**
	* Condicionales y sin condición (GO TO), ramas, ciclos.
* Bloques, para obtener **Referencias Locales**.
* **Parametrización**.
## Elementos Básicos
* Definiciones de **Tipos**.
* Declaraciones de **Variables**.
* Expresiones y sentencias de **Asignación**.
* Sentencias de **Control de Flujo**.
* **Alcance Léxico** y bloques, para poder tener variables con referencias locales.
* Declaraciones y definiciones de **Procedimientos** y **Funciones** (bloques parametrizados).
### Declaraciones de Variables
Las declaraciones **Tipadas** restringen los posibles valores de una variable en la ejecución del programa.
* Jerarquía de tipos built-in o personalizada.
* Inicialización.
#### Ubicación y Valores de Variables
Al declarar una variable la estamos ligando a una **Ubicación en Memoria** (global, en la pila o en el heap).
* **l-valor**: Ubicación en memoria (dirección de memoria).
* **r-valor**: Valor que se guarda en la ubicación de memoria identificada por el l-valor.
* **Identificador**: Nombre de la variable en el texto del programa.
### Expresiones y Sentencias de Asignación
#### Variable y Asignación
En la parte derecha de una asignación está el r-valor de la variable, en la parte izquierda está su l-valor.
* `x := 1` significa "guardemos `1` como r-valor de la ubicación señalada por el l-valor ligado a `x`".
Una expresión que no tenga un l-valor no puede aparecer en la parte izquierda de una asignación.
El r-valor de un puntero es el l-valor de otra variable (el valor de un puntero es una dirección).
Las constantes sólo tienen r-valor.
El nombre de una función sólo tiene l-valor.
#### l-valor y r-valor en C: Punteros
* `&x` devuelve el l-valor de `x`.
* `*p` devuelve el r-valor de `p`.
	* Si `p` es un puntero, esto es el l-valor de otra variable.
### Sentencias de Control de Flujo
#### Flujo de Control Estructurado
Se piensa como secuencial
* Las instrucciones se ejecutan en el orden en el que están escritas.
* En algunos casos soporta ejecución concurrente.
Un programa es **Estructurado** si el flujo de control es evidente en la estructura sintáctica del texto del programa.
* Útil para poder razonar intuitivamente leyendo el texto del programa.
* Se crean construcciones del lenguaje para patrones comunes de control: Iteración, selección, procedimiento / funciones.
### Alcance Léxico y bloques, para poder tener variables con referencias
#### Estilo Moderno
Construcciones estándar que estructuran los saltos
```
if ... then ... else ... end
while ... do ... end
for ... { ... }
case ...
```
* Agrupan el código en bloques lógicos
* Se evitan saltos explícitos (excepto retorno de función).
* No se puede saltar al medio de un bloque o función.
#### Lenguajes con Estructura de Bloques
* Bloques anidados con variables locales
```
{ int x = 2;             <-- Se declaran nuevas variables en los bloques anidados
	{ int y = 3;         <-- Igual que x
		x = y + 2;
	}
}
```
Manejo de memoria
* Al entrar al bloque reservamos espacio de variables.
* Al salir del bloque se puede liberar parte o todo el espacio.
#### Manejo de Memoria
* El **Stack** tiene los datos sobre entrada y salida de bloques.
* El **Heap** tiene datos de diferentes lifetime.
* El **Puntero de Entorno** (environment) apunta a la posición actual en el stack.
* Al entrar a un bloque:
	* Se añade un nuevo activation record al stack.
* Al salir de un bloque:
	* Se elimina el activation record más reciente del stack.
#### Alcance y Lifetime
* **Alcance**: Región del texto del programa donde una declaración es visible.
* **Lifetime**: Período de tiempo en que una ubicación de memoria es asignada a un programa.
#### Activation Record
Para cada **Bloque** se usa un activation record.
* Estructura de Datos que se guarda en la Pila de Ejecución.
* Tiene lugar para Variables Locales.
### Declaraciones y Definiciones de Procedimientos y Funciones (bloques parametrizados)
#### Abstracción Procedural
Un **Procedimiento** es un alcance parametrizado con nombre
* El programador se puede abstraer de los detalles de implementación, enfocándose en la interfaz.
#### Pila de Ejecución
Los activation record se guardan en el Stack
* Cada nueva llamada apila un activation record.
* Cada llamada finalizada desapila el activation record de la punta.
* El stack tiene todos los record de todas las llamadas activas en un momento de la ejecución, siendo el record de la punta la llamada más reciente.
