package tdd.numbercounter;

public class PositiveStrategy extends NumberStrategy {
    @Override
    public boolean check(int i) {
        return i > 0;
    }
}
