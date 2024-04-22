package com.wigellkoncernen.biluthyrning.controllers;


import com.wigellkoncernen.biluthyrning.entities.Car;
import com.wigellkoncernen.biluthyrning.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    public ResponseEntity<List<Car>> getAvailableCars() {
        List<Car> availableCars = carService.getAvailableCars();
        return ResponseEntity.ok(availableCars);
    }

    @PostMapping(value = "/addcar")
    public ResponseEntity<Car> addNewCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.addNewCar(car));}

    @GetMapping("/allcars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> allCars = carService.getAllCars();
        return ResponseEntity.ok().body(allCars);

    }
    @PutMapping(value = "/updatecar")
    public ResponseEntity<String> updateCar(@RequestBody Car updatedCar) {
        try {
            carService.updateCar(updatedCar);
            return ResponseEntity.ok("Car updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating car: " + e.getMessage());
        }
    }

    @DeleteMapping(value = "/deletecar")
    public void deleteCar(@RequestBody Car car) {
        carService.deleteCar(car);
    }
}


