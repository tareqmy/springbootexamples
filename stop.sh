source docker/.env
echo "Stopping existing containers..."
docker stop $MAILHOG $DATABASE
