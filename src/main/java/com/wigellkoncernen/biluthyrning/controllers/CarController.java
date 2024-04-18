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

    @GetMapping(value ="/cars")
    public ResponseEntity<List<Car>> getAvailableCars () {
        List<Car> availableCars = carService.getAvailableCars();
        return ResponseEntity.ok(availableCars);
    }

    @DeleteMapping(value = "/deletecar")
    public void deleteCar(@RequestBody Car car) {
        carService.deleteCar(car);
    }
}
