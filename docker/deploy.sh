 source .env

 ./delete.sh

 echo "Docker compose UP..."

 docker-compose up -d

 ./logs.sh
