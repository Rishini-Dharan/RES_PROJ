package com.techforge.reservation.starter;

import com.techforge.reservation.entity.Customer;
import com.techforge.reservation.repository.CustomerRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CustomerStarter implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    private final ObjectMapper mapper;

    public CustomerStarter(CustomerRepository customerRepository, ObjectMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (customerRepository.count() == 0) {

            ClassPathResource resource =
                    new ClassPathResource("customers.json");

            String content = new String(
                    resource.getInputStream().readAllBytes()
            );

            List<Customer> customers =
                    mapper.readValue(
                            content,
                            new TypeReference<List<Customer>>() {}
                    );

            customerRepository.saveAll(customers);
    }
        }
}
