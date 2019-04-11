package tdd.playstatement.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdd.playstatement.application.PlayStatementService;
import tdd.playstatement.domain.model.performancesummary.PerformanceSummary;
import tdd.playstatement.domain.model.playstatement.PlayStatement;

@RestController
public class PlayStatementController {

    @Autowired
    private PlayStatementService service;

    @PostMapping("/api/playstatement")
    public PlayStatement createStatement(@RequestBody PerformanceSummary performanceSummary) {
        return service.createPlayStatement(performanceSummary);
    }

}
