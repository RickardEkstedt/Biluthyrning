package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Car;

import java.util.List;

public interface CarServiceInterface {
    List<Car> getAvailableCars();


    Car addNewCar(Car car);

   List<Car> getAllCars();

    void deleteCar(Car car);

    void updateCar(Car car);

}