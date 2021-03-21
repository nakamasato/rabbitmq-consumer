# Simple RabbitMQ Consumer

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
    java -jar target/rabbitmqconsumer-1.0-SNAPSHOT-jar-with-dependencies.jar
    ```
1. Publish a message
    ```
    rabbitmqadmin publish routing_key="hello" payload="hello, world"
    ```

1. Check

    ```
    [*] Waiting for messages. To exit press CTRL+C
    [x] Received 'hello, world'
    [x] Received 'hello, world'
    [x] Received 'hello, world'
    ```

### With Docker

1. Run RabbitMQ and this application

    ```
    docker-compose up --build
    ```

1. Publish a message

    ```
    docker exec -it $(docker ps | grep rabbitmq-consumer_rabbitmq_1 | awk '{print $1}') rabbitmqadmin publish routing_key="hello" payload="hello, world"
    ```