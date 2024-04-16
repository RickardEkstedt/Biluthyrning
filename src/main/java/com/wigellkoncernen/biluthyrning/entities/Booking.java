package com.wigellkoncernen.biluthyrning.entities;


//är alla bokningar bara direkt ifrån idag? kan man kolla en vecka frammåt och se vilka som är bokade?
//är datumet för bokning bara från idag och datumet man anger är sista dagen?

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
        private LocalDate date;
    //private LocalDate endDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Car car;

    public Booking() {
    }

    public Booking(LocalDate date, Customer customer, Car car) {
        this.date = date;
        this.customer = customer;
        this.car = car;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
