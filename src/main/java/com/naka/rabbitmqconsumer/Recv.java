package com.naka.rabbitmqconsumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Recv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(System.getenv().getOrDefault("RABBITMQ_HOST", "localhost"));
        factory.setUsername(System.getenv().getOrDefault("RABBITMQ_USERNAME", "guest"));
        factory.setPassword(System.getenv().getOrDefault("RABBITMQ_PASSWORD", "guest"));
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        int processSeconds = Integer.parseInt(System.getenv().getOrDefault("PROCESS_SECONDS", "10"));

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Waiting for messages. To exit press CTRL+C");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            Date date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date) + " Received '" + message + "'. It will take " + processSeconds + " seconds to complete processing.");
            try {
                TimeUnit.SECONDS.sleep(processSeconds);
            } catch(InterruptedException e) {
                System.out.println("sleep failed");
            }
            date = new Date(System.currentTimeMillis());
            System.out.println(formatter.format(date) + " Finished Processing '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}
