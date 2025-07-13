#!/bin/bash

echo "🚀 Ejecutando analizador..."

# Activar entorno virtual
source venv/bin/activate

# Generar lexer/parser
java -jar $HOME/antlr/antlr-4.13.1-complete.jar -Dlanguage=Python3 MiniLang.g4

# Usar archivo de entrada (por defecto: program_test.txt)
INPUT_FILE=${1:-program_test.txt}

# Verificar que el archivo exista
if [ ! -f "$INPUT_FILE" ]; then
  echo "❌ Archivo '$INPUT_FILE' no encontrado."
  deactivate
  exit 1
fi

# Ejecutar análisis
python3 Driver.py "$INPUT_FILE"
deactivate

echo "✅ Análisis completado para '$INPUT_FILE'"

