source .env

sudo docker rm -f ${APP} ${MAILHOG} ${DATABASE}
