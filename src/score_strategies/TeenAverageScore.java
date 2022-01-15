package score_strategies;

import database.Child;

public final class TeenAverageScore implements AverageScoreStrategy {
    private Child teen;

    public TeenAverageScore(final Child teen) {
        this.teen = teen;
    }

    @Override
    public Double getScore() {
        double averageScore;
        int weight = 1;
        int sumWeights = 0;
        double sumScores = 0.0;

        for (Double score : teen.getNiceScoresList()) {
            sumScores += weight * score;
            sumWeights += weight;
            weight++;
        }

        averageScore = sumScores / sumWeights;
        return averageScore;
    }
}
