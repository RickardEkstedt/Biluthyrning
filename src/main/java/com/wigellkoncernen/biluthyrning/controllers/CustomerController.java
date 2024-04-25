package com.wigellkoncernen.biluthyrning.controllers;

import com.wigellkoncernen.biluthyrning.entities.Customer;
import com.wigellkoncernen.biluthyrning.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @DeleteMapping(value = "/deletecustomer")
    public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer) {
        customerService.deleteCustomer(customer);
        return ResponseEntity.ok("Customer deleted");
    }

    @PutMapping(value = "/updatecustomer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }


    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.addNewCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
}