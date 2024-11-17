package org.muberra.carapi.controller;



import org.muberra.carapi.model.Car;
import org.muberra.carapi.service.CarService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    //@Autowired
    private CarService carService;

    // Constructor-based Dependency Injection (preferred over @Autowired)
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }
/*
    @GetMapping("/{id}")
    @Cacheable(value = "car", key = "#id")
    public ResponseEntity<Car> getCarById(@PathVariable("id") long id) {
        Car car = carService.getCarById(id);
        return ResponseEntity.ok(car);
    }*/
    @GetMapping("/{id}")
    @Cacheable(value = "car", key = "#id")
    public Car getCarById(@PathVariable("id") long id) {
        return carService.getCarById(id); // ResponseEntity döndürmek yerine Car döndürdük
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car newCar = carService.createCar(car);
        return ResponseEntity.ok(newCar);
    }
    /*
    @PutMapping("/{id}")
    @CachePut(value = "car", key = "#id")
    public ResponseEntity<Car> updateCar(@PathVariable("id") long id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id, car);
        return ResponseEntity.ok(updatedCar);
    }*/
    @PutMapping("/{id}")
    @CachePut(value = "car", key = "#id")
    public Car updateCar(@PathVariable("id") long id, @RequestBody Car car) {
        return carService.updateCar(id, car); // ResponseEntity döndürmek yerine Car döndürdük
    }
/*
    @DeleteMapping("/{id}")
    @CacheEvict(value = "car", key = "#id")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
*/
    @DeleteMapping("/{id}")
    @CacheEvict(value = "car", key = "#id")
    public ResponseEntity<Void> deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
