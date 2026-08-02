## Distinción Clave: Security vs. Safety
- Security (Seguridad de Recursos):
	- Se refiere a prevenir comportamientos no esperados que afecten a recursos protegidos de la infraestructura o el sistema.
- Safety (Inuidad/Protección Social):
	- Se enfoca en evitar comportamientos dañinos para los usuarios, la sociedad o el medio ambiente, tales como la generación de contenido tóxico, la desinformación, la discriminación, la exposición de datos personales protegidos, información peligrosa o el consumo energético excesivo.
## Prácticas Inseguras del Programador (Overreliance y Vibe Coding)
El principal problema de seguridad no radica únicamente en la IA, sino en las prácticas de programación inseguras por exceso de confianza (overreliance) de los desarrolladores. Esto se manifiesta en:
- Falta de revisión:
	- No se lee el código generado, no se comprueba de forma rigurosa y se omite el proceso de control de calidad.
- Falsos positivos en pruebas:
	- Los problemas de seguridad suelen pasar los tests funcionales básicos (el programa hace "lo que tiene que hacer", pero abre una vulnerabilidad silenciosa).
- El fenómeno del "Vibe Coding":
	- Los profesionales suelen relajar los controles de seguridad y "programar por sensaciones" cuando usan asistentes para proyectos de ocio o desarrollo rápido que no manejan recursos sensibles.
## Deficiencias Técnicas en el Código Generado por IA
Los modelos de lenguaje sufren de alucinaciones, vaguedades y generalizaciones que introducen fallos sutiles en el software. Estos problemas incluyen:
- Mal manejo de particularidades: 
	- La IA tiene un desempeño deficiente con casos borde (_edge cases_), variaciones sutiles del problema original, aplicaciones minoritarias o lenguajes de programación poco comunes.
- Conflictos de tipos y alcance: 
	- Los asistentes suelen respetar los nombres de las variables del usuario, pero no respetan sus tipos (lo cual es sumamente grave en lenguajes de tipado implícito) o cometen fallos en el alcance (scope) de las variables.
- Errores silenciosos:
	- El código parece correcto y compila, pero ante una situación imprevista se comporta de manera distinta a la especificación sin lanzar excepciones, dificultando enormemente su detección.
## Vulnerabilidades y Vectores de Ataque Específicos
El uso de asistentes de IA expone al software a amenazas específicas:
- Inyección de código:
	- Explotable a partir de nombres de librerías sugeridas por la IA.
- Fuga de información (Exfiltration):
	- Obtención no autorizada de datos que deberían estar protegidos bajo el entorno de ejecución.
- Envenenamiento de datos (Data Poisoning):
	- Introducción de datos maliciosos en los conjuntos de entrenamiento del modelo para que este genere código vulnerable a propósito.