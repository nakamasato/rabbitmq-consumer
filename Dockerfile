FROM maven:3.6.3-jdk-11-slim AS BUILD_IMAGE
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY pom.xml $APP_HOME
RUN mvn install
COPY ./src ./src
RUN mvn clean compile assembly:single

FROM adoptopenjdk/openjdk11:alpine-slim
WORKDIR /root/
COPY --from=BUILD_IMAGE /usr/app/target/rabbitmq-consumer-1.0-SNAPSHOT-jar-with-dependencies.jar ./rabbitmq-consumer-jar.jar
CMD ["java","-jar","rabbitmq-consumer-jar.jar"]
