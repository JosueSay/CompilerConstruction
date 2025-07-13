#!/bin/bash

echo "🚀 Ejecutando analizador..."

# Activar entorno virtual
source venv/bin/activate

# Generar lexer/parser
antlr4 -Dlanguage=Python3 MiniLang.g4

# Usar argumento como archivo de prueba, o por defecto 'program_test.txt'
INPUT_FILE=${1:-program_test.txt}

# Verificar que el archivo exista
if [ ! -f "$INPUT_FILE" ]; then
  echo "❌ Archivo '$INPUT_FILE' no encontrado."
  exit 1
fi

# Ejecutar el parser con el archivo
python3 Driver.py "$INPUT_FILE"
