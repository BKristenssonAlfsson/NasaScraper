package com.snowcatsystems.nasascraper.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OnMessageListener {

    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void receivedMessage(Object msg) {
        System.out.println("Received Message: " + msg);
    }
}
