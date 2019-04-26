package tdd.customer.presentation.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import tdd.customer.domain.model.Customer;
import tdd.customer.share.testhelper.CustomerJdbcMapper;


import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerJdbcIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void create应创建客户() throws Exception {
        //Given
        clearCustomerTable();

        //When
        Customer stubCust = new Customer("云", "赵");
        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(stubCust)))
                .andDo(print());
        //Then
        List<Customer> customers = findAllCustomersWithJdbc();

        assertThat(customers)
                .extracting("firstName", "lastName")
                .containsOnly(tuple(stubCust.getFirstName(), stubCust.getLastName()));
    }

    private List<Customer> findAllCustomersWithJdbc() {
        return jdbc.query("select * from customer", new CustomerJdbcMapper());
    }

    private void clearCustomerTable() {
        jdbc.update("truncate table customer");
    }

    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}