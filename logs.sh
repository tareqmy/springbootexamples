source docker/.env

if [ $# -eq 1 ]
then
    if [ "$1" == "db" ]; then
        echo "logs for $DATABASE"
        docker logs -f $DATABASE
    fi
fi
echo "logs for $DATABASE"
docker logs -f $DATABASE
