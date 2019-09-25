package com.snowcatsystems.nasascraper.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.util.Map;


@Component
public class OnMessageListener {

    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void receivedMessage(@Payload Message msg) {
        Map<String, Object> headers = msg.getMessageProperties().getHeaders();

        for (Map.Entry<String, Object> header : headers.entrySet())
        {
            System.out.println(header.getKey() + " : " + header.getValue());
        }
    }
}
