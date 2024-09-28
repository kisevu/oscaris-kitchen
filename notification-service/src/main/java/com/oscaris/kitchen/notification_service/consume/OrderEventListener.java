package com.oscaris.kitchen.notification_service.consume;
/*
*
@author ameda
@project notification-service
*
*/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;


@Component
public class OrderEventListener {

    @Value("${kafka.topic.name}")
    private String topicName;

    @KafkaListener(id = "orderEventListener",topics = "my-topic" )
     // 😅when to retry n when delay updated 😅
    //😅attempts set to 3 even though the maxDelay is not adhered but atleast it tried for😅
    //😅 3 times then the error can be thrown. 😅
    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 2000L, maxDelay = 10000L,
    multiplier = 2))
    public void consume(String order){
        System.out.println("Received event :: "+order);
        throw new RuntimeException();
        /*
        * just above code added as a way of testing the @Retryables
        * */
    }
}
