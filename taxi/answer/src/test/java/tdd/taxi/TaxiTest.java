package tdd.taxi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//Done 不大于2公里时只收起步价6元
//Done 超过2公里时每公里0.8元
//Done 超过8公里则每公里加收50%长途费
//Done 停车等待时加收每分钟0.25元
//Done 距离必须大于0
//Done 等待时间不能小于0
class TaxiTest {

    private static final double DELTA = 0.000001;
    private Taxi taxi;


    @BeforeEach
    void setUp() {
        taxi = new Taxi();
    }

    //JUnit5的参数化测试方法
    //有多个参数时,使用@CsvSource
    @ParameterizedTest
    @CsvSource({
            "2,  0,  6",
            "1,  0,  6"
    })
    void calculate不大于2公里时只收起步价6元(final double distance,
                                 final int waitMinutes,
                                 final double expected) {
        verifyCalculate(distance, waitMinutes, expected);
    }

    @ParameterizedTest
    @CsvSource({
            "3,  0,   6.8",
            "8,  0,  10.8"
    })
    void calculate超过2公里时每公里0点8元(final double distance,
                                final int waitMinutes,
                                final double expected) {
        verifyCalculate(distance, waitMinutes, expected);
    }


    @ParameterizedTest
    @CsvSource({
            "9,  0,  12.0",
            "10, 0,  13.2"
    })
    void calculate超过8公里则每公里加收百分之50长途费(final double distance,
                                      final int waitMinutes,
                                      final double expected) {
        verifyCalculate(distance, waitMinutes, expected);

    }

    @ParameterizedTest
    @CsvSource({
            "2,  1,  6.25",
            "8,  2,  11.3"
    })
    void calculate停车等待时加收每分钟0点25元(final double distance,
                                  final int waitMinutes,
                                  final double expected) {
        verifyCalculate(distance, waitMinutes, expected);
    }


    // 注意抽出公共测试代码
    private void verifyCalculate(final double distance,
                                 final int waitMinutes,
                                 final double expected) {
        // When
        Double fee = taxi.calculate(distance, waitMinutes);

        // Then
        assertEquals(expected, fee, DELTA);
    }

    //只有一个参数时,可使用@ValueSource
    @ParameterizedTest
    @ValueSource(ints = {-1, -2})
    void calculate等待时间不能小于0(final int waitMinutes) {

        double distance = 1.0;
        String expectedMsg = Taxi.MSG_WAITTIME_SHOULD_NOT_LT_0;

        verifyCalculateException(waitMinutes, distance, expectedMsg);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0})
    void calculate距离必须大于0(final double distance) {

        int waitMinutes = 0;
        String expectedMsg = Taxi.MSG_DISTANCE_SHOULD_GT_0;

        verifyCalculateException(waitMinutes, distance, expectedMsg);
    }

    private void verifyCalculateException(
            int waitMinutes
            , double distance
            , String expectedMsg) {

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> taxi.calculate(distance, waitMinutes));

        assertEquals(expectedMsg, e.getMessage());
    }
}
