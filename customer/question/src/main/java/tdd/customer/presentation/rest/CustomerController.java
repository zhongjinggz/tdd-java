package tdd.customer.presentation.rest;

import tdd.customer.application.CustomerService;
import tdd.customer.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdd.customer.share.exceptions.CustomerNotFoundException;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService service;

    @PostMapping("/api/customers")
    public Long create(@RequestBody Customer customer) {
        return service.create(customer).getId();
    }

    @GetMapping("/api/customers/{id}")
    public Customer findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @GetMapping("/api/customers")
    public List<Customer> findAll() {
        return service.findAll();
    }

}
