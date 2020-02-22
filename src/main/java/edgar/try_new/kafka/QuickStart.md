# Commands

## Topics
export JMX_PORT=9998
/opt/kafka/bin/kafka-topics.sh --create --zookeeper zookeeper-1:2181,zookeeper-2:2181,zookeeper-3:2181 --replication-factor 1 --partitions 4 --topic mykafka
/opt/kafka/bin/kafka-topics.sh --list --zookeeper zookeeper-1:2181,zookeeper-2:2181,zookeeper-3:2181
/opt/kafka/bin/kafka-topics.sh --list --bootstrap-server kafka-1:9092

## Producer
/opt/kafka/bin/kafka-console-producer.sh --broker-list kafka-1:9092 --topic mykafka

## Consumer
/opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server kafka-1:9092 --topic mykafka --from-beginning