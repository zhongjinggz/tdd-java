package tdd.cashregister;

public class CashRegister {

    private final Printer printer;

    public CashRegister(Printer printer) {
        this.printer = printer;
    }

    public void process(Purchase purchase) {
        int discount = calculateDiscount(purchase);
        printer.print(purchase.description()
                + "\ndiscount: " + discount);
    }

    int calculateDiscount(Purchase purchase) {
        throw new UnsupportedOperationException(
                "Not implemented!");
    }
}



