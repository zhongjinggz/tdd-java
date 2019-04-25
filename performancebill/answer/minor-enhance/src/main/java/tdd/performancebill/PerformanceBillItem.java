package tdd.performancebill;

import javax.persistence.*;

@Entity
@Table(name = "performance_bill_item")
public class PerformanceBillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private int amount;

    @Column(name = "audience")
    private int audience;

    public PerformanceBillItem(String name, int amount, int audience) {
        this.name = name;
        this.amount = amount;
        this.audience = audience;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getAudience() {
        return audience;
    }
}
