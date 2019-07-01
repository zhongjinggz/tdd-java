package tdd.taxi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

//Done 不大于2公里时只收起步价6元
//Done 超过2公里时每公里0.8元
//Todo 超过8公里则每公里加收50%长途费
//Todo 停车等待时加收每分钟0.25元
//Todo 最后计价的时候司机(Driver)会四舍五入只收(Charge)到元 。

class TaxiTest {
    @ParameterizedTest
    @CsvSource({"1"
        ,"2"})
    void calculate不大于2公里时_只收起步价6元(int distance){
        //given
        calculate超过2公里时_每公里8角(distance, Taxi.INITIAL_FEE);

    }

    @ParameterizedTest
    @CsvSource({"3, 6.8"
            , "4, 7.6"})
    void calculate超过2公里时_每公里8角(int distance, double expected){
        validateCalculate(distance, expected);

    }

    private void validateCalculate(int distance, double expected) {
        //given
        Taxi taxi = new Taxi();

        //when
        double fee = taxi.calculate(distance);
        //then
        assertEquals(expected, fee);
    }
}