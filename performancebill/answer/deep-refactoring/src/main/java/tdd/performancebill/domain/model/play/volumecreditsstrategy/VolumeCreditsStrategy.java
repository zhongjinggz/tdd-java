package tdd.performancebill.domain.model.play.volumecreditsstrategy;

public abstract class VolumeCreditsStrategy {
    public static final VolumeCreditsStrategy TRADEGY = new TradegyCreditsStrategy();
    public static final VolumeCreditsStrategy COMEDY = new ComedyCreditsStrategy();

    public int calculate(int audience) {
        return Math.max(audience - 30, 0)
                + extraCredits(audience);
    }

    protected abstract int extraCredits(int audience);
}
