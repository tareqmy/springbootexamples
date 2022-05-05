source .env

sudo sh delete.sh

echo "Docker compose UP..."

docker-compose --file docker-compose-production.yml up -d

sudo sh logs.sh
