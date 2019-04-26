package tdd.performancebill;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.github.database.rider.spring.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;
import tdd.performancebill.domain.model.performancesummary.PerformanceSummary;
import tdd.performancebill.domain.model.performancebill.PerformanceBill;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DBRider
class PerformanceBillControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DataSet(cleanBefore = true)
    @ExpectedDataSet(value = "expectedPerformanceBill.yml", ignoreCols = {"id", "play_statement_id"})
    void createBill_2场表演() throws Exception {
        //Given
        String requestBody = buildRequestBody();

        //When
        String actual = mockMvc
                .perform(post("/api/performancebill")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        //Then
        String expected = buildExpectedRespond();

        assertJson(expected, actual);
    }

    private void assertJson(String expected, String actual) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode expectedTree;
        JsonNode actualTree;
        try {
            expectedTree = mapper.readTree(expected);
            actualTree = mapper.readTree(actual);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThat(actualTree).isEqualTo(expectedTree);

    }


//    {
//        "customer": "AAA",
//        "items": [
//            {
//                "name": "As You Like It",
//                "amount": 70000,
//                "audience": 50
//            }, {
//                "name": "Hamlet",
//                "amount": 40000,
//                "audience": 10
//            }
//        ],
//        "totalAmount": 110000,
//        "volumeCredits": 30
//    }

    private String buildExpectedRespond() {
        PerformanceBill aStatement = new PerformanceBill()
                .setCustomer("AAA")
                .addItem("As You Like It", 70000, 50)
                .addItem("Hamlet", 40000, 10)
                .setTotalAmount(110000)
                .setVolumeCredits(30);

        return asJsonString(aStatement);
    }

    //    {
//        "customer": "AAA",
//        "performances": [
//            {
//              "audience": 50,
//              "playId": "as-like"
//            }, {
//              "audience": 10,
//              "playId": "hamlet"
//            }
//          ]
//    }
    private String buildRequestBody() {
        PerformanceSummary aSummary = new PerformanceSummary()
                .setCustomer("AAA")
                .addPerformance("as-like", 50)
                .addPerformance("hamlet", 10);

        return asJsonString(aSummary);
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}