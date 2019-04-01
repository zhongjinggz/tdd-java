package tdd.numbercounter;

public class NegativeCounter extends NumberCounter {
    public NegativeCounter(int... numbers) {
        super(numbers);

    }

    @Override
    boolean check(int i) {
        return i < 0;
    }
}
