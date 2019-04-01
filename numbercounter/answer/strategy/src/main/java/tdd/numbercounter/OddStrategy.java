package tdd.numbercounter;

public class OddStrategy extends NumberStrategy {
    @Override
    public boolean check(int i) {
        return i % 2 == 1;
    }
}
