# Análisis de ANTLR

## Gramática MiniLang

La gramática utilizada se denomina `MiniLang` y está escrita en el archivo `MiniLang.g4`. Este archivo define tanto la parte léxica (tokens) como la sintáctica (reglas gramaticales) del lenguaje.

### Estructura general de un archivo `.g4`

Un archivo `.g4` tiene dos secciones principales:

1. **Lexer Rules**: definen los tokens básicos mediante expresiones regulares.
2. **Parser Rules**: definen la estructura del lenguaje mediante producciones gramaticales.

### Reglas léxicas

El lexer define los tokens a partir de expresiones regulares. Algunos de los tokens más relevantes son:

* Operadores: `MUL`, `DIV`, `ADD`, `SUB`
* Identificadores (`ID`): letras del alfabeto
* Números enteros (`INT`): dígitos del 0 al 9
* `NEWLINE` y `WS` (espacios en blanco). Los espacios son ignorados gracias a la directiva `skip`.

### Reglas principales

La regla inicial de la gramática es `prog`, la cual indica que un programa válido debe estar compuesto por una o más sentencias (`stat+`).

La regla `stat` define tres posibles sentencias:

* Una expresión seguida de un salto de línea (por ejemplo, `a + 2`).
* Una asignación de una variable `ID` con una expresión (por ejemplo, `x = 3 + 1`) seguida de un salto de línea.
* Una línea en blanco (solo `NEWLINE`).

Por su parte, la regla `expr` define cómo se pueden construir expresiones:

* Multiplicación o división entre expresiones.
* Suma o resta entre expresiones.
* Una variable (`ID`) o un número entero (`INT`).
* Una expresión encerrada entre paréntesis.

Estas reglas están etiquetadas usando `#Nombre`, lo que permite que ANTLR genere nodos específicos para cada tipo de producción, facilitando la implementación de fases posteriores como la semántica. Por ejemplo, `expr: expr op=('*'|'/') expr #MulDiv`.

## Archivos generados por ANTLR

Al ejecutar:

```bash
java -jar $HOME/antlr/antlr-4.13.1-complete.jar -Dlanguage=Python3 MiniLang.g4
```

ANTLR genera los siguientes archivos:

* `MiniLangLexer.py`: contiene las reglas léxicas en forma de clase `Lexer`.
* `MiniLangParser.py`: contiene las reglas sintácticas como clase `Parser`, con métodos para cada producción.
* `MiniLangListener.py`: clase base que implementa los métodos `enter` y `exit` para cada nodo del árbol sintáctico.
* `MiniLang.tokens`: contiene los nombres e identificadores de todos los tokens definidos.
* Archivos `.interp`: contienen la representación serializada del ATN (Augmented Transition Network) para el lexer y el parser, utilizados internamente por ANTLR para el reconocimiento.

El `MiniLangListener` no implementa directamente la fase semántica, pero sirve como base para extenderla. Al sobreescribir sus métodos (`exitAssign`, `exitExpr`, etc.), es posible realizar acciones semánticas como validación de tipos o construcción de una tabla de símbolos.

## Archivo `Driver.py`

Este archivo contiene el flujo principal de ejecución del analizador. Su función es tomar un archivo fuente escrito en MiniLang, procesarlo con ANTLR y validar si cumple con la gramática definida. El flujo es el siguiente:

1. Recibe un archivo de entrada como argumento.
2. Abre y lee el contenido del archivo.
3. Crea una instancia del `MiniLangLexer`, que convierte el texto en una secuencia de tokens.
4. Crea un `CommonTokenStream` con esos tokens.
5. Instancia el `MiniLangParser` con la secuencia de tokens.
6. Llama a la regla inicial `prog`, que comienza el análisis sintáctico.
7. ANTLR recorre el árbol sintáctico generado. Si se desea, se puede extender con listeners o visitors para implementar acciones semánticas o de interpretación.

## Flujo resumido

1. Se genera el código fuente Python a partir de la gramática (`MiniLang.g4`).
2. Se ejecuta `Driver.py` con un archivo fuente como entrada.
3. El lexer convierte el texto en tokens.
4. El parser recibe los tokens y genera el árbol sintáctico.
5. Se llama a la regla inicial `prog` para iniciar el análisis.
6. ANTLR reporta si el análisis es exitoso o si hay errores.

## Observaciones finales

* Las etiquetas `#Nombre` en las producciones ayudan a generar nodos específicos en el árbol sintáctico, útiles para implementar lógica diferenciada en listeners o visitors.
* `MiniLangListener` es útil para implementar lógica semántica, pero debe ser extendido para incluir validaciones reales.
* La estructura modular generada por ANTLR facilita la separación entre análisis léxico, sintáctico y semántico.

## Referencia

[ANTLR 3 - Introducción general](https://objectcomputing.com/resources/publications/sett/june-2008-antlr-3#:~:text=I%20%2D%20Descripci%C3%B3n%20general-,Introducci%C3%B3n%20a%20ANTLR,lugar%20de%20rodearlo%20con%20%5B%20%5D.)
