package tdd.cashregister;

public class CashRegister {
    private final Printer printer;

    public CashRegister(Printer printer) {
        this.printer = printer;
    }

    public void process(Purchase purchase) {
        printer.print(purchase.description());
    }

}
