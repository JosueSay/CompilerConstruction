# Guía de uso con Docker, Docker Compose y Terraform 🚀

## 📦 **Prerrequisitos**

Antes de comenzar, asegúrate de tener instalado en tu sistema:

* [Docker](https://docs.docker.com/get-docker/)
* [Docker Compose](https://docs.docker.com/compose/install/)
* [Terraform](https://developer.hashicorp.com/terraform/downloads)

## ⚙️ **Configuración inicial**

### 1️⃣ Crear archivo `.env`

En la carpeta `/bash`, crea un archivo llamado `.env` con el siguiente contenido:

```env
API_KEY=token
```

> 🔑 **Reemplaza `token` con tu API Key real.**

## 🐳 **Uso con Docker Compose**

### 2️⃣ Construir la imagen

Desde la carpeta `/bash`, ejecuta:

```bash
docker-compose build
```

### 3️⃣ Crear un droplet en DigitalOcean

```bash
docker-compose run digitalocean /usr/local/bin/create_droplet.sh
```

### 4️⃣ Destruir el droplet

```bash
docker-compose run digitalocean /usr/local/bin/destroy_droplet.sh
```

> 📄 Estos scripts crearán un archivo `.txt` con un **ID** dentro.

## 🌍 **Uso con Terraform**

### 5️⃣ Crear archivo `terraform.tfvars`

En la carpeta `terraform`, crea un archivo `terraform.tfvars` basado en el ejemplo:

```bash
cd terraform
cp terraform.tfvars.example terraform.tfvars
```

Luego **reemplaza la cadena `DO_API_TOKEN` con el mismo API Key del archivo `.env`.**

### 6️⃣ Ejecutar comandos de Terraform

```bash
terraform init
terraform plan
terraform apply
terraform destroy
```

## 🐳 **Alternativa: usar Terraform desde Docker**

En caso de que Terraform no funcione de forma local, puedes usar la imagen oficial de HashiCorp:

```bash
docker run --rm -it -v ${PWD}:/workspace -w /workspace hashicorp/terraform:latest init
docker run --rm -it -v ${PWD}:/workspace -w /workspace hashicorp/terraform:latest plan
docker run --rm -it -v ${PWD}:/workspace -w /workspace hashicorp/terraform:latest apply
docker run --rm -it -v ${PWD}:/workspace -w /workspace hashicorp/terraform:latest destroy
```
