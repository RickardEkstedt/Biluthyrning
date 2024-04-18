package com.wigellkoncernen.biluthyrning.repositories;

import com.wigellkoncernen.biluthyrning.entities.Booking;
import com.wigellkoncernen.biluthyrning.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer(Customer customer);
}


