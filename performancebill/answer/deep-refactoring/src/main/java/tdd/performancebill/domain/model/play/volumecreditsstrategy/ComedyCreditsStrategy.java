package tdd.performancebill.domain.model.play.volumecreditsstrategy;

public class ComedyCreditsStrategy extends VolumeCreditsStrategy {
    @Override
    protected int extraCredits(int audience) {
        return Math.floorDiv(audience, 5);
    }
}
