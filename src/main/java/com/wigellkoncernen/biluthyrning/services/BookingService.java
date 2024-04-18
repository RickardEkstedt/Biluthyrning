package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Booking;
import com.wigellkoncernen.biluthyrning.entities.Customer;
import com.wigellkoncernen.biluthyrning.repositories.BookingRepository;
import com.wigellkoncernen.biluthyrning.repositories.exceptions.ResourceNotFoundException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingService implements BookingServiceInterface {
    Logger logger = Logger.getLogger(BookingService.class);

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings(Customer customer) {
        List<Booking> bookings = bookingRepository.findByCustomer(customer);
        if (bookings.isEmpty()) {
            throw new ResourceNotFoundException("Booking", "customer", customer.getId());
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookings() {
        return null;
    }


    @Override
    public void cancelBooking(Booking booking) {
        bookingRepository.findById(booking.getId()).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", booking.getId()));
//booking.setBooked(false);
       // booking.setBooked(false);
        bookingRepository.save(booking);
        logger.log(Level.WARN, "Booking with id " + booking.getId() + " canceled");
        //TODO kolla om det går att använda bara id och skicka med det från postman istället
    }

    @Override
    public void deleteBooking(Booking booking) {

    }

}
