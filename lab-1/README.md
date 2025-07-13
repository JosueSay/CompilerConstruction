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

| Archivo                | Función                                                                   |
| ---------------------- | ------------------------------------------------------------------------- |
| `MiniLangLexer.py`     | Analizador léxico (tokens)                                                |
| `MiniLangParser.py`    | Analizador sintáctico (reglas de la gramática)                            |
| `MiniLangListener.py`  | Clase base vacía para escuchar eventos del parser                         |
| `MiniLang.tokens`      | Tabla de tokens con sus valores numéricos                                 |
| `MiniLangLexer.tokens` | Similar, pero solo para el lexer                                          |
| `.interp`              | Archivos usados por `grun` (puedes ignorarlos si no usas esa herramienta) |

## ✅ Validación

* ✅ Si la entrada es **válida**, no se mostrará ningún mensaje.
* ❌ Si hay errores, ANTLR los mostrará en la consola.
* Puedes modificar `program_test.txt` o pasar otros archivos al `run.sh`.
