package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Car;
import com.wigellkoncernen.biluthyrning.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService implements CarServiceInterface {
    @Autowired
    private final CarRepository carRepository;


    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.findByBookedFalse();
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}