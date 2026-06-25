package com.techforge.reservation.service;

import com.techforge.reservation.entity.Customer;
import com.techforge.reservation.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> createCustomers(List<Customer> customers){
        return customerRepository.saveAll(customers);
    }

}
