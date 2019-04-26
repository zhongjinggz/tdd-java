package tdd.performancebill.domain.model.play.amountstrategy;

public class ComedyAmountStrategy extends AmountStrategy {

    private static final int BASE_PRICE = 30000;
    private static final int UNIT_PRICE = 300;
    private static final int THRESHOLD = 20;
    private static final int ADDITIONAL_BASE_PRICE = 10000;
    private static final int ADDITIONAL_UNIT_PRICE = 500;

    @Override
    public int calculate(int audience) {

        int thisAmount= BASE_PRICE + UNIT_PRICE * audience;

        if (audience > THRESHOLD) {
            thisAmount += ADDITIONAL_BASE_PRICE
                    + ADDITIONAL_UNIT_PRICE * (audience - THRESHOLD);
        }
        return thisAmount;
    }
}
