package com.wigellkoncernen.biluthyrning.entities;


//är alla bokningar bara direkt ifrån idag? kan man kolla en vecka frammåt och se vilka som är bokade?
//är datumet för bokning bara från idag och datumet man anger är sista dagen?

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @JsonIgnoreProperties("listOfBookings")
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @JsonIgnoreProperties("listOfBookings")
    @ManyToOne(cascade = CascadeType.ALL)
    private Car car;

    public Booking() {
    }

    public Booking(LocalDate startDate,LocalDate endDate, Customer customer, Car car) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.car = car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate date) {
        this.startDate = date;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
