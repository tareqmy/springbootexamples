source docker/.env
echo "Starting existing containers..."
docker start ${APIUI} $MAILHOG $DATABASE
