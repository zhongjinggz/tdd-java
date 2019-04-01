package tdd.numbercounter;

public class OddCounter extends NumberCounter{
    public OddCounter(int... numbers) {
        super(numbers);

    }

    @Override
    boolean check(int i) {
        return i % 2 == 1;
    }
}
