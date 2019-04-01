package tdd.numbercounter;

public class EvenCounter extends NumberCounter{
    public EvenCounter(int... numbers) {
        super(numbers);

    }

    @Override
    boolean check(int i) {
        return i % 2 == 0;
    }
}
