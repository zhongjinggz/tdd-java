package tdd.cashregister;

import org.junit.jupiter.api.Test;

//Done 应打印小票信息
//Done 应计算折扣
class CashRegisterManuallyTest {


//    @Test
//    void process应打印小票信息() {
//        //Given
//        MockPrinter mockPrinter = new MockPrinter();
//        Purchase purchase = new Purchase(new Item[]{
//                new Item("Apple", 10.0),
//                new Item("Banana", 20.0)
//        });
//        CashRegister cashRegister = new CashRegister(mockPrinter);
//
//        //When
//        cashRegister.process(purchase);
//
//        //Then
//        mockPrinter.verify(purchase.description());
//
//    }

    @Test
    void process应打印小票信息() {
        //Given
        MockPrinter mockPrinter = new MockPrinter();

        final String DESCRIPTION = "Purchase description";
        Purchase stubPurchase = new StubPurchase(DESCRIPTION);

        CashRegister spyCashRegister = new CashRegister(mockPrinter) {
            @Override
            int calculateDiscount(Purchase purchase) {
                return 10;
            }
        };

        //When
        spyCashRegister.process(stubPurchase);

        //Then
        mockPrinter.verify(DESCRIPTION + "\ndiscount: " + 10);
    }
}