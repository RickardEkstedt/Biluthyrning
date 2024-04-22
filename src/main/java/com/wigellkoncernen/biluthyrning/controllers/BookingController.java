package com.wigellkoncernen.biluthyrning.controllers;

import com.wigellkoncernen.biluthyrning.entities.Booking;
import com.wigellkoncernen.biluthyrning.entities.Customer;
import com.wigellkoncernen.biluthyrning.services.BookingService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/myorders")
    public ResponseEntity<List<Booking>> getMyOrders(@RequestBody Customer customer) {
        List<Booking> myOrders = bookingService.getAllBookings(customer);
        return ResponseEntity.ok().body(myOrders);
    }

    @PutMapping(value = "/cancelorder")
    public ResponseEntity<String> cancelOrder(@RequestBody Booking booking) {
        System.out.println("test");
        bookingService.cancelBooking(booking);
        return ResponseEntity.ok("Order canceled");
    }

    @DeleteMapping(value = "/deleteorder")
    public void deleteBooking(@RequestBody Booking booking) {
        bookingService.deleteBooking(booking);
    }


    @PostMapping("/ordercar")
    public ResponseEntity<String> orderCar(@RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.bookCar(booking));
    }

}


