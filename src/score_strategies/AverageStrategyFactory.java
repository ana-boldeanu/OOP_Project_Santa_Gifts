package score_strategies;

import database.Child;

public final class AverageStrategyFactory {
    /**
     * Unique instance of this Singleton class
     */
    private static AverageStrategyFactory instance = null;

    // This is a Singleton
    private AverageStrategyFactory() {
    }

    /**
     * Get the unique instance of this class
     */
    public static AverageStrategyFactory getInstance() {
        if (instance == null) {
            instance = new AverageStrategyFactory();
        }
        return instance;
    }

    /**
     * Create and return an AverageScoreStrategy according to the age of the Child given as
     * parameter
     */
    public AverageScoreStrategy createStrategy(final Child child) {
        switch (child.getAgeCategory()) {
            case BABY:
                return new BabyAverageScore();

            case KID:
                return new KidAverageScore(child);

            case TEEN:
                return new TeenAverageScore(child);

            case YOUNG_ADULT:
                return new YoungAdultAverageScore();

            default:
        }

        return null;
    }
}
