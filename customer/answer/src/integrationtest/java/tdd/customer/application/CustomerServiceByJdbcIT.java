package tdd.customer.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tdd.customer.domain.model.Customer;
import tdd.customer.share.testhelper.CustomerJdbcMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerServiceByJdbcIT {
    @Autowired
    CustomerService service;

    @Autowired
    JdbcTemplate jdbc;

    @Test
    void create应创建客户() {
        //Given
        clearCustomerTable();

        //When
        Customer stubCust = new Customer("羽", "关");
        service.create(stubCust);

        //Then
        List<Customer> customers = findAllCustomersFromDb();

        assertThat(customers).hasSize(1)
                .extracting("firstName", "lastName")
                .contains(tuple(stubCust.getFirstName(), stubCust.getLastName()));
    }

    private List<Customer> findAllCustomersFromDb() {
        return jdbc.query("select * from customer", new CustomerJdbcMapper());

    }

    private void clearCustomerTable() {
        jdbc.update("truncate table customer");
    }

}