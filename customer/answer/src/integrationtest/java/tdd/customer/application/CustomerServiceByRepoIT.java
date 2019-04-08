package tdd.customer.application;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import tdd.customer.domain.model.Customer;
import tdd.customer.infrastructure.persistence.CustomerRepository;
import tdd.customer.share.testhelper.CustomerJdbcMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.tuple;

/**
 * 本类中的测试方法借助Repository中的
 * 数据访问方法进行集成测试,比较方便但不够严谨,
 * 适合于冒烟测试.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerServiceByRepoIT {

    @Autowired
    CustomerService service;

    @Autowired
    CustomerRepository repository;

    @Test
    void create应创建客户() {
        //Given
        clearCustomerTableWithService();

        //When
        Customer stubCust = new Customer("羽", "关");
        service.create(stubCust);

        //Then
        List<Customer> customers = service.findAll();
        assertThat(customers).hasSize(1)
                .extracting("firstName", "lastName")
                .contains(tuple(stubCust.getFirstName(), stubCust.getLastName()));
    }

    private void clearCustomerTableWithService() {
        repository.deleteAll();
    }

}