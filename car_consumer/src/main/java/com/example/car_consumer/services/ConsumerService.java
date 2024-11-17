package com.example.car_consumer.services;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {


    @KafkaListener(topics = "car-consumer-system",groupId = "car-consumer-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println(record.value());
    }
}
