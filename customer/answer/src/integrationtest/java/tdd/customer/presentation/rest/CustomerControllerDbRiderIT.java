package tdd.customer.presentation.rest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import tdd.customer.domain.model.Customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static tdd.customer.share.testhelper.Utils.asJsonString;
import static tdd.customer.share.testhelper.Utils.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DBRider
class CustomerControllerDbRiderIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "customer/expectedCustomers.yml", ignoreCols = "id")
    void create应创建客户() throws Exception {

        Customer expected = new Customer("羽", "关");

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
    }

    @Test
    @DataSet(value = "customer/customers.yml")
    void findById应查出指定客户() throws Exception {

        String actual = mockMvc
                .perform(get("/api/customers/1"))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        String expected = asJsonString(
                new Customer(1L, "羽", "关"));

        assertJson(actual, expected);
    }

    @Test
    @DataSet("customer/customers.yml")
    void findAll应查出所有客户() throws Exception {


        String actual = mockMvc
                .perform(get("/api/customers"))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expected = asJsonString(
                new Customer[]{
                        new Customer(1L, "羽", "关"),
                        new Customer(2L, "备", "刘")
                }
        );

        assertJson(actual, expected);
    }
}