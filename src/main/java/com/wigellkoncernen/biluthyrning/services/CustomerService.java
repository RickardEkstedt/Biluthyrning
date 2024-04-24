package com.wigellkoncernen.biluthyrning.services;

import com.wigellkoncernen.biluthyrning.entities.Customer;
import com.wigellkoncernen.biluthyrning.exceptions.ResourceNotFoundException;
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
        Customer existingCustomer = customerRepository.findById(customer.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Customer", "id", customer.getId()));
        customerRepository.deleteById(existingCustomer.getId());
        logger.log(Level.WARN, "Customer with id " + existingCustomer.getId() + " has been deleted");
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        Customer excistingCustomer = customerRepository.findById(customer.getId()).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customer.getId()));
        excistingCustomer.setName(customer.getName());
        excistingCustomer.setUserName(customer.getUserName());
        excistingCustomer.setEmail(customer.getEmail());
        excistingCustomer.setAdress(customer.getAdress());
        excistingCustomer.setPhone(customer.getPhone());
        customerRepository.save(excistingCustomer);
        logger.log(Level.WARN, "Customer with id " + excistingCustomer.getId() + " has been updated");
        return excistingCustomer;
    }

    public Customer addNewCustomer(Customer customer) {

        logger.log(Level.WARN, "Admin added new customer " + customer);
        return customerRepository.save(customer);
    }
}
