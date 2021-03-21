# Simple RabbitMQ Consumer

## Prerequisite

Mac OS

```
brew install rabbitmq
brew services start rabbitmq
export PATH=$PATH:/usr/local/opt/rabbitmq/sbin # to add rabbitmq cli
```

## Run RabbitMQ Consumer

```
mvn clean compile assembly:single
```

```
java -jar target/rabbitmqconsumer-1.0-SNAPSHOT-jar-with-dependencies.jar
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
 [*] Waiting for messages. To exit press CTRL+C
```

```
rabbitmqadmin publish routing_key="hello" payload="hello, world"
Message published
```

```
 [*] Waiting for messages. To exit press CTRL+C
 [x] Received 'hello, world'
 [x] Received 'hello, world'
 [x] Received 'hello, world'
```