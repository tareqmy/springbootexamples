source docker/.env
echo "Stopping existing containers..."
docker stop ${APIUI} $MAILHOG $DATABASE
