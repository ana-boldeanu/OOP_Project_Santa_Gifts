package strategies;

public final class YoungAdultAverageScore implements AverageScoreStrategy {
    @Override
    public Double getScore() {
        // YoungAdults will not have an averageScore
        return null;
    }
}
