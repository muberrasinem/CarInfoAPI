package org.muberra.carapi.service;


import org.muberra.carapi.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class CarKafkaProducer {
    private static final String TOPIC = "car-consumer-system";
    @Autowired
    private KafkaTemplate<String, Car> kafkaTemplate;

    public void sendCar(Car car) {
        kafkaTemplate.send(TOPIC, car);
    }
}
