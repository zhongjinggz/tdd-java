package tdd.performancebill.infrastructure.persistence;

import org.springframework.stereotype.Repository;
import tdd.performancebill.domain.model.play.Play;
import tdd.performancebill.domain.model.play.PlayRepository;
import tdd.performancebill.domain.model.play.amountstrategy.AmountStrategy;
import tdd.performancebill.domain.model.play.volumecreditsstrategy.VolumeCreditsStrategy;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PlayRepositoryMem implements PlayRepository {
    @Override
    public Map<String, Play> findPlays() {
        Map<String, Play> plays = new HashMap<>();
        plays.put("hamlet"
                , new Play("hamelet"
                        , "Hamlet"
                        , "tragedy"
                        , AmountStrategy.TRAGEDY
                        , VolumeCreditsStrategy.TRADEGY));
        plays.put("as-like"
                , new Play("as-like"
                        , "As You Like It"
                        , "comedy"
                        , AmountStrategy.COMEDY
                        , VolumeCreditsStrategy.COMEDY));
        plays.put("othello"
                , new Play("othello"
                        , "Othello"
                        , "tragedy"
                        , AmountStrategy.TRAGEDY
                        , VolumeCreditsStrategy.TRADEGY ));
        return plays;
    }
}
