package tdd.numbercounter;

public class NegativeStrategy extends NumberStrategy {
    @Override
    public boolean check(int i) {
        return i < 0;
    }
}
