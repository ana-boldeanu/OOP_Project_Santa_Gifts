package score_strategies;

import database.Child;

public final class KidAverageScore implements AverageScoreStrategy {
    private final Child kid;

    public KidAverageScore(final Child kid) {
        this.kid = kid;
    }

    @Override
    public Double getScore() {
        Double sumScores = 0.0;
        int nrScores = 0;
        double averageScore;

        for (Double score : kid.getNiceScoresList()) {
            sumScores += score;
            nrScores++;
        }

        averageScore = sumScores / nrScores;
        return averageScore;
    }
}
