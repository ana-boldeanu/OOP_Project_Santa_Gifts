package database;

import enums.Cities;

import java.util.List;

/**
 * City class that contains the averageScore of a City
 */
public final class CityScore {
    /**
     * The corresponding Cities Enum for this City
     */
    private final Cities cityEnum;
    /**
     * The averageScore of this City
     */
    private Double averageScore;

    public CityScore(final Cities cityEnum, final Double averageScore) {
        this.cityEnum = cityEnum;
        this.averageScore = averageScore;
    }

    /**
     * Computes the averageScore of this City as the average of the scores of the Children
     * who live here
     */
    public void computeCityScore(final List<Child> currChildrenList) {
        Double sumScores = 0.0;
        int nrScores = 0;

        for (Child child : currChildrenList) {
            if (child.getCity().equals(cityEnum.getValue())) {
                sumScores += child.getAverageScore();
                nrScores++;
            }
        }

        averageScore = sumScores / nrScores;
    }

    public Cities getCityEnum() {
        return cityEnum;
    }

    public Double getAverageScore() {
        return averageScore;
    }
}
