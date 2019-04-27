package tdd.customer.share.testhelper;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class Utils {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void assertJson(String actual, String expected) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            assertThat(mapper.readTree(actual))
                    .isEqualTo(mapper.readTree(expected));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
