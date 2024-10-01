package com.oscaris.kitchen.order_service.consume;
/*
*
@author ameda
@project notification-service
*
*/

import com.oscaris.kitchen.order_service.payload.NotificationRequest;
import com.oscaris.kitchen.order_service.payload.Order;
import com.oscaris.kitchen.order_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private static  final Logger LOGGER = LoggerFactory.getLogger(OrderEventListener.class);
    @Value("${kafka.topic.name}")
    private String topicName;

    private final NotificationService notificationService;


    @KafkaListener(id = "orderEventListener",topics = "my-topic" )
     // 😅when to retry n when delay updated 😅
    //😅attempts set to 3 even though the maxDelay is not adhered but atleast it tried for😅
    //😅 3 times then the error can be thrown. 😅
    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 2000L, maxDelay = 10000L,
    multiplier = 2))
    public void consume(Order order){

        NotificationRequest req = NotificationRequest.builder()
                .message("")
                .orderItem(order.getOrderName())
                .build();
        notificationService.saveNotification(req);
        System.out.println(order);

//        throw new RuntimeException();
        /*
        * just above code added as a way of testing the @Retryables
        * */
    }
}
