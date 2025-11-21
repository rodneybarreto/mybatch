#docker network create --driver bridge mybatch-net

docker run --name mybatchdb-serv \
    --network=mybatch-net \
    -e MARIADB_DATABASE=mybatchdb \
    -e MARIADB_USER=mybatchuser \
    -e MARIADB_PASSWORD=mybatchpass \
    -e MARIADB_ROOT_PASSWORD=admin@123 \
    -p 3306:3306 \
    -v /home/rodney/databases/mariadb/mybatch-api:/var/lib/mysql \
    -d mariadb:10.11