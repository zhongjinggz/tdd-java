package tdd.numbercounter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tdd.numbercounter.NumberCounter;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class NumberCounterTest {

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10', 5",
            "'28,25,22,19,16,13,11', 3"
    })
    public void count能数出偶数的个数(String numbers, int expected) {

        int count = new NumberCounter(parseNumbers(numbers)).count(NumberCounter.EVEN);
        assertThat(count).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5,6,7,8,9,10', 5",
            "'28,25,22,19,16,13,11', 4"
    })
    public void count能数出奇数的个数(String numbers, int expected) {
        int count = new NumberCounter(parseNumbers(numbers)).count(NumberCounter.ODD);
        assertThat(count).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "'-5,-4,-3,-2,-1,0,1,2,3,4,5', 5",
            "'1,-2,2,-2,3,-3,100,-100', 4"
    })
    public void count能数出正数的个数(String numbers, int expected) {
        int count = new NumberCounter(parseNumbers(numbers)).count(NumberCounter.POSITIVE);
        assertThat(count).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "'-5,-4,-3,-2,-1,0,1,2,3,4,5', 5",
            "'1,-2,2,-2,3,-3,100,-100', 4"
    })
    public void count能数出负数的个数(String numbers, int expected) {
        int count = new NumberCounter(parseNumbers(numbers)).count(NumberCounter.NEGATIVE);
        assertThat(count).isEqualTo(expected);
    }

    private int[] parseNumbers(String numbers) {
        return Arrays.stream(numbers.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
