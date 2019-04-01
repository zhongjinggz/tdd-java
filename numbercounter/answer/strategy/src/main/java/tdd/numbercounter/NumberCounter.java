package tdd.numbercounter;

public class NumberCounter {
    private final int[] numbers;


    public NumberCounter(int... numbers) {
        this.numbers = numbers;
    }

    public int count(NumberStrategy strategy) {
        int sum = 0;
        for (int i : numbers) {
            if (strategy.check(i)) {
                sum++;
            }
        }

        return sum;
    }
}
