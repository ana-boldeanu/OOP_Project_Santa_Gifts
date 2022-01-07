package database;

import enums.AgeCategory;
import enums.Category;
import strategies.AverageScoreStrategy;
import strategies.AverageStrategyFactory;

import java.util.List;

/**
 * General information about a Child. This abstract class will be extended according to the
 * ageCategory of the child.
 */
public final class Child {
    /**
     * Child's ID (unique)
     */
    private final int id;
    /**
     * Last name
     */
    private final String lastName;
    /**
     * First name
     */
    private final String firstName;
    /**
     * Age
     */
    private final int age;
    /**
     * Age Category (based on age)
     */
    private final AgeCategory ageCategory;
    /**
     * City
     */
    private final String city;
    /**
     * List of Gifts preferences
     */
    private final List<Category> giftsPreferences;
    /**
     * Nice Scores (used to compute Average Score)
     */
    private final List<Double> niceScoresList;
    /**
     * Strategy used to compute averageScore
     */
    private AverageScoreStrategy averageScoreStrategy;
    /**
     * Average Score
     */
    private Double averageScore;

    public Child(final int id, final String lastName, final String firstName, final int age,
                 final AgeCategory ageCategory, final String city,
                 final List<Category> giftsPreferences, final List<Double> niceScoresList) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.ageCategory = ageCategory;
        this.city = city;
        this.giftsPreferences = giftsPreferences;
        this.niceScoresList = niceScoresList;
    }

    /**
     * Create and set the AverageScoreStrategy used for this Child
     */
    public void setAverageScoreStrategy() {
        AverageStrategyFactory strategyFactory = AverageStrategyFactory.getInstance();
        this.averageScoreStrategy = strategyFactory.createStrategy(this);
    }

    public void computeAverageScore() {
        this.averageScore = averageScoreStrategy.getScore();
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public String getCity() {
        return city;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public List<Double> getNiceScoresList() {
        return niceScoresList;
    }

    public AverageScoreStrategy getAverageScoreStrategy() {
        return averageScoreStrategy;
    }

    public Double getAverageScore() {
        return averageScore;
    }
}
