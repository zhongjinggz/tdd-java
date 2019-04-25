package tdd.customer.application;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.github.database.rider.spring.api.DBRider;

import static org.assertj.core.api.Assertions.*;

import tdd.customer.domain.model.Customer;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DBRider
class CustomerServiceByDbUnitIT {
    @Autowired
    CustomerService service;

    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "customer/expectedCustomers.yml", ignoreCols = "id")
    void create应创建客户() {

        //When
        Customer stubCust = new Customer("羽", "关");
        service.create(stubCust);

    }

    @Test
    @DataSet("customer/customers.yml")
    void findAll应查询到所有客户() {

        List<Customer> customers = service.findAll();
        assertThat(customers)
                .extracting("firstName", "lastName")
                .containsOnly(tuple("备", "刘")
                        , tuple("羽", "关")
                );
    }

}