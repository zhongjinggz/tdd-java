package tdd.performancebill;

import java.util.ArrayList;
import java.util.List;

public class PerformanceSummary {
    private String customer;
    List<Performance> performances = new ArrayList<>();

    public PerformanceSummary() {}

    public PerformanceSummary(String customer) {
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void addPerformance(String playId, int audience) {
        Performance p = new Performance(playId, audience);
        performances.add(p);
    }
}
