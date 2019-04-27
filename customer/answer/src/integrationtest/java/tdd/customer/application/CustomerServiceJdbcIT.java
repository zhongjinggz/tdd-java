package tdd.customer.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tdd.customer.domain.model.Customer;
import tdd.customer.share.testhelper.JdbcHelper;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerServiceJdbcIT {
    @Autowired
    CustomerService service;

    @Autowired
    JdbcHelper helper;

    @BeforeEach
    void initDb() {
        helper.clearCustomerTable();
    }

    @Test
    void create应创建客户() {
        //When
        Customer expected = new Customer("羽", "关");
        expected.setId(service.create(expected).getId());

        //Then
        Customer actual = helper.findCustomerFromDbById(expected.getId());

        assertThat(actual).isNotNull()
                .isEqualToComparingFieldByField(expected);
    }

    @Test
    void findById应查出指定客户() {

        Customer expected = helper.createCustomerInDb(1L, "羽", "关");

        Customer actual = service
                .findById(expected.getId())
                .orElseGet(() ->
                        fail("找不到id为 '" + expected.getId() + "' 的客户")
                );

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    void findAll应查出所有客户() {

        Customer zhangfei = helper.createCustomerInDb(1L, "飞", "张");
        Customer liubei = helper.createCustomerInDb(2L, "备", "刘");

        List<Customer> actual = service.findAll();

        assertThat(actual).hasSize(2)
                .extracting("firstName", "lastName")
                .contains(
                        tuple(zhangfei.getFirstName(), zhangfei.getLastName()),
                        tuple(liubei.getFirstName(), liubei.getLastName())
                );
    }
}