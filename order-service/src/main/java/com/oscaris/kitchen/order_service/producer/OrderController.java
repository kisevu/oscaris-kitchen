package com.oscaris.kitchen.order_service.producer;/*
*
@author ameda
@project order-service
*
*/


import com.oscaris.kitchen.order_service.config.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private KafkaTemplate<String,String> template;

    @Autowired
    private KafkaConfig kafkaConfig;

    @Value("${kafka.topic.name}")
    private String topicName;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody String order){
        System.out.println("Initiated order :: "+order);
        template.send(topicName,"Order_Create "+order)
                .whenComplete(
                        (sendResult, throwable)->{
                            if(throwable == null) {
                                System.out.println(sendResult);
                            }else{
                                throwable.printStackTrace();
                            }
                });
        return ResponseEntity.ok("Order created successfully.");
    }
}
