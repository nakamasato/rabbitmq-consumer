# Simple RabbitMQ Consumer

## Overview

This application does the followings:
- consume RabbitMQ messages from a queue named `hello`
- need `PROCESS_SECONDS` seconds to complete processing each message
- consume one message at a time (`basicQos(prefetchCount = 1)`, `unacked` message won't exceed one)
- send an acknowledgement after processing a message

## Version

- Java 11
- Maven 3.6.3

## Run RabbitMQ Consumer

### With local java and RabbitMQ

1. Run RabbitMQ

    Mac OS:

    ```
    brew install rabbitmq
    brew services start rabbitmq
    export PATH=$PATH:/usr/local/opt/rabbitmq/sbin # to add rabbitmq cli
    ```

    Other OS: TBD

1. Build

    ```
    mvn clean compile assembly:single
    ```
1. Run
    ```
    java -jar target/rabbitmq-consumer-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```
1. Publish a message
    ```
    rabbitmqadmin publish routing_key="hello" payload="hello, world"
    ```

1. Check

    ```
    Waiting for messages. To exit press CTRL+C
    Received 'hello, world'. It will take 10 seconds to complete processing.
    Finished Processing 'hello, world'
    Received 'hello, world'. It will take 10 seconds to complete processing.
    Finished Processing 'hello, world'
    Received 'hello, world'. It will take 10 seconds to complete processing.
    Finished Processing 'hello, world'
    Received 'hello, world'. It will take 10 seconds to complete processing.
    Finished Processing 'hello, world'
    Received 'hello, world'. It will take 10 seconds to complete processing.
    ```

### With Docker

1. Run RabbitMQ and this application

    ```
    docker-compose up # docker-compose up --build if you need to build
    ```

1. Publish a message

    ```
    docker exec -it $(docker ps | grep rabbitmq-consumer_rabbitmq_1 | awk '{print $1}') rabbitmqadmin publish routing_key="hello" payload="hello, world"
    ```
