# Análisis de ANTLR

## Enlaces de videos a YouTube

- [Ejecución de pruebas](https://youtu.be/AbZ06BqE4Mg)
- [Análisis ANTLR + ejecución de pruebas](https://youtu.be/v4-KbrPg6Bs)

## Gramática SimpleLang

La gramática utilizada en este laboratorio se denomina `SimpleLang` y está definida en el archivo `SimpleLang.g4`. Esta gramática especifica las reglas sintácticas y léxicas de un lenguaje simple que permite trabajar con operaciones aritméticas y distintos tipos de datos.

### Estructura general del archivo `.g4`

Un archivo `.g4` de ANTLR se divide en dos secciones:

1. **Lexer Rules**: definen los tokens del lenguaje mediante expresiones regulares.
2. **Parser Rules**: definen la estructura sintáctica del lenguaje usando reglas de producción.

### Reglas léxicas

Estas reglas indican cómo reconocer las unidades básicas del lenguaje, conocidas como tokens. Algunos de los tokens definidos en `SimpleLang` son:

- `INT`: números enteros, definidos como una o más cifras del 0 al 9.
- `FLOAT`: números de punto flotante, formados por una parte entera, un punto, y una parte decimal opcional.
- `STRING`: cadenas encerradas entre comillas dobles, con cualquier contenido intermedio.
- `BOOL`: valores booleanos, que pueden ser `true` o `false`.
- `NEWLINE`: saltos de línea (permite separar instrucciones).
- `WS`: espacios en blanco o tabulaciones. Son ignorados mediante la instrucción `-> skip`.

### Reglas de análisis sintáctico (parser rules)

La parte sintáctica define cómo se estructura un programa válido en este lenguaje.

- `prog`: regla inicial del programa. Un programa debe estar compuesto por una o más sentencias (`stat+`).

- `stat`: representa una sentencia del programa. En este caso, se define como una expresión seguida de un salto de línea.

- `expr`: esta regla define las posibles expresiones del lenguaje, con soporte para:

  - Multiplicación y división: `expr '*' expr` o `expr '/' expr`, etiquetado como `#MulDiv`.
  - Suma y resta: `expr '+' expr` o `expr '-' expr`, etiquetado como `#AddSub`.
  - Constantes enteras, flotantes, cadenas o booleanos: `INT`, `FLOAT`, `STRING`, `BOOL`.
  - Expresiones agrupadas con paréntesis: `('(' expr ')')`, etiquetado como `#Parens`.

Cada producción está etiquetada con `#Nombre`, lo cual permite que ANTLR genere nodos diferenciados en el árbol de sintaxis abstracta. Esto facilita el uso de visitantes (`Visitor`) o escuchas (`Listener`) para implementar validaciones semánticas, como verificación de tipos. Por ejemplo, la expresión `expr: expr op=('+'|'-') expr #AddSub` permite identificar fácilmente operaciones de suma y resta para validarlas posteriormente.

Claro, aquí tienes la **documentación de los archivos generados por ANTLR** en el **Laboratorio 2**, basada en el nuevo contenido:

---

## Archivos generados por ANTLR

Al ejecutar:

```bash
java -jar $HOME/antlr/antlr-4.13.1-complete.jar -Dlanguage=Python3 -visitor -listener SimpleLang.g4
```

ANTLR genera los siguientes archivos:

- **`SimpleLangLexer.py`**: contiene la definición del analizador léxico (`Lexer`), que identifica tokens como enteros, flotantes, strings, booleanos y operadores aritméticos. Define reglas para cada token según `SimpleLang.g4`.

- **`SimpleLangParser.py`**: incluye la implementación del analizador sintáctico (`Parser`) que traduce secuencias de tokens en árboles de análisis, de acuerdo con las reglas gramaticales de `SimpleLang.g4`. Genera clases contextuales para cada regla y métodos como `prog`, `stat` y `expr`.

- **`SimpleLangListener.py`**: clase base con métodos `enter` y `exit` para cada nodo del árbol de análisis. Se usa como base para implementar lógica personalizada durante el recorrido del árbol (en modo *listener*).

- **`SimpleLangVisitor.py`**: clase base con métodos `visit` para cada nodo del árbol, útil si se quiere usar el patrón *visitor*. Permite recorrer manualmente el árbol y definir acciones específicas por nodo.

- **`SimpleLang.tokens`**: lista los nombres e identificadores de todos los tokens léxicos definidos en el archivo `.g4`.

- **`SimpleLangLexer.interp` y `SimpleLang.interp`**: archivos binarios con representaciones serializadas de las redes de transición (ATN) utilizadas por ANTLR para el análisis léxico y sintáctico.

## Código: `Driver.py`

Este archivo representa el flujo principal de ejecución del analizador utilizando el enfoque **Visitor**. Su propósito es cargar un archivo fuente escrito en `SimpleLang`, procesarlo con ANTLR y aplicar validaciones semánticas, particularmente verificación de tipos.

El flujo del código es el siguiente:

1. **Importaciones**
   Se importan los módulos necesarios:

   - `sys` para acceder a argumentos desde la línea de comandos.
   - `antlr4` para manejar flujos de entrada y análisis léxico/sintáctico.
   - `SimpleLangLexer` y `SimpleLangParser`, generados por ANTLR a partir de la gramática.
   - `TypeCheckVisitor`, que implementa la lógica de verificación de tipos mediante el patrón Visitor.

2. **Lectura del archivo de entrada**
   Se usa `FileStream(argv[1])` para leer el archivo proporcionado como argumento en la línea de comandos.

3. **Análisis léxico**
   Se crea una instancia de `SimpleLangLexer`, que convierte el texto fuente en una secuencia de tokens según las reglas léxicas de la gramática.

4. **Construcción de flujo de tokens**
   `CommonTokenStream` encapsula los tokens generados por el lexer. Este flujo es requerido por el parser.

5. **Análisis sintáctico**
   Se instancia `SimpleLangParser` y se invoca la regla inicial `prog()` para comenzar el análisis sintáctico. Esto produce un árbol de sintaxis (parse tree).

6. **Verificación de tipos con Visitor**
   Se crea un objeto `TypeCheckVisitor` y se recorre el árbol de sintaxis usando `visitor.visit(tree)`. Este paso realiza la verificación de tipos definida en el visitor.

7. **Manejo de errores semánticos**
   Si durante el recorrido ocurre un error de tipo (por ejemplo, sumar un entero con una cadena), se lanza una excepción `TypeError` y se muestra un mensaje de error en consola.

8. **Resultado**
   Si no hay errores, se imprime “Type checking passed”; en caso contrario, se muestra el mensaje correspondiente al error.

## Código: `DriverListener.py`

Este archivo tiene la misma función general que `Driver.py`, pero implementado usando el enfoque **Listener**, donde se recorre el árbol de sintaxis de forma automática mediante un `ParseTreeWalker`.

El flujo del código es el siguiente:

1. **Importaciones**
   Se importan:

   - Módulos estándar (`sys`, `antlr4`)
   - Componentes generados por ANTLR: `SimpleLangLexer`, `SimpleLangParser`
   - El listener personalizado `TypeCheckListener`
   - `ParseTreeWalker`, que se encarga de recorrer el árbol

2. **Lectura del archivo de entrada**
   Se lee el archivo especificado desde la línea de comandos mediante `FileStream(argv[1])`.

3. **Análisis léxico**
   Se usa `SimpleLangLexer` para convertir el texto fuente en una secuencia de tokens.

4. **Análisis sintáctico**
   El parser generado por ANTLR (`SimpleLangParser`) utiliza el flujo de tokens para construir el árbol de sintaxis (`tree`), invocando la regla inicial `prog()`.

5. **Recorrido del árbol con Listener**
   Se crea una instancia de `ParseTreeWalker`, que recorre automáticamente el árbol. Durante este recorrido, se invocan los métodos correspondientes definidos en `TypeCheckListener`.

6. **Manejo de errores**
   El listener acumula errores en la propiedad `listener.errors`. Al finalizar el recorrido, si hay errores, se imprimen uno por uno; si no hay errores, se imprime “Type checking passed”.

## Código: `custom_types.py`

Este archivo define las clases que representan los **tipos de datos del lenguaje SimpleLang**, y se utiliza principalmente durante la fase de **verificación de tipos** en el análisis semántico.

### Descripción de las clases

1. **Clase base `Type`**
   Es una clase abstracta que actúa como punto común para todos los tipos del lenguaje. Sirve para que el sistema de tipos sea jerárquico y extensible. Aunque no contiene lógica por sí sola, permite que las demás clases puedan ser tratadas de forma uniforme.

2. **`IntType`**
   Representa el tipo entero. Se asocia a tokens léxicos reconocidos como enteros en el código fuente. Cuando una expresión está compuesta solo por enteros, esta clase se utiliza para representar su tipo.

3. **`FloatType`**
   Representa números decimales (de punto flotante). Se usa cuando el analizador detecta que una expresión contiene un número con parte fraccionaria. También se emplea para resolver operaciones mixtas mediante coerción, por ejemplo, al sumar un entero con un flotante.

4. **`StringType`**
   Representa cadenas de texto. Este tipo es útil para detectar errores cuando se intenta realizar operaciones aritméticas con datos no numéricos, como concatenar strings con enteros sin conversión explícita.

5. **`BoolType`**
   Representa valores booleanos (`true` o `false`). Se utiliza para validaciones lógicas y también para verificar errores cuando se usan valores booleanos en contextos no permitidos, como operaciones aritméticas.

Aquí tienes la documentación del archivo `type_check_listener.py` con el mismo nivel de detalle que los anteriores:

## Código: `type_check_listener.py`

Este archivo implementa la verificación de tipos usando el enfoque Visitor, en el cual el recorrido del árbol de sintaxis se realiza de forma manual, invocando explícitamente métodos `visit` para cada nodo. A diferencia del enfoque Listener, aquí se tiene control total sobre el orden de visita y el retorno de valores desde cada nodo del árbol.

El objetivo principal es determinar el tipo de cada expresión y lanzar errores si las combinaciones de tipos no son válidas (por ejemplo, sumar un número con una cadena). Cada método corresponde a una producción de la gramática y retorna el tipo deducido para la expresión correspondiente. Esto permite construir una verificación semántica precisa y extensible.

### Flujo general

1. Se hereda de la clase base `SimpleLangListener`, que contiene métodos vacíos para cada regla de la gramática.
2. A lo largo del recorrido del árbol, se implementan métodos `enterX` y `exitX` para reaccionar cuando ANTLR entra o sale de una producción específica del lenguaje (`MulDiv`, `AddSub`, `Int`, etc.).
3. En cada nodo relevante, se determina el tipo de la expresión y se registran errores si las operaciones son inválidas.

### Componentes principales

- **Atributo `errors`**: lista que acumula todos los errores de tipo encontrados durante el recorrido.
- **Atributo `types`**: diccionario que asocia cada subárbol de expresión con su tipo correspondiente. Permite acceder al tipo de una expresión previamente evaluada para combinarlo con otra.

### Reglas específicas implementadas

- **`exitMulDiv`** y **`exitAddSub`**: se ejecutan después de evaluar una operación de multiplicación/división o suma/resta. Se obtienen los tipos de las expresiones hijas (operandos) y se valida si la operación es válida entre esos tipos.

  - Si alguno de los operandos es `float`, el resultado también se considera `float` (coerción implícita).
  - Si hay tipos incompatibles (por ejemplo, `int` con `string`), se guarda un error indicando el conflicto de tipos.

- **`enterInt`**, **`enterFloat`**, **`enterString`**, **`enterBool`**: se ejecutan al reconocer un valor literal. Se asigna el tipo correspondiente a la expresión (entero, flotante, cadena o booleano).

- **`exitParens`**: propaga el tipo de la expresión contenida entre paréntesis hacia su nodo padre, ya que los paréntesis no afectan el tipo.

- **Método auxiliar `is_valid_arithmetic_operation`**: encapsula la lógica de validación de operaciones aritméticas. Solo permite operaciones entre enteros y/o flotantes.

Aquí tienes la documentación correspondiente para el archivo `type_check_visitor.py` siguiendo el mismo formato y nivel de detalle que los anteriores:

## Código: `type_check_visitor.py`

Este archivo implementa la lógica de **verificación de tipos utilizando el patrón Visitor**, donde cada método visita explícitamente los nodos del árbol sintáctico construidos por ANTLR, aplicando las reglas semánticas necesarias para validar los tipos de datos de las expresiones.

### Flujo general

1. La clase extiende `SimpleLangVisitor`, lo cual permite redefinir métodos `visitX` para cada tipo de nodo (`AddSub`, `MulDiv`, `Int`, etc.).
2. En cada visita se evalúan los tipos de los operandos involucrados en la expresión y se decide si la operación es válida y cuál es su tipo resultante.
3. Si ocurre una operación inválida entre tipos no compatibles, se lanza una excepción de tipo (`TypeError`), la cual será capturada en el `Driver.py`.

### Componentes principales

- Cada método `visitX` implementa la lógica de **verificación semántica** para una producción específica del lenguaje.

### Reglas específicas implementadas

- **`visitMulDiv`** y **`visitAddSub`**: se evalúan recursivamente los operandos izquierdo y derecho de la operación. Si ambos son tipos numéricos (`int` o `float`), se permite la operación y se devuelve el tipo resultante:

  - Si alguno es `float`, el resultado es `float` (coerción implícita).
  - Si ambos son `int`, el resultado es `int`.
  - Si alguno no es numérico (como `string` o `bool`), se lanza un error de tipo.

- **`visitInt`**, **`visitFloat`**, **`visitString`**, **`visitBool`**: cada uno reconoce una constante literal y retorna el tipo correspondiente (por ejemplo, al ver un número entero, retorna un objeto `IntType`).

- **`visitParens`**: simplemente propaga el tipo de la expresión que está entre paréntesis, ya que los paréntesis no modifican el tipo.

## Flujo resumido

1. Se genera el código Python automáticamente a partir del archivo `SimpleLang.g4` mediante ANTLR, incluyendo el lexer, parser, visitor y listener.
2. Se ejecuta `Driver.py` o `DriverListener.py` con un archivo fuente como entrada.
3. El lexer toma el texto del programa y lo convierte en una secuencia de tokens según las reglas léxicas.
4. El parser procesa estos tokens y construye un árbol sintáctico de acuerdo con las reglas gramaticales.
5. Se activa el recorrido del árbol llamando a la regla inicial `prog`, que representa la estructura global del programa.
6. Se utiliza un visitor o listener personalizado para recorrer el árbol y realizar el análisis semántico.
7. Durante este recorrido, se validan los tipos de las expresiones. Para cada operación se comparan los tipos de operandos según las reglas definidas.
8. Si todos los tipos son compatibles, se indica que el chequeo de tipos fue exitoso. Si hay conflictos, se reportan como errores de tipo con un mensaje detallado.

## Referencia

[ANTLR 3 - Introducción general](https://objectcomputing.com/resources/publications/sett/june-2008-antlr-3#:~:text=I%20%2D%20Descripci%C3%B3n%20general-,Introducci%C3%B3n%20a%20ANTLR,lugar%20de%20rodearlo%20con%20%5B%20%5D.)
