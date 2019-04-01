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
        if (numberType == EVEN) {
            for (int i : numbers) {
                if (i % 2 == 0) {
                    sum++;
                }
            }

        } else if (numberType == ODD){
            for (int i : numbers) {
                if (i % 2 == 1) {
                    sum++;
                }
            }

        } else if (numberType == POSITIVE){
            for (int i : numbers) {
                if (i > 0) {
                    sum++;
                }
            }

        } else if (numberType == NEGATIVE){
            for (int i : numbers) {
                if (i < 0) {
                    sum++;
                }
            }

        }

        return sum;
    }

}
