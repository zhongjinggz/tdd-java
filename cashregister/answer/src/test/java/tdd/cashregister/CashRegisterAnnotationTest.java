package tdd.cashregister;

//import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

//Done 应打印小票信息
//Done 应计算折扣

@ExtendWith(MockitoExtension.class)
class CashRegisterAnnotationTest {

    @Mock private Printer mockPrinter;

    @Mock private Purchase stubPurchase;

    @InjectMocks
    @Spy private CashRegister spyCashRegister;

    @Test
    void process应打印小票信息() {
        //Given
        final String DESCRIPTION = "Purchase description";
        doReturn(DESCRIPTION).when(stubPurchase).description();

        doReturn(10).when(spyCashRegister).calculateDiscount(stubPurchase);

        //When
        spyCashRegister.process(stubPurchase);

        //Then
        verify(mockPrinter).print(DESCRIPTION + "\ndiscount: " + 10);
        verify(spyCashRegister).calculateDiscount(stubPurchase);
    }
}