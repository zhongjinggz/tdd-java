package tdd.customer.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdd.customer.domain.model.Customer;
import tdd.customer.domain.model.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> findById(long id) {
        return repository.findById(id);
    }


    public List<Customer> findAll() {
        return repository.findAll();
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
