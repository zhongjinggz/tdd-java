package tdd.cashregister;

import static org.assertj.core.api.Assertions.*;

public class MockPrinter extends Printer {
    private String message;

    @Override
    public  void print(String message) {
        this.message = message;
    }
    public void verify(String description) {
        assertThat(description).as("print() 方法应以正确的参数被调用!").isEqualTo(this.message);
    }
}
