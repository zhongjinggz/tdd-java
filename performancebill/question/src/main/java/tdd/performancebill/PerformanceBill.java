package tdd.performancebill;

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
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Deprecated
    public void addItem(PerformanceBillItem item) {
        this.items.add(item);
    }

    public void addItem(String name, int amount, int audience) {
        this.items.add(new PerformanceBillItem(name, amount, audience));
    }

    public String getCustomer() {
        return customer;
    }

    public List<PerformanceBillItem> getItems() {
        return items;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setVolumeCredits(int volumeCredits) {
        this.volumeCredits = volumeCredits;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }
}
