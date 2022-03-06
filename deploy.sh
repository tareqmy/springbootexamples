source docker/.env

./delete.sh

echo "Docker compose UP... "
cd docker
docker-compose up -d
cd ../

