package tdd.customer.share.testhelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tdd.customer.domain.model.Customer;
import tdd.customer.domain.model.CustomerRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.fail;

@Component
public class RepoHelper {

    @Autowired
    CustomerRepository repository;

    public Customer createCustomerInDb(String firstName, String lastName) {
        Customer expected = new Customer(firstName, lastName);

        expected.setId(
                repository.save(expected).getId()
        );
        return expected;
    }

    public Customer findCustomerFromDbById(Long id) {
        return repository.findById(id)
                .orElseGet(()-> fail("找不到ID为 '" + id + "' 的客户!"));
    }

    public void clearCustomerTable() {
        repository.deleteAll();
    }
}
