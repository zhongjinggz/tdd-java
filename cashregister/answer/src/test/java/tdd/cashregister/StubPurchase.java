package tdd.cashregister;

public class StubPurchase extends Purchase {
    private final String description;

    public StubPurchase(String description) {
        this.description = description;
    }

    @Override
    public String description() {
       return description;
    }
}
