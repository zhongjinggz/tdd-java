package tdd.numbercounter;

public abstract class NumberStrategy {
    public static final NumberStrategy EVEN = new EvenStrategy();
    public static final NumberStrategy ODD = new OddStrategy();
    public static final NumberStrategy POSITIVE = new PositiveStrategy();
    public static final NumberStrategy NEGATIVE = new NegativeStrategy();

    public abstract boolean check(int i);
}
