package tdd.customer.presentation.rest;

import tdd.customer.application.CustomerService;
import tdd.customer.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService service;

    @PostMapping("/api/customers")
    public void create(@RequestBody Customer customer) {
        service.create(customer);
    }

    @GetMapping("/api/customers")
    public List<Customer> findAll() {
        return service.findAll();
    }

}
