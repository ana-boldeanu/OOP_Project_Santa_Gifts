package score_strategies;

import common.Constants;

public final class BabyAverageScore implements AverageScoreStrategy {

    @Override
    public Double getScore() {
        return Constants.BABY_AVERAGE_SCORE;
    }
}
