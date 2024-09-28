package com.oscaris.kitchen.order_service.producer;/*
*
@author ameda
@project order-service
*
*/


import com.oscaris.kitchen.order_service.config.KafkaConfig;
import com.oscaris.kitchen.order_service.payload.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class OrderController {

    @Autowired
    private KafkaTemplate<String, Order> template;

    @Autowired
    private KafkaConfig kafkaConfig;

    @Value("${kafka.topic.name}")
    private String topicName;

    private static  final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);


    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Order order){
        System.out.println("Initiated order :: "+order);
        LOGGER.info(String.format("Message  sent -> %s",order.toString()));
//        template.send(topicName,"Order_Create "+order)
//                .whenComplete(
//                        (sendResult, throwable)->{
//                            if(throwable == null) {
//                                System.out.println(sendResult);
//                            }else{
//                                throwable.printStackTrace();
//                            }
//                });
        Message<Order> message = MessageBuilder
                .withPayload(order)
                .setHeader(KafkaHeaders.TOPIC,topicName)
                .build();

         template.send(message)
                 .whenComplete((sendResult,throwable)->{
                     if(throwable == null){
                         LOGGER.info(String.format("Result was sent my guy -> %s",sendResult));
                     }else {
                         throwable.printStackTrace();
                     }
                 });
        return ResponseEntity.ok("Order created successfully.");
    }

}
