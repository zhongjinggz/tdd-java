package tdd.performancebill.domain.model.play;

import tdd.performancebill.domain.model.play.amountstrategy.AmountStrategy;
import tdd.performancebill.domain.model.play.volumecreditsstrategy.VolumeCreditsStrategy;

public class Play {
    private String id;
    private String name;
    private String type;
    private AmountStrategy amountStrategy;
    private VolumeCreditsStrategy volumeCreditsStrategy;

    public Play(String id
            , String name
            , String type
            , AmountStrategy amountStrategy
            , VolumeCreditsStrategy volumeCreditsStrategy) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amountStrategy = amountStrategy;
        this.volumeCreditsStrategy = volumeCreditsStrategy;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public AmountStrategy getAmountStrategy() {
        return amountStrategy;
    }
    public VolumeCreditsStrategy getVolumeCreditsStrategy() {
        return volumeCreditsStrategy;
    }
}
