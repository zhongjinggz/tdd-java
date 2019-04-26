package tdd.performancebill.domain.model.performancesummary;

import java.util.ArrayList;
import java.util.List;

public class PerformanceSummary {
    private String customer;
    List<Performance> performances = new ArrayList<>();

    public PerformanceSummary() {}

    public PerformanceSummary(String customer) {
        this.customer = customer;
    }

    public PerformanceSummary setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    public String getCustomer() {
        return customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public PerformanceSummary addPerformance(String playId, int audience) {
        Performance p = new Performance(playId, audience);
        performances.add(p);
        return this;
    }
}
