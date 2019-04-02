package tdd.cashregister;

public class CashRegisterLegacy {

    public void process(Purchase purchase) {

        //Printer printer = new Printer();
        Printer printer = buildPrinter();

        int discount = calculateDiscount(purchase);
        printer.print(purchase.description() + "\ndiscount: " + discount);
    }


    Printer buildPrinter() {
        return new Printer();
    }

    int calculateDiscount(Purchase purchase) {
        throw new UnsupportedOperationException("Not implemented!");
    }
}
