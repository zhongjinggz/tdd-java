package tdd.performancebill.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdd.performancebill.application.PerformanceBillService;
import tdd.performancebill.domain.model.performancesummary.PerformanceSummary;
import tdd.performancebill.domain.model.performancebill.PerformanceBill;

@RestController
public class PerformanceBillController {

    @Autowired
    private PerformanceBillService service;

    @PostMapping("/api/performancebill")
    public PerformanceBill createBill(@RequestBody PerformanceSummary performanceSummary) {
        return service.createBill(performanceSummary);
    }

}
