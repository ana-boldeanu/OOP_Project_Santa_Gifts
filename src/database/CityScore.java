package database;

import enums.Cities;

import java.util.List;

public class CityScore {
    /**
     * The corresponding Cities Enum for this City
     */
    private final Cities cityEnum;
    /**
     * The averageScore of this City
     */
    private Double averageScore;

    public CityScore(Cities cityEnum, Double averageScore) {
        this.cityEnum = cityEnum;
        this.averageScore = averageScore;
    }

    public void computeCityScore(List<Child> currChildrenList) {
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

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }
}