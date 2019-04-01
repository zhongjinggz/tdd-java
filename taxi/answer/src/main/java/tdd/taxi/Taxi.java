package tdd.taxi;

public class Taxi {

    private static final double INIT_PRICE = 6.0;
    private static final double UNIT_PRICE = 0.8;
    private static final double FAR_RATE = 0.5;
    private static final int INIT_DISTANCE = 2;
    private static final int FAR_DISTANCE = 8;
    private static final double MINUTE_PRICE = 0.25;
    static final String MSG_DISTANCE_SHOULD_GT_0 = "距离必须大于0!";
    static final String MSG_WAITTIME_SHOULD_NOT_LT_0 = "等待时间不能小于0!";

    public Double calculate(final double distance, final int waitMinutes) {
        checkCalculateParam(distance, waitMinutes);
        double driveFee = INIT_PRICE + distanceFee(distance) + farFee(distance);
        return driveFee + waitFee(waitMinutes);
    }

    private double farFee(final double distance) {
        return Math.max((distance - FAR_DISTANCE), 0) * UNIT_PRICE * FAR_RATE;
    }

    private double distanceFee(final double distance) {
        return Math.max((distance - INIT_DISTANCE), 0) * UNIT_PRICE;
    }

    private double waitFee(final int waitMinutes) {
        return waitMinutes * MINUTE_PRICE;
    }

    private void checkCalculateParam(final double distance, final int waitMinutes) {
        if (distance <= 0) {
            throw new IllegalArgumentException(MSG_DISTANCE_SHOULD_GT_0);
        }
        if (waitMinutes < 0) {
            throw new IllegalArgumentException(MSG_WAITTIME_SHOULD_NOT_LT_0);
        }
    }
}
