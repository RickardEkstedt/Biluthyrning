package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Booking;
import com.wigellkoncernen.biluthyrning.entities.Customer;

import com.wigellkoncernen.biluthyrning.repositories.BookingRepository;
import com.wigellkoncernen.biluthyrning.exceptions.ResourceNotFoundException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

        // Beräkna det totala priset för varje bokning och sätt det i objektet
        for (Booking booking : bookings) {
            double totalPrice = calculateTotalPrice(booking.getStartDate(), booking.getEndDate(), booking.getCar().getPrice());
            booking.setTotalPrice(totalPrice);
        }

        return bookings;
    }


    @Override
    public List<Booking> getBookings() {
        List<Booking> bookings = bookingRepository.findAll();

        // Beräkna det totala priset för varje bokning och sätt det i objektet
        for (Booking booking : bookings) {
            double totalPrice = calculateTotalPrice(booking.getStartDate(), booking.getEndDate(), booking.getCar().getPrice());
            booking.setTotalPrice(totalPrice);
        }

        return bookings;
    }

    @Override
    public void cancelBooking(Booking booking) {
        bookingRepository.findById(booking.getId()).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", booking.getId()));
        booking.setBooked(false);
        bookingRepository.save(booking);
        logger.log(Level.WARN, "Booking with id " + booking.getId() + " canceled");

    }

    @Override
    public void deleteBooking(Booking booking) {
        bookingRepository.findById(booking.getId()).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", booking.getId()));
        bookingRepository.delete(booking);
        logger.log(Level.WARN, "Booking with id " + booking.getId() + " deleted");
    }


    @Override
    public String bookCar(Booking newBooking) {  //Har ändrat till string ist för Booking
        if (bookingRepository.existsById(newBooking.getCustomer().getId())) { // om kunden finns
            if (bookingRepository.existsById(newBooking.getCar().getId())) {//om bilen finns
                if (bookingDateAvailable(newBooking)) {
                    logger.log(Level.WARN, "Customer with id " + newBooking.getCustomer().getId() + " have booked a car");
                    bookingRepository.save(newBooking);
                    return "Customer with id " + newBooking.getCustomer().getId() + " have booked car with id " + newBooking.getCar().getId();
                } else
                    throw new RuntimeException("Date for booking not available");
            } else
                throw new ResourceNotFoundException("Car", "id", newBooking.getCar().getId());
        } else throw new
                ResourceNotFoundException("Customer", "id", newBooking.getCustomer().getId());
    }


    public boolean bookingDateAvailable(Booking newBooking) {
        LocalDate newStartDate = newBooking.getStartDate();
        LocalDate newEndDate = newBooking.getEndDate();

        for (Booking oldBooking : bookingRepository.findAll()) {
            if (oldBooking.getCar().getId().equals(newBooking.getCar().getId())) { //Går bara igenom de ordrar som har samma bil som i vill boka
                LocalDate oldStartDate = oldBooking.getStartDate();
                LocalDate oldEndDate = oldBooking.getEndDate();

                boolean startDateOverlaps = newStartDate.isAfter(oldStartDate) && newStartDate.isBefore(oldEndDate);
                boolean endDateOverlaps = newEndDate.isAfter(oldStartDate) && newEndDate.isBefore(oldEndDate);
                boolean sameStartDate = newStartDate.isEqual(oldStartDate);
                boolean sameEndDate = newEndDate.isEqual(oldEndDate);

                if (startDateOverlaps || endDateOverlaps || sameStartDate || sameEndDate) {
                    return false;
                }
            }
        }
        return true;
    }

    private double calculateTotalPrice(LocalDate startDate, LocalDate endDate, double carPrice) {
        // Om bokningen inte är avslutad ännu
        if (endDate.isAfter(LocalDate.now())) {
            // Beräkna antalet dagar mellan startdatumet och dagens datum inklusive båda dagarna
            long daysBetween = ChronoUnit.DAYS.between(startDate, LocalDate.now()) + 1;

            // Om antalet dagar är mindre än 1, returnera 0 (för att undvika negativa eller nollpriser)
            if (daysBetween < 1) {
                return 0;
            }

            // Beräkna det totala priset genom att multiplicera antalet dagar med bilens pris per dag
            double totalPrice = daysBetween * carPrice;
            return totalPrice;
        } else {
            // Om bokningen är avslutad, använd den ursprungliga beräkningen
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;

            // Om antalet dagar är mindre än 1, returnera 0 (för att undvika negativa eller nollpriser)
            if (daysBetween < 1) {
                return 0;
            }

            // Beräkna det totala priset genom att multiplicera antalet dagar med bilens pris per dag
            double totalPrice = daysBetween * carPrice;
            return totalPrice;
        }
    }
}
