package tdd.customer.share.testhelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tdd.customer.domain.model.Customer;

@Component
public class JdbcHelper {

    @Autowired
    JdbcTemplate jdbc;

    public void clearCustomerTable() {
        jdbc.update("truncate table customer");
    }

    public Customer findCustomerFromDbById(Long id) {
        return jdbc.queryForObject("select * from customer where id = ?"
                , new CustomerJdbcMapper()
                , id);
    }

    public Customer createCustomerInDb(Long id, String firstName, String lastName) {
        Customer expected = new Customer(id, firstName, lastName);
        jdbc.update("insert into customer (id, first_name, last_name) "
                        + "values(?, ?, ?)"
                , id
                , firstName
                , lastName
        );

        return expected;
    }
}

