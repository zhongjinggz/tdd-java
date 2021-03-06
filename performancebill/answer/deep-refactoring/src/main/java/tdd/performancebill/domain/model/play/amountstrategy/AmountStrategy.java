package tdd.performancebill.domain.model.play.amountstrategy;

public abstract class AmountStrategy {
    public static final AmountStrategy TRADEGY = new TragedyAmountStrategy();
    public static final AmountStrategy COMEDY = new ComedyAmountStrategy();

    public abstract int calculate(int audience);
}
