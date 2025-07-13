#!/bin/bash

echo "🔧 Creando entorno virtual..."
python3 -m venv venv

echo "📦 Instalando dependencias..."
source venv/bin/activate
pip install -r ../requirements.txt || pip install antlr4-python3-runtime
deactivate

echo "✅ Entorno listo. Puedes ejecutar ./run.sh para compilar y analizar pruebas."
