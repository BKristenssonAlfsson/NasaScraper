package com.snowcatsystems.nasascraper.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;


@Component
public class OnMessageListener {

    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void receivedMessage(@Payload Message msg) {

        //System.out.println("Header:::::::: " + header);
        System.out.println(msg);
        byte[] body = msg.getBody();
        JsonArray json = jsonFromString(new String(body));
        System.out.println(json.getJsonObject(1));

    }

    private JsonArray jsonFromString(String input) {
        JsonReader jsonReader = Json.createReader(new StringReader(input));
        JsonArray object = jsonReader.readArray();
        System.out.println(object);
        jsonReader.close();

        return object;
    }
}
