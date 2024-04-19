package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Booking;
import com.wigellkoncernen.biluthyrning.entities.Customer;
import com.wigellkoncernen.biluthyrning.exceptions.ResourceAlreadyExists;
import com.wigellkoncernen.biluthyrning.repositories.BookingRepository;
import com.wigellkoncernen.biluthyrning.exceptions.ResourceNotFoundException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class BookingService implements BookingServiceInterface {
    Logger logger = Logger.getLogger(BookingService.class);

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public List<Booking> getBookings() {
        return null;
    }

    @Override
    public List<Booking> getMyBookings(Customer customer) {
        return null;
    }


    @Override
    public void cancelBooking(Booking booking) {
        bookingRepository.findById(booking.getId()).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", booking.getId()));
        booking.setBooked(false);
        bookingRepository.save(booking);
        logger.log(Level.WARN, "Customer canceled booking with id " + booking.getId());
        //TODO kolla om det går att använda bara id och skicka med det från postman istället
    }


    @Override
    public void deleteBooking(Booking booking) {

    }

    @Override
    public Booking bookCar(Booking newBooking) { //TODO i postman om jag anger  id för bokningen får jag med alla info om kund och bil utifrån id
        //men skriver jag inte med boknings id får jag null värden på allt från kund och bil

        if (bookingRepository.existsById(newBooking.getCustomer().getId())) { // om kunden finns

            if (bookingRepository.existsById(newBooking.getCar().getId())) {//om bilen finns
                if (bookingDateAvailable(newBooking)) {
                    logger.log(Level.WARN, "Customer with id " + newBooking.getCustomer().getId() + " have booked a car");
                    return bookingRepository.save(newBooking);
                } else
                    throw new ResourceAlreadyExists("Order", "date", newBooking.getStartDate() + " - " + newBooking.getEndDate());
            } else
                throw new ResourceNotFoundException("Car", "id", newBooking.getCar().getId());
        } else throw new
                ResourceNotFoundException("Customer", "id", newBooking.getCustomer().getId());
    }


    public boolean bookingDateAvailable(Booking newBooking) {

        boolean bookingDateFree = true;

        for (Booking oldBooking : bookingRepository.findAll()) {
            if (oldBooking.getCar().getId().equals(newBooking.getCar().getId())) { //Går bara igenom de ordrar som har samma bil som i vill boka

                System.out.println("Start gammal " + oldBooking.getStartDate());
                System.out.println("SLut gammal " + oldBooking.getEndDate());

                if ((newBooking.getStartDate().isAfter(oldBooking.getStartDate()) &&  //Nya startdatum är efter gamla startdatum och
                        newBooking.getStartDate().isBefore(oldBooking.getEndDate()) //nya startdatum är före gamla slutdatum
                )                                                                     //eller
                        || (newBooking.getStartDate().isBefore(oldBooking.getStartDate()) && //nya startdatumet är före gamla startdatumet och
                        newBooking.getEndDate().isAfter(oldBooking.getStartDate())           //nya slutdatumet är efter gamla startdatumet
                )                                                                           //eller
                        || (newBooking.getStartDate().isEqual(oldBooking.getStartDate()))   //nya startdatumet är samma som gamla startdatum eller
                        || (newBooking.getEndDate().isEqual(oldBooking.getEndDate()))       //nya slutdatum är samma som gamla slutdatum
                ) {
                    bookingDateFree = false;
                }
            }
        }
        return bookingDateFree;
    }

}