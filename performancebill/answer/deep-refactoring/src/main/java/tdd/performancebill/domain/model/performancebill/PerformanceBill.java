package tdd.performancebill.domain.model.performancebill;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "performance_bill")
public class PerformanceBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer")
    private String customer;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "volume_credits")
    private int volumeCredits;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "performance_bill_id")
    private List<PerformanceBillItem> items = new ArrayList<>();

    public PerformanceBill(String customer) {
        this.customer = customer;
    }

    public PerformanceBill() {
    }

    public PerformanceBill setCustomer(String customer) {
        this.customer = customer;
        return this;
    }

    @Deprecated
    public PerformanceBill addItem(PerformanceBillItem item) {
        this.items.add(item);
        return this;
    }

    public PerformanceBill addItem(String name, int amount, int audience) {
        this.items.add(new PerformanceBillItem(name, amount, audience));
        return this;
    }

    public String getCustomer() {
        return customer;
    }

    public List<PerformanceBillItem> getItems() {
        return items;
    }

    public PerformanceBill setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public PerformanceBill setVolumeCredits(int volumeCredits) {
        this.volumeCredits = volumeCredits;
        return this;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }
}
