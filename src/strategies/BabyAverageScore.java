package strategies;

import common.Constants;

public final class BabyAverageScore implements AverageScoreStrategy {

    @Override
    public Double getScore() {
        return Constants.MAX_AVERAGE_SCORE;
    }
}
