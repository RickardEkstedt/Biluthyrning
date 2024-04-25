package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Customer;

import java.util.List;

public interface CustomerServiceInterface {

    List<Customer> getCustomers();

    Customer addNewCustomer(Customer customer);
    void deleteCustomer(Customer customer);
    Customer updateCustomer(Customer customer);

}