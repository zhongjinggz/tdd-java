package tdd.performancebill.domain.model.play.volumecreditsstrategy;

public class TradegyCreditsStrategy extends VolumeCreditsStrategy {
    @Override
    protected int extraCredits(int audience) {
        return 0;
    }
}
