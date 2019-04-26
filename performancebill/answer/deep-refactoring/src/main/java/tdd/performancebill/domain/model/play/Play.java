package tdd.performancebill.domain.model.play;

import tdd.performancebill.domain.model.play.amountstrategy.AmountStrategy;
import tdd.performancebill.domain.model.play.volumecreditsstrategy.VolumeCreditsStrategy;

public class Play {
    private String id;
    private String name;
    private PlayType type;

    public Play(String id
            , String name
            , PlayType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AmountStrategy getAmountStrategy() {
        return type.getAmountStrategy();
    }

    public VolumeCreditsStrategy getVolumeCreditsStrategy() {
        return type.getVolumeCreditsStrategy();
    }
}
