# Lab 1 — Introducción a ANTLR 🧪

Este laboratorio tiene como objetivo familiarizarse con **ANTLR** y ejecutar un analizador léxico-sintáctico básico en Python, usando una gramática proporcionada.

## ⚙️ Preparación del Entorno

Desde el directorio `lab-1/program/` puedes automatizar la configuración y ejecución con scripts.

### 1. Ejecutar el script de preparación

```bash
cd lab-1/program/
./setup.sh
```

Este script:

* Crea y activa un entorno virtual (`venv/`)
* Instala las dependencias necesarias
* Deja todo listo para ejecutar pruebas

## 🚀 Ejecución del Analizador

Una vez configurado el entorno, puedes correr el analizador con:

```bash
./run.sh
```

Esto:

* Activa el entorno virtual
* Genera el lexer y parser desde `MiniLang.g4`
* Ejecuta `Driver.py` con el archivo de entrada por defecto (`program_test.txt`)

También puedes probar con otros archivos:

```bash
./run.sh archivo_personal.txt
```

## 📁 Archivos generados por ANTLR

Al ejecutar `antlr4 -Dlanguage=Python3 MiniLang.g4`, se generan:

| Archivo                | Descripción                                                                  |
| ---------------------- | ---------------------------------------------------------------------------- |
| `MiniLangLexer.py`     | Código del **analizador léxico** generado, encargado de reconocer tokens.    |
| `MiniLangParser.py`    | Código del **analizador sintáctico**, que aplica las reglas de la gramática. |
| `MiniLangListener.py`  | Clase base con métodos vacíos que se ejecutan al **entrar/salir de reglas**. |
| `MiniLang.tokens`      | Mapeo numérico de todos los **tokens definidos en la gramática**.            |
| `MiniLangLexer.tokens` | Similar al anterior, pero generado desde la sección de **tokens del lexer**. |
| `MiniLang.interp`      | Archivo auxiliar usado por `grun` para **pruebas interactivas** (opcional).  |
| `MiniLangLexer.interp` | Archivo auxiliar para `grun`, correspondiente al lexer (también opcional).   |

## ✅ Validación

* ✅ Si la entrada es **válida**, no se mostrará ningún mensaje.
* ❌ Si hay errores, ANTLR los mostrará en la consola.
* Puedes modificar `program_test.txt` o pasar otros archivos al `run.sh`.
