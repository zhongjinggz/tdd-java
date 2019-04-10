package tdd.playstatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PlayStatementController {

    Map<String, Play> plays = new HashMap<>();

    @Autowired
    PlayStatementRepository repository;

    @PostMapping("/api/playstatement")
    public PlayStatement createStatement(@RequestBody PerformanceSummary performanceSummary) {
        //初始化戏剧列表
        plays.put("hamlet", new Play("hamelet", "Hamlet", "tragedy"));
        plays.put("as-like", new Play("as-like", "As You Like It", "comedy"));
        plays.put("othello", new Play("othello", "Othello", "tragedy"));

        int totalAmount = 0;
        int volumeCredits = 0;

        PlayStatement statement = new PlayStatement(
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
                thisAmount = 30000;
                if (perf.getAudience() > 20) {
                    thisAmount += 10000 + 500 * (perf.getAudience() - 20);
                }
                thisAmount += 300 * perf.getAudience();
            } else {
                throw new IllegalArgumentException("戏剧类型不正确!");
            }

            //计算观众量积分
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floorDiv(perf.getAudience(), 5);
            }

            totalAmount += thisAmount;

            statement.addItem(play.getName(),thisAmount, perf.getAudience());


        }

        statement.setTotalAmount(totalAmount);
        statement.setVolumeCredits(volumeCredits);

        repository.save(statement);

        return statement;


    }

}
