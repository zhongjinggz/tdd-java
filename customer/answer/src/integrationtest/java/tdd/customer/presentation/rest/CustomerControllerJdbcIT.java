package tdd.customer.presentation.rest;

import tdd.customer.domain.model.Customer;
import tdd.customer.share.testhelper.JdbcHelper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.assertj.core.api.Assertions.*;

import static tdd.customer.share.testhelper.Utils.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerJdbcIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JdbcHelper helper;

    @BeforeEach
    void initDb() {
        helper.clearCustomerTable();
    }

    @Test
    void create应创建客户() throws Exception {

        Customer expected = new Customer("云", "赵");

        Long createdId = Long.valueOf(
                mockMvc.perform(
                        post("/api/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(expected)))
                        .andDo(print())
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
        );

        Customer actual = helper.findCustomerFromDbById(createdId);

        assertThat(actual).isEqualToComparingOnlyGivenFields(
                expected
                , "firstName"
                , "lastName");
    }

    @Test
    void findById应查出指定客户() throws Exception {
        String expected = asJsonString(
                helper.createCustomerInDb(
                        1L, "羽", "关")
        );

        String actual = mockMvc
                .perform(get("/api/customers/1"))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertJson(actual, expected);
    }

    @Test
    void findAll应查出所有客户() throws Exception {

        Customer zhangfei = helper.createCustomerInDb(1L, "飞", "张");
        Customer liubei = helper.createCustomerInDb(2L, "备", "刘");

        String expected =asJsonString(new Customer[]{zhangfei, liubei});

        String actual = mockMvc
                .perform(get("/api/customers"))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertJson(actual, expected);
    }
}