# Lab 2 — Sistema de Tipos con ANTLR

Este laboratorio se enfoca en extender un lenguaje simple usando **ANTLR** y aplicar **verificación de tipos** mediante las técnicas de **Visitor** y **Listener**. Se busca detectar errores semánticos, como incompatibilidades de tipos en operaciones, y experimentar con extensiones al sistema de tipos.

## ⚙️ Preparación del Entorno

Desde el directorio `lab-2/program/` puedes configurar el entorno de manera automática con:

```bash
cd lab-2/program/
./setup.sh
```

Este script:

* Crea un entorno virtual (`venv/`)
* Instala las dependencias requeridas (como `antlr4-python3-runtime`)
* Deja todo listo para ejecutar pruebas

## 🚀 Ejecución del Analizador

Una vez preparado el entorno, puedes ejecutar el analizador con:

```bash
./run.sh
```

Por defecto, este script:

* Activa el entorno virtual
* Genera el lexer y parser desde `SimpleLang.g4`
* Ejecuta `Driver.py` con el archivo `program_test_pass.txt`

También puedes especificar un archivo de entrada diferente:

```bash
./run.sh program_test_no_pass.txt
```

## 📁 Archivos generados por ANTLR

Al ejecutar ANTLR sobre `SimpleLang.g4`, se generan los siguientes archivos:

| Archivo                  | Descripción                                                          |
| ------------------------ | -------------------------------------------------------------------- |
| `SimpleLangLexer.py`     | Código del **lexer** generado automáticamente                        |
| `SimpleLangParser.py`    | Código del **parser** generado a partir de las reglas gramaticales   |
| `SimpleLangVisitor.py`   | Clase base con métodos `visitX` para usar el patrón Visitor          |
| `SimpleLangListener.py`  | Clase base con métodos `enterX`/`exitX` para usar el patrón Listener |
| `SimpleLang.tokens`      | Tabla de tokens léxicos generados                                    |
| `SimpleLangLexer.tokens` | Tokens del lexer, usados internamente                                |
| `SimpleLang.interp`      | Archivo auxiliar para `grun` (opcional)                              |
| `SimpleLangLexer.interp` | Archivo auxiliar para el lexer (opcional)                            |

## 🧠 Validación Semántica

El sistema implementa reglas de tipo para detectar errores como:

* Operar `int` con `string` → ❌
* Negar un `float` → ❌
* Módulo entre `int` y `float` → ❌

La salida te indicará si el tipo es correcto:

```bash
Type checking passed
```

O te mostrará errores detallados por línea:

```bash
Type checking error: Unsupported operand types for + or -: float and bool
```

## ✅ Archivos de prueba

Se incluyen:

* `program_test_pass.txt` → contiene expresiones con tipos válidos
* `program_test_no_pass.txt` → contiene errores intencionales para probar el verificador

Puedes editar o crear tus propios archivos para probar nuevas combinaciones.
