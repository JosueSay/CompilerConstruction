#!/bin/bash

echo "🚀 Ejecutando analizador de Terraform..."

# Crear entorno virtual si no existe
if [ ! -d "venv" ]; then
  echo "🔧 Creando entorno virtual..."
  python3 -m venv venv
fi

# Activar entorno virtual
source venv/bin/activate

# Instalar dependencias si no están
echo "📦 Instalando dependencias..."
pip install -r requirements.txt || pip install antlr4-python3-runtime

# Generar lexer/parser en la carpeta 'program'
echo "⚙️ Generando lexer y parser con ANTLR..."
cd program
java -jar ../antlr-4.13.1-complete.jar -Dlanguage=Python3 TerraformSubset.g4
cd ..

# Ejecutar parser
echo "🧠 Ejecutando parser..."
python3 program/terraform_parser.py program/main.tf

# Salir del entorno virtual
deactivate

echo "✅ Análisis completado."
