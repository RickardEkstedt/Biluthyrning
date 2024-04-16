package com.wigellkoncernen.biluthyrning.repositories;

import com.wigellkoncernen.biluthyrning.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
