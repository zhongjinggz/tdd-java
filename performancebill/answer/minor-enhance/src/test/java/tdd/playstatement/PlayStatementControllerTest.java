package tdd.playstatement;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class PlayStatementControllerTest {

    @ParameterizedTest
    @CsvSource(value = {
            "10, 33000",
            "1, 30300"
    })

    void calculateComedyAmount不大于10人时不应额外收费(int audience, int expected) {
        verifyCalculateComedyAmount(audience, expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "11, 38800",
            "20, 46000"
    })
    void calculateComedyAmount大于10人时应收取额外收费(int audience, int expected) {
        verifyCalculateComedyAmount(audience, expected);
    }

    private void verifyCalculateComedyAmount(int audience, int expected) {
        PlayStatementController controller = new PlayStatementController();
        assertThat(controller.calculateComedyAmount(audience)).isEqualTo(expected);
    }
}