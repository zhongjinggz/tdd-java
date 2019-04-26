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
import java.util.Map;

@Service
public class PerformanceBillService {
    @Autowired
    PerformanceBillRepository billRepository;

    @Autowired
    PlayRepository playRepository;

    @Transactional
    public PerformanceBill createBill(@RequestBody PerformanceSummary summary) {
        verifyParameter(summary);

        Map<String, Play> plays = playRepository.findPlays();

        PerformanceBill bill = new PerformanceBill(summary.getCustomer());
        for (Performance perf : summary.getPerformances()) {
            Play play = plays.get(perf.getPlayId());
            bill.addItem(play, perf.getAudience());
        }

        return billRepository.save(bill);
    }

    private void verifyParameter(@RequestBody PerformanceSummary performanceSummary) {
        if (performanceSummary.getPerformances().size() == 0) {
            throw new IllegalArgumentException("没有演出!");
        }
    }

}
