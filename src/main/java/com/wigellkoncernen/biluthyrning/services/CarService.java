package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Car;
import com.wigellkoncernen.biluthyrning.exceptions.ResourceAlreadyExists;
import com.wigellkoncernen.biluthyrning.repositories.CarRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService implements CarServiceInterface {
    @Autowired
    private final CarRepository carRepository;

    Logger logger = Logger.getLogger(CarService.class);

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAvailableCars() {
        return carRepository.findByBookedFalse();
    }


    @Override
    public Car addNewCar(Car car) {
        for (Car c : carRepository.findAll()) {
            if (car.getPlateNo().equals(c.getPlateNo())) {
                throw new ResourceAlreadyExists("Car", "PlateNo", car.getPlateNo());
            }
        }
        logger.log(Level.WARN, "Admin added new car with plateNo " + car.getPlateNo());
        return carRepository.save(car);
    }



}