package tdd.performancebill;

public class Performance {

    private String playId;
    private int audience;

    public Performance() {}

    public Performance(String playId, int audience) {
        this.playId = playId;
        this.audience = audience;
    }

    public String getPlayId() {
        return playId;
    }

    public int getAudience() {
        return audience;
    }
}
