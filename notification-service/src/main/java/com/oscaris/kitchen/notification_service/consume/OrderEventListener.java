package com.oscaris.kitchen.notification_service.consume;
/*
*
@author ameda
@project notification-service
*
*/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class OrderEventListener {

    @Value("${kafka.topic.name}")
    private String topicName;

    @KafkaListener(id = "orderEventListener",topics = "my-topic" )
    public void consume(String order){
        System.out.println("Received event :: "+order);
    }
}
