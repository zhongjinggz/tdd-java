package tdd.customer.application;

import tdd.customer.domain.model.Customer;
import tdd.customer.domain.model.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

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
        clearCustomerTable();

        //When
        Customer aCustomer = new Customer("羽", "关");

        Long customerId = service
                .create(aCustomer)
                .getId();

        //Then
        Optional<Customer> foundCustomer = service.findById(customerId);

        assertThat(foundCustomer.isPresent()).isTrue();
        assertThat(foundCustomer.get()).isEqualToComparingOnlyGivenFields(
                aCustomer , "firstName", "lastName"
        );
    }

    @Test
    void findAll应该查出所有客户() {
        clearCustomerTable();

    }

    private void clearCustomerTable() {
        repository.deleteAll();
    }

}