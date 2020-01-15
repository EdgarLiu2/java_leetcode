
docker-compose -p dev-leetcode -f docker-compose.dev.yml down
docker-compose -p dev-leetcode -f docker-compose.dev.yml up -d


REM docker container exec -it dev-leetcode_mysql-db_1 bash
REM docker container exec -it dev-leetcode_redis-db_1 bash
REM docker container exec -it dev-leetcode_canal-server_1 bash
REM docker container exec -it dev-leetcode_rabbitmq_1 bash
REM docker container exec -it dev-leetcode_etcd-1_1 bash

REM docker container logs dev-leetcode_mysql-db_1
REM docker container logs dev-leetcode_canal-server_1
REM docker container logs dev-leetcode_rabbitmq_1
REM docker container logs dev-leetcode_etcd-1_1
