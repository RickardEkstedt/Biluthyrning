package com.wigellkoncernen.biluthyrning.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties("customer")
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Booking> listOfBookings = new ArrayList<>();
    @Column (length =  35, nullable = false)
    private String name;
    @Column (length =  25, nullable = false)
    private String userName;
    @Column (length =  35, nullable = false)
    private String email;
    @Column (length =  60, nullable = false)
    private String adress;
    @Column (length =  20, nullable = false)
    private Long phone;

    public Customer() {
    }

    public Customer(List<Booking> listOfBookings, String name, String userName, String email, String adress, Long phone) {
        this.listOfBookings = listOfBookings;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.adress = adress;
        this.phone = phone;
    }

    public List<Booking> getListOfBookings() {
        return listOfBookings;
    }

    public void setListOfBookings(List<Booking> listOfBookings) {
        this.listOfBookings = listOfBookings;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
