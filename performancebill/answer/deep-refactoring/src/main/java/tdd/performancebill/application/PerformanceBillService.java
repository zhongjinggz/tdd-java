package tdd.performancebill.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tdd.performancebill.domain.model.play.Play;
import tdd.performancebill.domain.model.performancebill.PerformanceBillRepository;
import tdd.performancebill.domain.model.performancesummary.Performance;
import tdd.performancebill.domain.model.performancesummary.PerformanceSummary;
import tdd.performancebill.domain.model.performancebill.PerformanceBill;
import tdd.performancebill.domain.model.play.PlayRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class PerformanceBillService {
    @Autowired
    PerformanceBillRepository billRepository;

    @Autowired
    PlayRepository playRepository;

    @Transactional
    public PerformanceBill createBill(@RequestBody PerformanceSummary performanceSummary) {
        if (performanceSummary.getPerformances().size() == 0) {
            throw new IllegalArgumentException("没有演出!");
        }

        Map<String, Play> plays = playRepository.findPlays();

        PerformanceBill bill = new PerformanceBill(performanceSummary.getCustomer());

        int totalAmount = addBillItems(
                performanceSummary.getPerformances(), plays, bill);
        bill.setTotalAmount(totalAmount);

        int volumeCredits = calculateVolumeCredits(
                performanceSummary.getPerformances(), plays);
        bill.setVolumeCredits(volumeCredits);

        return billRepository.save(bill);
    }

    private int addBillItems(List<Performance> performances
            , Map<String, Play> plays, PerformanceBill bill) {

        int totalAmount = 0;

        for (Performance perf : performances) {
            Play play = plays.get(perf.getPlayId());

            int itemAmount = play.getAmountStrategy()
                    .calculate(perf.getAudience());

            bill.addItem(play.getName(), itemAmount, perf.getAudience());

            totalAmount += itemAmount;

        }
        return totalAmount;
    }

    private int calculateVolumeCredits(List<Performance> performance
            , Map<String, Play> plays) {

        int volumeCredits = 0;

        for (Performance perf : performance) {
            Play play = plays.get(perf.getPlayId());

            volumeCredits += play.getVolumeCreditsStrategy()
                    .calculate(perf.getAudience());
        }

        return volumeCredits;
    }
}
