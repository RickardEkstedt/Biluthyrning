package com.wigellkoncernen.biluthyrning.controllers;

import com.wigellkoncernen.biluthyrning.entities.Booking;
import com.wigellkoncernen.biluthyrning.services.BookingService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
    @Autowired
    private BookingService bookingService;


    @PutMapping(value = "/cancelorder")
    public ResponseEntity<String> cancelOrder(@RequestBody Booking booking) {
        bookingService.cancelBooking(booking);
        return ResponseEntity.ok("Order canceled");
    }

        @PostMapping("/ordercar")
        public ResponseEntity<Booking> orderCar (@RequestBody Booking booking){
            return ResponseEntity.ok(bookingService.bookCar(booking));
        }

}
