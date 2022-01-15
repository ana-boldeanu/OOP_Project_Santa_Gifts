package database;

import common.Constants;
import enums.AgeCategory;
import enums.Category;
import enums.ElvesType;
import score_strategies.AverageScoreStrategy;
import score_strategies.AverageStrategyFactory;

import java.util.ArrayList;
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
    private int age;
    /**
     * Age Category (based on age)
     */
    private AgeCategory ageCategory;
    /**
     * City
     */
    private final String city;
    /**
     * List of Gifts preferences
     */
    private List<Category> giftsPreferences;
    /**
     * Nice Scores (used to compute Average Score)
     */
    private final List<Double> niceScoresList;
    /**
     * Strategy used to compute averageScore
     */
    private AverageScoreStrategy averageScoreStrategy;
    /**
     * Average Score in current Round
     */
    private Double averageScore;
    /**
     * Santa's budget for this Child in current Round
     */
    private Double assignedBudget;
    /**
     * Gifts that this Child received in current Round
     */
    private List<ReceivedGift> receivedGifts = new ArrayList<>();
    /**
     * Nice Score Bonus
     */
    private final Double niceScoreBonus;
    /**
     * Elf Type
     */
    private final ElvesType elfType;

    public Child(final int id, final String lastName, final String firstName, final int age,
                 final AgeCategory ageCategory, final String city,
                 final List<Category> giftsPreferences, final List<Double> niceScoresList,
                 final Double niceScoreBonus, final ElvesType elfType) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.ageCategory = ageCategory;
        this.city = city;
        this.giftsPreferences = giftsPreferences;
        this.niceScoresList = niceScoresList;
        this.niceScoreBonus = niceScoreBonus;
        this.elfType = elfType;
    }

    /**
     * Create and set the AverageScoreStrategy used for this Child
     */
    public void setAverageScoreStrategy() {
        AverageStrategyFactory strategyFactory = AverageStrategyFactory.getInstance();
        this.averageScoreStrategy = strategyFactory.createStrategy(this);
    }

    /**
     * Use this Child's Strategy to compute their averageScore
     */
    public void computeAverageScore() {
        this.averageScore = averageScoreStrategy.getScore();
    }

    /**
     * Add a Gift to this Child's receivedGifts List
     */
    public void receiveGift(final ReceivedGift gift) {
        this.receivedGifts.add(gift);
    }

    /**
     * Clear the list of Received Gifts for next Round
     */
    public void resetReceivedGifts() {
        this.receivedGifts.clear();
    }

    /**
     * Add a new NiceScore to this Child's niceScores List
     */
    public void receiveNiceScore(final Double niceScore) {
        this.niceScoresList.add(niceScore);
    }

    /**
     * Increment this Child's age and update their ageCategory
     */
    public void incrementAge() {
        age++;
        updateAgeCategory();
        setAverageScoreStrategy();
    }

    /**
     * Update this Child's ageCategory
     */
    public void updateAgeCategory() {
        if (age <= Constants.BABY_MAX_AGE) {
            ageCategory = AgeCategory.BABY;

        } else if (age <= Constants.KID_MAX_AGE) {
            ageCategory = AgeCategory.KID;

        } else if (age <= Constants.TEEN_MAX_AGE) {
            ageCategory = AgeCategory.TEEN;

        } else {
            ageCategory = AgeCategory.YOUNG_ADULT;
        }
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

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public List<ReceivedGift> getReceivedGifts() {
        return receivedGifts;
    }

    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public ElvesType getElfType() {
        return elfType;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public void setAgeCategory(final AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public void setReceivedGifts(final List<ReceivedGift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    @Override
    public String toString() {
        return "Child{" + "id=" + id + ", lastName='" + lastName + '\'' + ", firstName='"
                + firstName + '\'' + ", age=" + age + ", ageCategory=" + ageCategory
                + ", giftsPreferences=" + giftsPreferences + ", niceScoresList=" + niceScoresList
                + ", averageScore=" + averageScore + ", assignedBudget=" + assignedBudget
                + ", receivedGifts=" + receivedGifts + ", niceScoreBonus=" + niceScoreBonus
                + ", elfType=" + elfType + '}';
    }
}
