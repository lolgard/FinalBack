.PHONY: help build up down restart logs logs-backend logs-db clean rebuild status

# Variables
COMPOSE_FILE=docker compose.yml
SERVICE_BACKEND=backend
SERVICE_DB=db

help: ## Muestra esta ayuda
	@echo "Comandos disponibles:"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'

build: ## Construye las imágenes Docker
	@echo "🔨 Construyendo imágenes..."
	./gradlew clean build -x test 
	docker compose build --no-cache
up: ## Levanta los contenedores
	@echo "🚀 Levantando contenedores..."
	docker compose up -d
	@echo "✅ Contenedores levantados"

down: ## Detiene y elimina los contenedores
	@echo "🛑 Deteniendo contenedores..."
	docker compose down
	@echo "✅ Contenedores detenidos"

restart: ## Reinicia los contenedores
	@echo "🔄 Reiniciando contenedores..."
	docker compose restart
	@echo "✅ Contenedores reiniciados"

logs: ## Muestra los logs de todos los servicios
	docker compose logs -f

logs-backend: ## Muestra los logs del backend
	docker compose logs -f $(SERVICE_BACKEND)

logs-db: ## Muestra los logs de la base de datos
	docker compose logs -f $(SERVICE_DB)

status: ## Muestra el estado de los contenedores
	@echo "📊 Estado de los contenedores:"
	docker compose ps --format "table {{.Name}}\t{{.Status}}\t{{.Ports}}"
clean: ## Detiene contenedores y elimina volúmenes
	@echo "🧹 Limpiando contenedores y volúmenes..."
	docker compose down -v
	@echo "✅ Limpieza completada"

rebuild: clean build up ## Reconstruye todo desde cero

stop: ## Detiene los contenedores sin eliminarlos
	@echo "⏸️  Deteniendo contenedores..."
	docker compose stop
	@echo "✅ Contenedores detenidos"

start: ## Inicia los contenedores detenidos
	@echo "▶️  Iniciando contenedores..."
	docker compose up -d
	@echo "✅ Contenedores iniciados"

shell-backend: ## Abre una shell en el contenedor del backend
	docker compose logs backend -f 

shell-db: ## Abre una shell en el contenedor de MySQL
	docker compose logs db -f

bash-backend:
	@echo "Iniciando bash del backend"
	docker compose exec backend bash
bash-db:
	@echo "Bash DB"
	docker compose exec db bash