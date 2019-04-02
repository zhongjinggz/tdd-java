package tdd.cashregister;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PurchaseTest {

    @Test
    void description能够描述购买的产品() {
        //Given
        Purchase purchase = new Purchase(new Item[] {
                new Item("Apple", 10.0),
                new Item("Banana", 20.0)
        });

        //When
        String des = purchase.description();

        //Then
        String expected = "Apple\t10.0\nBanana\t20.0\n";
        assertThat(des).isEqualTo(expected);

    }

}