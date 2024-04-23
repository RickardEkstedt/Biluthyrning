package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Car;
import com.wigellkoncernen.biluthyrning.entities.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    String addCustomer(Customer customer);
    List<Customer> getCustomers();
    void deleteCustomer(Customer customer);
    Customer updateCustomer(Customer customer);


}
