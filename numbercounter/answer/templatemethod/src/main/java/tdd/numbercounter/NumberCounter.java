package tdd.numbercounter;

public class NumberCounter {

    private final int[] numbers;


    public NumberCounter(int... numbers) {
        this.numbers = numbers;
    }

    public int count() {
        int sum = 0;
        for (int i : numbers) {
            if (check(i)) {
                sum++;
            }
        }

        return sum;

    }

    boolean check(int i) {
        return false;
    }
}
