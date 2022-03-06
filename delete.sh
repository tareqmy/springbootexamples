source docker/.env
echo "Delete existing containers..."
docker rm -f $MAILHOG
