package com.oscaris.kitchen.order_service.config;/*
*
@author ameda
@project order-service
*
*/

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Value("${kafka.topic.partitions:1}")
    private int partitions;

    @Value("${kafka.topic.replicas:1}")
    private short replicas;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        return new KafkaAdmin(kafkaProperties());
    }

    private Map<String, Object> kafkaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:9092"); // Change to your Kafka broker address
        return props;
    }

    @Bean
    public NewTopic createTopic() {
        return new NewTopic(topicName, partitions, replicas);
    }

}
