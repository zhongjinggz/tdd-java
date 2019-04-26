package tdd.performancebill.domain.model.play;

import tdd.performancebill.domain.model.play.amountstrategy.AmountStrategy;
import tdd.performancebill.domain.model.play.volumecreditsstrategy.VolumeCreditsStrategy;

public enum PlayType {
    TRADEGY(AmountStrategy.TRADEGY
            , VolumeCreditsStrategy.TRADEGY
            , "tradegy"),
    COMEDY(AmountStrategy.COMEDY
            , VolumeCreditsStrategy.COMEDY
            , "comedy");


    private AmountStrategy amountStrategy;
    private VolumeCreditsStrategy volumeCreditsStrategy;
    private String desc;


    PlayType(AmountStrategy amountStrategy
            , VolumeCreditsStrategy creditsStrategy
            , String desc) {
        this.amountStrategy = amountStrategy;
        this.volumeCreditsStrategy = creditsStrategy;
        this.desc = desc;
    }

    public AmountStrategy getAmountStrategy() {
        return amountStrategy;
    }

    public VolumeCreditsStrategy getVolumeCreditsStrategy() {
        return volumeCreditsStrategy;
    }

    public String getDesc() {
        return desc;
    }
}
