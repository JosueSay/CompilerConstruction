#!/bin/bash

echo "🚀 Ejecutando analizador..."

# Activar entorno virtual
source venv/bin/activate

# Generar lexer/parser desde SimpleLang.g4
java -jar $HOME/antlr/antlr-4.13.1-complete.jar -Dlanguage=Python3 -visitor -listener SimpleLang.g4

# Usar archivo de entrada (por defecto: program_test_pass.txt)
INPUT_FILE=${1:-program_test_pass.txt}

# Verificar que el archivo exista
if [ ! -f "$INPUT_FILE" ]; then
  echo "❌ Archivo '$INPUT_FILE' no encontrado."
  deactivate
  exit 1
fi

# Ejecutar análisis (Driver con Visitor)
python3 Driver.py "$INPUT_FILE"

# Ejecutar análisis (Driver con Listener)
python3 DriverListener.py "$INPUT_FILE"

deactivate
echo "✅ Análisis completado para '$INPUT_FILE'"
