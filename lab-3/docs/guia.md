tener docker, docker compose y terraform instalado

crear un archivo .env en /bash donde tenga:

API_KEY=token

ir a la carpeta /bash y ejecutar los comandos:

docker-compose build

docker-compose run digitalocean /usr/local/bin/create_droplet.sh

docker-compose run digitalocean /usr/local/bin/destroy_droplet.sh

esto creará un archivo txt con un id dentro

para la parte de terraform crear el archivo terraform.tfvars dentro se coloca el contenido puesto en terraform.tfvars.example y se reemplaza la cadena "DO_API_TOKEN" con el mismo API KEY del archivo .env

luego ejecutar los comandos:
terraform init

terraform plan

terraform apply
