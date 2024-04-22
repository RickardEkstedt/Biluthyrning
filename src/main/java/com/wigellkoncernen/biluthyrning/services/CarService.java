package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Booking;
import com.wigellkoncernen.biluthyrning.entities.Car;
import com.wigellkoncernen.biluthyrning.exceptions.ResourceAlreadyExists;
import com.wigellkoncernen.biluthyrning.repositories.CarRepository;
import com.wigellkoncernen.biluthyrning.exceptions.ResourceNotFoundException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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




    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    @Override
    public void deleteCar(Car car) {
        Car existingCar = carRepository.findById(car.getId()).orElseThrow(() -> new ResourceNotFoundException("Car", "id", car.getId()));
        for (Booking booking : existingCar.getListOfBookings()) {
            booking.setCar(null);
        }
        existingCar.getListOfBookings().clear();
        carRepository.save(existingCar);
        carRepository.deleteById(existingCar.getId());
        logger.log(Level.WARN, "Car with id "+existingCar.getId()+" was deleted");
    }
    public void updateCar(Car updatedCar) {
        Long carId = updatedCar.getId();
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            // Uppdatera egenskaper för bilen med de nya värdena från updatedCar
            car.setPlateNo(updatedCar.getPlateNo());
            car.setManufacturer(updatedCar.getManufacturer());
            car.setModel(updatedCar.getModel());
            car.setPrice(updatedCar.getPrice());
            car.setBooked(updatedCar.isBooked());

            carRepository.save(car);

            // Konvertera carId till en sträng och logga den
            logger.log(Level.INFO,"Car with ID " + carId.toString() + " updated");
        } else {
            throw new ResourceNotFoundException("Car", "id", carId);
        }
    }

}