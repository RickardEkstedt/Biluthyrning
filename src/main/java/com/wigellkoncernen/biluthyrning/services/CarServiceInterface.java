package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Car;

import java.util.List;

public interface CarServiceInterface {
    List<Car> getAvailableCars();

   List<Car> getAllCars();


    //kund lista tillgängliga bilar, ev. via CarDTO entity (separata repo, service & controller)
   /* String addCar(Car car);
    void deleteCar(Long id);
    void updateCar(Car car);
    List<Car> getCars();*/





}
