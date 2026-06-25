package com.techforge.reservation.controller;

import com.techforge.reservation.entity.Customer;
import com.techforge.reservation.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @PostMapping("/registerAll")
    public List<Customer> registerCustomers(@RequestBody List<Customer> customers){
        return customerService.createCustomers(customers);
    }
}
