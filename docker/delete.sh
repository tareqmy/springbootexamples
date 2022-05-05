source .env

sudo docker rm -f ${APP} ${APIUI} ${MAILHOG} ${DATABASE}
