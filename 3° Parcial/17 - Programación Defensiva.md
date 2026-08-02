## ¿Qué es la Programación Defensiva?
La programación defensiva es una filosofía de diseño de software cuyo principio rector es: "Defiende contra lo imposible, porque lo imposible pasará". Su objetivo principal es asegurar el correcto funcionamiento del software bajo circunstancias inesperadas, protegiéndolo de fallos causados por entradas del usuario, variaciones en el entorno de ejecución, cambios en las condiciones del sistema o ataques de inyección de código.
### ¿Como tratarlo?
1. Detectar potenciales problemas
2. Y después:
	* Graceful degradation (fault tolerance)
	* offensive programming
## Clasificación de Problemas a Detectar
Para defender el código de manera efectiva, se clasifican los errores en dos categorías principales:
- Errores esperables (originados por el entorno):
	- Situaciones externas e inevitables que el entorno puede originar, tales como entradas de usuario inválidas, agotamiento de recursos del sistema (memoria, almacenamiento) o fallos de hardware.
- Errores prevenibles (producto de la lógica interna):
	- Fallos que provienen del mal funcionamiento del propio software, como argumentos de función incorrectos, valores fuera de rango o excepciones y valores de retorno no documentados.
## Técnicas
* Canonicalización:
	* Los atacantes suelen inventar representaciones alternativas de datos maliciosos para evadir filtros 
	* La canonicalización consiste en emplear librerías que reduzcan el input a un formato estándar único antes de validarlo.
* Diseño por Contrato:
	* Utilizar precondiciones, postcondiciones e invariantes para asegurar la integridad de los datos y del estado del programa en todo momento. 
	* Permite que el código documente formalmente sus suposiciones.
* Aserciones y Guardas:
	* Implementar guardas explícitas en el flujo (`if (inesperado) { lanzar excepción }`).
* Uso de Excepciones:
	* Se prefiere el manejo estructurado de excepciones sobre el uso de códigos de retorno tradicionales, facilitando la transferencia de control, la desasignación de memoria en la pila y el paso de datos de diagnóstico durante la recuperación del error.
## Programación Defensiva vs. Programación Ofensiva
Mientras que la defensiva intenta "salvar" el programa y tolerar fallas inesperadas (_graceful degradation_), la programación ofensiva tiene como objetivo descubrir todos los malfuncionamientos posibles y hacer que el programa falle inmediatamente ante cualquier anomalía para que no pase desapercibida durante el desarrollo.

Bajo la estrategia ofensiva, se eliminan los chequeos innecesarios, los bloques de contingencia (_fallback_) y los atajos, operando bajo la premisa de confiar plenamente en la validez del software y de los datos.

