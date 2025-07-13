#!/bin/bash

echo "🔧 Creando entorno virtual..."
python3 -m venv venv
source venv/bin/activate

echo "📦 Instalando dependencias..."
pip install -r ../requirements.txt || pip install antlr4-python3-runtime

echo "✅ Entorno listo. Puedes ejecutar run.sh para probar la gramática."