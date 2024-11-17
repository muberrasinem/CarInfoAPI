package org.muberra.carapi.service;


import org.muberra.carapi.model.Car;
import org.muberra.carapi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Autowired
    private CarKafkaProducer carKafkaProducer;


    // GET all car
    public List<Car> getAllCars() {
        List<Car>car = new ArrayList<Car>();
        carRepository.findAll().forEach(e -> car.add(e));
        return car;
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Car not found with id: "+id));
    }

    public Car createCar(Car car) {
        Car savedCar = carRepository.save(car);
        carKafkaProducer.sendCar(savedCar); // Send to Kafka
        return savedCar;
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car existingCar = getCarById(id);
        existingCar.setMake(updatedCar.getMake());
        existingCar.setModel(updatedCar.getModel());
        existingCar.setYear(updatedCar.getYear());
        existingCar.setColor(updatedCar.getColor());

        return carRepository.save(existingCar);
    }

    public void deleteCar(Long id) {
        Car car = getCarById(id);
        carRepository.delete(car);
    }




}
