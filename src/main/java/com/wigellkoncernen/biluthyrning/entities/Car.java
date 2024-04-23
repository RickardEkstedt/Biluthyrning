package com.wigellkoncernen.biluthyrning.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties("car")
    @OneToMany(/*fetch = FetchType.LAZY, */mappedBy = "car", cascade = CascadeType.ALL)
    private List<Booking> listOfBookings = new ArrayList<>();
    @Column (length =  15, nullable = false)
    private String plateNo;
    @Column (nullable = false)
    private int price;
    @Column (length =  20, nullable = false)
    private String manufacturer;
    @Column (length =  25, nullable = false)
    private String model;

    private boolean booked;

    public Car() {
    }

    public Car(ArrayList<Booking> listOfBookings, String plateNo, int price, String manufacturer, String model, boolean booked) {
        this.listOfBookings = listOfBookings;
        this.plateNo = plateNo;
        this.price = price;
        this.manufacturer = manufacturer;
        this.model = model;
        this.booked = booked;
    }
@JsonIgnore
    public List<Booking> getListOfBookings() {
        return listOfBookings;
    }

    public void setListOfBookings(List<Booking> listOfBookings) {
        this.listOfBookings = listOfBookings;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
