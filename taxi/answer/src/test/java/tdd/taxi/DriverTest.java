package tdd.taxi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tdd.taxi.Driver;
import tdd.taxi.Taxi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

//Done 司机会四舍五入只收到元
class DriverTest {

    private static final double DELTA = 0.000001;

    @ParameterizedTest
    @CsvSource({
            "3,  0,   7.0",
            "9,  0,  12.0",
            "10, 0,  13.0"
    })
    void charge司机会四舍五入只收到元(final double distance,
                           final int waitMinutes,
                           final double expected) {
        //Given
        Taxi taxi = new Taxi();
        Driver driver = new Driver(taxi);

        //When
        double fee = driver.charge(distance, waitMinutes);

        //Then
        assertThat(fee).isEqualTo(expected, offset(DELTA));
    }

}