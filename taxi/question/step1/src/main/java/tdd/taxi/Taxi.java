package tdd.taxi;

public class Taxi {
    public static final double INITIAL_FEE = 6.0;
    public static final int INITIAL_DISTANCE = 2;
    public static final double UNIT_PRICE = 0.8;

    public double calculate(int distance) {
        if(distance <= INITIAL_DISTANCE ) {
            return INITIAL_FEE;
        }
        return INITIAL_FEE + UNIT_PRICE * (distance - INITIAL_DISTANCE);
    }
}
