package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Booking;
import com.wigellkoncernen.biluthyrning.entities.Customer;


import java.util.List;

public interface BookingServiceInterface {

    List<Booking> getBookings();

    List<Booking> getAllBookings(Customer customer);

    void cancelBooking(Booking booking);

    void deleteBooking(Booking booking);

    String bookCar(Booking booking);

}