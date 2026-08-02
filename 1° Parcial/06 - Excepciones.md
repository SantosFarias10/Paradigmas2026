Las excepciones son un mecanismo de **Salida Estructurada** que permite terminar una parte de la computación, saltar fuera de una construcción y retornar al lugar más reciente diseñado para trata dicho evento.
A diferencia del GO TO, las excepciones no cruzan ramas del código de forma arbitraria (NO genera "código spaghetti") y permiten pasar datos informativos como parte del salto.
## Componentes de una Excepción
El mecanismo se compone de dos construcciones lingüísticas principales:
* Una sentencia para **Levantar** o **Lanzar** (*raise* / *throw*) la excepción.
* Un **Manejador** (*Handler* / *Catch*) que la captura para poder tratarla y recuperarse del error.
## Alcance Dinámico
Los Manejadores de Excepciones tienen Alcance Dinámico. Esto quiere decir que cuando se lanza una excepción, el programa busca el manejador adecuado subiendo por la cadena dinámica de llamados a función.
* En el proceso de retorno hacia el manejador, el lenguaje desapila y desaloja todos los activation record intermedios que ya no son necesarios.
* Esto es vital para la separación de responsabilidades: El creador de una función sabe cuándo falla su código y lanza el error, pero es el programador que llama a esa función quien sabe cómo manejar ese error.
## Casos de Uso
Aunque se asocian a problemas, las excepciones tiene dos usos claros:
* Condiciones de errores: Para abortar un cálculo que no puede continuar.
* Eficiencia y Optimización: Se utilizan para cortar cálculos largos cuando el resultado final ya es evidente.
## Tipado de las Excepciones
El sistema de tipos trata a las excepciones con reglas matemáticas muy especificas:
* Tipado de *raise*: Como levantar una excepción interrumpe el flujo y nunca retorna un valor de forma formal, la instrucción *raise* asume mágicamente el tipo de dato que necesite la expresión para que el código compile sin errores.
* Tipado de *handle*: Un bloque con un manejador (por ejemplo, `<exp1> handle X => <exp2>`) requiere un **Acuerdo de Tipos**. Esto obliga a que la expresión original `<exp1>` y el código de recuperación `<exp2>` devuelvan exactamente el mismo tipo de dato.
## Diferencia entre ML y C++
* En ML, las excepciones son entidades únicas y diferentes a los tipos de datos normales, requieren declaración explícita, y los manejadores funcionan utilizando *pattern-matching*.
* En C++, se puede usar *throw* para lanzar cualquier tipo de dato primitivo o clase, aunque los expertos recomiendan definir clases exclusivas para los errores.
## Problemas con los Recursos (*Resource Allocation*)
Dado que las excepciones interrumpen la ejecución y saltan código, pueden dejar recursos alojados (como espacio en memoria, locks de concurrencia o archivos abiertos). No siempre es claro para el lenguaje cómo limpiar estos recursos automáticamente después de una excepción, lo que puede requerir el uso de recolectores de basura (garbage collector).
