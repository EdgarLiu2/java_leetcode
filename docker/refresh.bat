REM COMPOSE_PROJECT_NAME=dev-leetcode
docker-compose -p dev-leetcode -f docker-compose.dev.yml down --remove-orphans
docker-compose -p dev-leetcode -f docker-compose.dev.yml up -d
docker-compose -p dev-leetcode -f docker-compose.dev.yml down sonarqube
docker-compose -p dev-leetcode -f docker-compose.dev.yml up sonarqube -d

docker-compose -p dev-kafka -f kafka.cluster.yml down --remove-orphans
docker-compose -p dev-kafka -f kafka.cluster.yml up -d

docker events
docker image prune -a
docker volume prune
docker system prune --volumes


REM docker container exec -it dev-leetcode_mysql-db_1 bash
mysql -uroot -p123456
	CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';
	GRANT ALL PRIVILEGES ON test_springboot.* TO 'test'@'localhost';
	CREATE USER 'test'@'%' IDENTIFIED BY 'test';
	GRANT ALL PRIVILEGES ON test_springboot.* TO 'test'@'%';
REM docker container exec -it dev-leetcode_redis-db_1 bash
REM docker container exec -it dev-leetcode_canal-server_1 bash
REM docker container exec -it dev-leetcode_rabbitmq_1 bash
REM docker container exec -it dev-leetcode_etcd-1_1 bash
REM docker container exec -it dev-leetcode_zookeeper-1_1 bash
REM docker container exec -it dev-leetcode_kafka-1_1 bash
REM docker container exec -it dev-leetcode_kafka-manager_1 bash
REM docker container exec -it dev-leetcode_elk-kibana-1_1 bash
REM docker container exec -it dev-leetcode_elk-elasticsearch-1_1 bash
REM docker container exec -it dev-leetcode_clickhouse-db_1 bash
REM docker container exec -it dev-leetcode_mongo-db_1 bash
REM docker container exec -it dev-leetcode_gremlin-console_1 bash

REM docker container logs dev-leetcode_redis-db_1
REM docker container logs dev-leetcode_mysql-db_1
REM docker container logs dev-leetcode_canal-server_1
REM docker container logs dev-leetcode_rabbitmq_1
REM docker container logs dev-leetcode_etcd-1_1
REM docker container logs dev-leetcode_zookeeper-1_1
REM docker container logs dev-leetcode_kafka-1_1
REM docker container logs dev-leetcode_kafka-manager_1
REM docker container logs dev-leetcode_elk-elasticsearch-1_1
REM docker container logs dev-leetcode_elk-kibana-1_1
REM docker container logs dev-leetcode_gremlin-console_1

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

2.2.2.0

<!-- 阿里云仓库 -->
<mirror>
	<id>alimaven</id>
	<mirrorOf>central</mirrorOf>
	<name>aliyun maven</name>
	<url>https://maven.aliyun.com/repository/central</url>
</mirror>

REM Docker mirror: https://r3cphckj.mirror.aliyuncs.com


REM Swarm

docker swarm init --advertise-addr=192.168.1.29
docker swarm join --token
docker swarm join-token manager
docker node ls
docker swarm leave -f

docker service create -p 8888:8080 --name my-tomcat tomcat:latest
docker service ls
docker service ps my-tomcat
docker service scale my-tomcat=2
docker service rm my-tomcat

https://www.portainer.io/
docker volume create portainer_data
docker run -d -p 8000:8000 -p 9000:9000 --name=portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce
