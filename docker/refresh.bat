REM COMPOSE_PROJECT_NAME=dev-leetcode
docker-compose -p dev-leetcode -f docker-compose.dev.yml down
docker-compose -p dev-leetcode -f docker-compose.dev.yml up -d

docker-compose -p dev-leetcode -f kafka.cluster.yml down
docker-compose -p dev-leetcode -f kafka.cluster.yml up -d



REM docker container exec -it dev-leetcode_mysql-db_1 bash
REM docker container exec -it dev-leetcode_redis-db_1 bash
REM docker container exec -it dev-leetcode_canal-server_1 bash
REM docker container exec -it dev-leetcode_rabbitmq_1 bash
REM docker container exec -it dev-leetcode_etcd-1_1 bash
REM docker container exec -it dev-leetcode_zookeeper-1_1 bash
REM docker container exec -it dev-leetcode_kafka-1_1 bash
REM docker container exec -it dev-leetcode_kafka-manager_1 bash

REM docker container logs dev-leetcode_mysql-db_1
REM docker container logs dev-leetcode_canal-server_1
REM docker container logs dev-leetcode_rabbitmq_1
REM docker container logs dev-leetcode_etcd-1_1
REM docker container logs dev-leetcode_zookeeper-1_1
REM docker container logs dev-leetcode_kafka-1_1
REM docker container logs dev-leetcode_kafka-manager_1


docker run -dit --name ubuntu ubuntu
docker container exec -it ubuntu /bin/bash

docker container exec -it dev-leetcode_ubuntu_1 bash
mv /etc/apt/sources.list /etc/apt/sources.list.bak
cat > /etc/apt/sources.list <<EOF
deb http://mirrors.aliyun.com/ubuntu/ bionic main restricted universe multiverse
deb http://mirrors.aliyun.com/ubuntu/ bionic-security main restricted universe multiverse
deb http://mirrors.aliyun.com/ubuntu/ bionic-updates main restricted universe multiverse
deb http://mirrors.aliyun.com/ubuntu/ bionic-proposed main restricted universe multiverse
deb http://mirrors.aliyun.com/ubuntu/ bionic-backports main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ bionic main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ bionic-security main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ bionic-updates main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ bionic-proposed main restricted universe multiverse
deb-src http://mirrors.aliyun.com/ubuntu/ bionic-backports main restricted universe multiverse
EOF
apt-get update
apt-get -y install vim
apt-get -y install netcat-traditional	# nc
apt-get -y install net-tools
apt-get -y install iputils-ping
