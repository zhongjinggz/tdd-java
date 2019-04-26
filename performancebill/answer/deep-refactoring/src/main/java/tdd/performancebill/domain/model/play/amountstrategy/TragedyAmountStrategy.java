package tdd.performancebill.domain.model.play.amountstrategy;

public class TragedyAmountStrategy extends AmountStrategy {

    private static final int BASE_PRICE = 40000;
    private static final int ADDITIONAL_UNIT_PRICE = 1000;

    @Override
    public int calculate(int audience) {
        return BASE_PRICE + ADDITIONAL_UNIT_PRICE * Math.max((audience - 30), 0);
    }
}
