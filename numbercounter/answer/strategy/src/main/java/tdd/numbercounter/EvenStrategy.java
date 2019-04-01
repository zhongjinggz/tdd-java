package tdd.numbercounter;

public class EvenStrategy extends NumberStrategy {
    @Override
    public boolean check(int i) {
        return i % 2 == 0;
    }
}
