package tdd.playstatement.domain.model.playstatement;

import javax.persistence.*;

@Entity
@Table(name = "play_statement_item")
public class PlayStatementItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "amount")
    private int amount;

    @Column(name = "audience")
    private int audience;

    public PlayStatementItem(String name, int amount, int audience) {
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
