package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Customer;
import com.wigellkoncernen.biluthyrning.repositories.CustomerRepository;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService implements CustomerServiceInterface {
    @Autowired
    private CustomerRepository customerRepository;

    Logger logger = Logger.getLogger(CustomerService.class);


    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }


    @Override
    public void deleteCustomer(Customer customer) {

    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    public Customer addNewCustomer(Customer customer) {

        logger.log(Level.WARN, "Admin added new customer " + customer);
        return customerRepository.save(customer);
    }
}
