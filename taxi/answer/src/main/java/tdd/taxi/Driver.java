package tdd.taxi;

public class Driver {
    private final Taxi taxi;

    public Driver(final Taxi taxi) {
        this.taxi = taxi;
    }

    public double charge(final double distance, final int waitMinutes) {
        return Math.round(taxi.calculate(distance, waitMinutes));
    }
}
