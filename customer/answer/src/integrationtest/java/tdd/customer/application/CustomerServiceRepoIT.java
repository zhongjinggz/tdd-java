package tdd.customer.application;

import org.junit.jupiter.api.BeforeEach;
import tdd.customer.domain.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tdd.customer.share.testhelper.RepoHelper;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 本类中的测试方法借助Repository中的
 * 数据访问方法进行集成测试,比较方便但不够严谨,
 * 适合于冒烟测试.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerServiceRepoIT {

    @Autowired
    CustomerService service;

    @Autowired
    RepoHelper helper;

    @BeforeEach
    void initDb() {
        helper.clearCustomerTable();
    }

    @Test
    void create应创建指定客户() {

        Customer expected = new Customer("羽", "关");

        expected.setId(
                service.create(expected).getId()
        );

        Customer actual = helper.findCustomerFromDbById(expected.getId());

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    void findById应查出指定客户() {
        Customer expected = helper.createCustomerInDb("羽", "关");
        Customer actual = service
                .findById(expected.getId())
                .orElseGet(() ->
                        fail("找不到id为 '" + expected.getId() + "' 的客户")
                );

        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    @Test
    void findAll应查出所有客户() {

        Customer zhangfei = helper.createCustomerInDb("飞", "张");
        Customer liubei = helper.createCustomerInDb("备", "刘");

        List<Customer> actual = service.findAll();

        assertThat(actual).hasSize(2)
                .extracting("firstName", "lastName")
                .contains(
                        tuple(zhangfei.getFirstName(), zhangfei.getLastName()),
                        tuple(liubei.getFirstName(), liubei.getLastName())
                );
    }
}