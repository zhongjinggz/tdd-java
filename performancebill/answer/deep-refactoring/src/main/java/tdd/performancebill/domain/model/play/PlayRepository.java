package tdd.performancebill.domain.model.play;

import java.util.Map;

public interface PlayRepository {
    Map<String, Play> findPlays();
}
