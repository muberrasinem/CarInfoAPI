package org.muberra.carapi;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.muberra.carapi.model.Car;
import org.muberra.carapi.repository.CarRepository;
import org.muberra.carapi.service.CarService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    private Car car1;
    private Car car2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        car1 = new Car();
        car1.setId(1L);
        car1.setMake("Toyota");
        car1.setModel("Camry");
        car1.setYear(2020);
        car1.setColor("Red");

        car2 = new Car();
        car2.setId(2L);
        car2.setMake("Honda");
        car2.setModel("Civic");
        car2.setYear(2019);
        car2.setColor("Blue");
    }

    @Test
    void getAllCars_ShouldReturnListOfCars() {
        // Arrange
        List<Car> carList = Arrays.asList(car1, car2);
        when(carRepository.findAll()).thenReturn(carList);

        // Act
        List<Car> result = carService.getAllCars();

        // Assert
        assertEquals(2, result.size());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void getCarById_ShouldReturnCar_WhenCarExists() {
        // Arrange
        when(carRepository.findById(1L)).thenReturn(Optional.of(car1));

        // Act
        Car result = carService.getCarById(1L);

        // Assert
        assertEquals("Toyota", result.getMake());
        verify(carRepository, times(1)).findById(1L);
    }

    @Test
    void getCarById_ShouldThrowException_WhenCarDoesNotExist() {
        // Arrange
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> carService.getCarById(1L));
        assertEquals("Car not found with id: 1", exception.getMessage());
    }

    @Test
    void createCar_ShouldSaveAndReturnNewCar() {
        // Arrange
        when(carRepository.save(car1)).thenReturn(car1);

        // Act
        Car result = carService.createCar(car1);

        // Assert
        assertNotNull(result);
        assertEquals("Toyota", result.getMake());
        verify(carRepository, times(1)).save(car1);
    }

    @Test
    void updateCar_ShouldUpdateAndReturnUpdatedCar() {
        // Arrange
        Car updatedCar = new Car();
        updatedCar.setMake("Toyota");
        updatedCar.setModel("Corolla");
        updatedCar.setYear(2021);
        updatedCar.setColor("White");

        when(carRepository.findById(1L)).thenReturn(Optional.of(car1));
        when(carRepository.save(car1)).thenReturn(car1);

        // Act
        Car result = carService.updateCar(1L, updatedCar);

        // Assert
        assertEquals("Toyota", result.getMake());
        assertEquals("Corolla", result.getModel());
        assertEquals(2021, result.getYear());
        assertEquals("White", result.getColor());
        verify(carRepository, times(1)).save(car1);
    }

    @Test
    void deleteCar_ShouldDeleteCar_WhenCarExists() {
        // Arrange
        when(carRepository.findById(1L)).thenReturn(Optional.of(car1));

        // Act
        carService.deleteCar(1L);

        // Assert
        verify(carRepository, times(1)).delete(car1);
    }

    @Test
    void deleteCar_ShouldThrowException_WhenCarDoesNotExist() {
        // Arrange
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> carService.deleteCar(1L));
        assertEquals("Car not found with id: 1", exception.getMessage());
    }
}
