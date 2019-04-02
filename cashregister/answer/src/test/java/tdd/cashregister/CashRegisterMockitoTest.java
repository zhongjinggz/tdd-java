package tdd.cashregister;

//import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

//Done 应打印小票信息
//Done 应计算折扣
//@Disabled
class CashRegisterMockitoTest {
    @Test
    void process应打印小票信息() {
        //Given
        Printer mockPrinter = mock(Printer.class);

        final String DESCRIPTION = "Purchase description";
        Purchase stubPurchase = mock(Purchase.class);
        doReturn(DESCRIPTION).when(stubPurchase).description();

        CashRegister spyCashRegister = spy(new CashRegister(mockPrinter));
        doReturn(10).when(spyCashRegister).calculateDiscount(stubPurchase);

        //When
        spyCashRegister.process(stubPurchase);

        //Then
        verify(mockPrinter).print(DESCRIPTION + "\ndiscount: " + 10);
        verify(spyCashRegister).calculateDiscount(stubPurchase);
    }
}