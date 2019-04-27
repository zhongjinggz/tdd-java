package tdd.taxi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//本文件中的示例使用 JUnit4 的风格, 供使用 JUnit4 的团队参考
//实践中使用 Junit4 时,应 import JUnit4 的包

//Done 不大于2公里时只收起步价6元
//Done 超过2公里时每公里0.8元
//Done 超过8公里则每公里加收50%长途费
//Done 停车等待时加收每分钟0.25元
//Done 距离必须大于0
//Done 等待时间不能小于0
class TaxiJunit4Test {

    private static final double DELTA = 0.000001;
    private Taxi taxi;


    //use @Before instead for JUnit4
    @BeforeEach
    void setUp() {
        taxi = new Taxi();
    }

    @Test
    void calculate_不大于2公里时_只收起步价6元_例如2公里() {
        verifyCalculate(2, 0, 6);
    }

    @Test
    void calculate_不大于2公里时_只收起步价6元_例如1公里() {
        verifyCalculate(1, 0, 6);
    }

    @Test
    void calculate_超过2公里时_每公里0点8元_例如3公里() {
        verifyCalculate(3, 0, 6.8);
    }

    @Test
    void calculate_超过2公里时_每公里0点8元_例如8公里() {
        verifyCalculate(8, 0, 10.8);
    }

    @Test
    void calculate_超过8公里_则每公里加收百分之50长途费_例如9公里() {
        verifyCalculate(9, 0, 12.0);
    }

    @Test
    void calculate_超过8公里_则每公里加收百分之50长途费_例如10公里() {
        verifyCalculate(10, 0, 13.2);
    }

    @Test
    void calculate_停车等待时_加收每分钟0点25元_例如1分钟() {
        verifyCalculate(2, 1, 6.25);
    }

    @Test
    void calculate_停车等待时_加收每分钟0点25元_例如2分钟() {
        verifyCalculate(8, 2, 11.3);
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


    @Test
    void calculate_距离必须大于0_例如不应为负1公里() {
        verifyDistanceException(-1.0);
    }

    @Test
    void calculate_距离必须大于0_例如不应为0公里() {
        verifyDistanceException(0.0);
    }

    private void verifyDistanceException(final double distance) {
        try {
            taxi.calculate(distance, 0);
            fail("距离不大于0时应抛出异常");
        } catch (IllegalArgumentException e) {
            assertEquals(Taxi.MSG_DISTANCE_SHOULD_GT_0, e.getMessage());

        }
    }

    @Test
    void calculate_等待时间不能小于0_例如不应为负1分钟() {
        verifyWaitingTimeException(-1);
    }

    @Test
    void calculate_等待时间不能小于0_例如不应为负2分钟() {
        verifyWaitingTimeException(-2);
    }
    private void verifyWaitingTimeException(int waitMinutes) {
        try {
            taxi.calculate(1, waitMinutes);
            fail("等待时间为负时应抛出异常");
        } catch (IllegalArgumentException e) {
            assertEquals(Taxi.MSG_WAITTIME_SHOULD_NOT_LT_0, e.getMessage());

        }
    }
}
