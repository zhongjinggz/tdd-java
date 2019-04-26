package tdd.performancebill.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import static org.assertj.core.api.Assertions.*;

import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import tdd.performancebill.domain.model.performancebill.PerformanceBill;
import tdd.performancebill.domain.model.performancebill.PerformanceBillRepository;
import tdd.performancebill.domain.model.performancesummary.PerformanceSummary;
import tdd.performancebill.domain.model.play.Play;
import tdd.performancebill.domain.model.play.PlayRepository;
import tdd.performancebill.domain.model.play.amountstrategy.AmountStrategy;
import tdd.performancebill.domain.model.play.volumecreditsstrategy.VolumeCreditsStrategy;

import java.util.HashMap;
import java.util.Map;

// Done 1场表演_悲剧_不大于30人
// Done 1场表演_悲剧_大于30人
// Done 1场表演_喜剧_不大于20人
// Done 1场表演_喜剧_大于20人
// Done 没有表演

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PerformanceBillServiceTest {

    @Mock
    private PerformanceBillRepository mockBillRepository;

    @Mock
    PlayRepository mockPlayRepository;

    @InjectMocks
    PerformanceBillService service;

    @BeforeEach
    void setUp() {
        Map<String, Play> plays = new HashMap<>();
        plays.put("hamlet"
                , new Play("hamelet"
                        , "Hamlet"
                        , "tragedy"
                        , AmountStrategy.TRAGEDY
                        , VolumeCreditsStrategy.TRADEGY));

        plays.put("as-like"
                , new Play("as-like"
                        , "As You Like It"
                        , "comedy"
                        , AmountStrategy.COMEDY
                        , VolumeCreditsStrategy.COMEDY));
        plays.put("othello"
                , new Play("othello"
                        , "Othello"
                        , "tragedy"
                        , AmountStrategy.TRAGEDY
                        , VolumeCreditsStrategy.TRADEGY));

        doReturn(plays).when(mockPlayRepository).findPlays();
    }


    @Test
    void createBill_1场表演_悲剧_不大于30人() {

        verifyCreateBillForOnePerformance(
                "hamlet"
                , 10
                , 40000
                , 0
                , "Hamlet");
    }

    @Test
    void createBill_1场表演_悲剧_大于30人() {

        verifyCreateBillForOnePerformance(
                "othello"
                , 50
                , 60000
                , 20
                , "Othello");


    }

    @Test
    void createBill_1场表演_喜剧_不大于20人() {

        verifyCreateBillForOnePerformance(
                "as-like"
                , 10
                , 33000
                , 2
                , "As You Like It");


    }

    @Test
    void createBill_1场表演_喜剧_大于20人() {

        verifyCreateBillForOnePerformance(
                "as-like"
                , 30
                , 54000
                , 6
                , "As You Like It");


    }

    @Test
    void createBill_没有表演() {

        PerformanceSummary summary = new PerformanceSummary();

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> service.createBill(summary))
                .withMessage("没有演出!");
    }

    private void verifyCreateBillForOnePerformance(
            String playId
            , int audience
            , int expectedAmount
            , int expectedVolumeCredits
            , String expectedPlayName) {

        final String company = "AAA";

        PerformanceSummary summary = new PerformanceSummary()
                .setCustomer(company)
                .addPerformance(playId, audience);

        service.createBill(summary);

        ArgumentCaptor<PerformanceBill> argument = ArgumentCaptor.forClass(PerformanceBill.class);
        verify(mockBillRepository).save(argument.capture());
        PerformanceBill actual = argument.getValue();

//        //Approach 1
//        assertThat(actual.getCustomer()).isEqualTo("A Company");
//        assertThat(actual.getVolumeCredits()).isEqualTo(0);
//        assertThat(actual.getItems()).hasSize(1)
//                .extracting("name", "amount", "audience")
//                .contains(tuple("Hamlet", 40000, 20));

        // Approach 2
        PerformanceBill expected = new PerformanceBill(company)
                .setTotalAmount(expectedAmount)
                .setVolumeCredits(expectedVolumeCredits)
                .addItem(expectedPlayName, expectedAmount, audience);

        assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}