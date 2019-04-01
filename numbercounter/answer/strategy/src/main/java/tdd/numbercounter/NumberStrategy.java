package tdd.numbercounter;

public abstract class NumberStrategy {
    public static final NumberStrategy even = new EvenStrategy();
    public static final NumberStrategy odd = new OddStrategy();
    public static final NumberStrategy positive = new PositiveStrategy();
    public static final NumberStrategy negative = new NegativeStrategy();

    public abstract boolean check(int i);
}
