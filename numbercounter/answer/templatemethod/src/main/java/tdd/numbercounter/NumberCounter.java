package tdd.numbercounter;

public class NumberCounter {
    public static final int EVEN = 1;
    public static final int ODD = 2;
    public static final int POSITIVE = 3;
    public static final int NEGATIVE = 4;

    private final int[] numbers;


    public NumberCounter(int... numbers) {
        this.numbers = numbers;
    }

    public int count(int numberType) {
        int sum = 0;
        for (int i : numbers) {
            if (check(i, numberType)) {
                sum++;
            }
        }

        return sum;
    }

    private boolean check(int i, int numberType) {
        boolean result = false;
        if (numberType == EVEN) {
            result = i % 2 == 0;

        } else if (numberType == ODD) {
            result = i % 2 == 1;

        } else if (numberType == POSITIVE) {
            result = i > 0;

        } else if (numberType == NEGATIVE) {
            result = i < 0;

        }

        return result;

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
