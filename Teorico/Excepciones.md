Terminan una parte de la computación, nos permite saltar fuera de una construcción, pasar datos como parte del salto, retorna al lugar mas reciente donde tratar la excepción, en el proceso de retorno se pueden desalojar los activation records innecesarios.
Esta compuesta de **Dos Construcciones** lingüísticas
* Un **Manejador** de excepciones (*exeption handler*), que captura (*catch*) la excepción y la trata.
* Y la **Sentencia** o **Expresión** que **Levanta** (*raise*) o tira (*throw*) la excepción.

#### Diferencias con go to
Solo se puede salir de una función, no saltar a cualquier parte del programa.
Se pueden pasar Datos como parte del salto para recuperarse de la excepción o como información para el usuario, pero estos datos tienen alcance dinámico. Se obtienen del entorno en el que se ejecuta la función, no del entorno en le que se definió.

