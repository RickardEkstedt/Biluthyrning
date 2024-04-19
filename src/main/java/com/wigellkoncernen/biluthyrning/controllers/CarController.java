package com.wigellkoncernen.biluthyrning.controllers;

import com.wigellkoncernen.biluthyrning.entities.Car;
import com.wigellkoncernen.biluthyrning.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping(value = "/deletecar")
    public void deleteCar(@RequestBody Car car) {
        carService.deleteCar(car);
    }
}
