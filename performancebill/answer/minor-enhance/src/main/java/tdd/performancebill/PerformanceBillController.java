package tdd.performancebill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PerformanceBillController {

    Map<String, Play> plays = new HashMap<>();

    @Autowired
    PerformanceBillRepository repository;

    @PostMapping("/api/performancebill")
    public PerformanceBill createBill(@RequestBody PerformanceSummary performanceSummary) {
        //初始化戏剧列表
        plays.put("hamlet", new Play("hamelet", "Hamlet", "tragedy"));
        plays.put("as-like", new Play("as-like", "As You Like It", "comedy"));
        plays.put("othello", new Play("othello", "Othello", "tragedy"));

        int totalAmount = 0;
        int volumeCredits = 0;

        PerformanceBill bill = new PerformanceBill(
                performanceSummary.getCustomer());


        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = plays.get(perf.getPlayId());
            int thisAmount;

            if (play.getType().equals("tragedy")) {
                thisAmount = 40000;
                if (perf.getAudience() > 30) {
                    thisAmount += 1000 * (perf.getAudience() - 30);
                }
            } else if (play.getType().equals("comedy")) {
                thisAmount = calculateComedyAmount(perf.getAudience());
            } else {
                throw new IllegalArgumentException("戏剧类型不正确!");
            }

            //计算观众量积分
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floorDiv(perf.getAudience(), 5);
            }

            totalAmount += thisAmount;

            bill.addItem(play.getName(),thisAmount, perf.getAudience());


        }

        bill.setTotalAmount(totalAmount);
        bill.setVolumeCredits(volumeCredits);

        repository.save(bill);

        return bill;

    }

    int calculateComedyAmount(int audience) {
        final int BASE_PRICE = 30000;
        final int UNIT_PRICE = 300;

        final int THRESHOLD = 10;
        final int ADDITIONAL_BASE_PRICE = 10000;
        final int ADDITIONAL_UNIT_PRICE = 500;

        int amount = BASE_PRICE + UNIT_PRICE * audience;

        if (audience > THRESHOLD) {
            amount += ADDITIONAL_BASE_PRICE
                    + ADDITIONAL_UNIT_PRICE * (audience - THRESHOLD);
        }

        return amount;
    }
}
