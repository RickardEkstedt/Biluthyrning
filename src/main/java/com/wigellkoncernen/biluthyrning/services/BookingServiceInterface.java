package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Booking;
import com.wigellkoncernen.biluthyrning.entities.Customer;

import java.util.List;

public interface BookingServiceInterface {

    List<Booking> getBookings();
    List<Booking> getMyBookings(Customer customer); //request-body

    void cancelBooking(Booking booking); //request-body, PUT

    void deleteBooking(Booking booking);






}
