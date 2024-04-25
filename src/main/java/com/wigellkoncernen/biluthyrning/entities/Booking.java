package com.wigellkoncernen.biluthyrning.entities;

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

    @Column
    private boolean booked;
    @JsonIgnoreProperties("listOfBookings")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Customer customer;
    @JsonIgnoreProperties("listOfBookings")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Car car;

    @Column
    private Double totalPrice;


    public Booking() {
    }

    public Booking(LocalDate startDate, LocalDate endDate, boolean booked, Customer customer, Car car, Double totalPrice) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.booked = booked;
        this.customer = customer;
        this.car = car;
        this.totalPrice = totalPrice;
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

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
