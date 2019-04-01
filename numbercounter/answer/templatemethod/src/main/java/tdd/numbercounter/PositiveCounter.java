package tdd.numbercounter;

public class PositiveCounter extends NumberCounter{
    public PositiveCounter(int... numbers) {
        super(numbers);

    }

    @Override
    boolean check(int i) {
        return i > 0;
    }
}
