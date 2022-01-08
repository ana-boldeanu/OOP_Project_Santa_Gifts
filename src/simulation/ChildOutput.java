package simulation;

import database.Gift;
import enums.Category;

import java.util.List;

/**
 * This class contains only the information about a Child that is printed to output (Printing
 * required a new class in order to use Jackson)
 */
public final class ChildOutput {
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
     * City
     */
    private final String city;
    /**
     * Age
     */
    private final int age;
    /**
     * List of Gifts preferences
     */
    private final List<Category> giftsPreferences;
    /**
     * Average Score in current Round
     */
    private final Double averageScore;
    /**
     * Nice Scores (used to compute Average Score)
     */
    private final List<Double> niceScoreHistory;
    /**
     * Santa's budget for this Child in current Round
     */
    private final Double assignedBudget;
    /**
     * Gifts that this Child received in current Round
     */
    private final List<Gift> receivedGifts;

    public ChildOutput(final int id, final String lastName, final String firstName,
                       final String city, final int age, final List<Category> giftsPreferences,
                       final Double averageScore, final List<Double> niceScoresList,
                       final Double assignedBudget, final List<Gift> receivedGifts) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = giftsPreferences;
        this.averageScore = averageScore;
        this.niceScoreHistory = niceScoresList;
        this.assignedBudget = assignedBudget;
        this.receivedGifts = receivedGifts;
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

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    @Override
    public String toString() {
        return "ChildOutput{" + "id=" + id + ", lastName='" + lastName + '\'' + ", firstName='"
                + firstName + '\'' + ", age=" + age + ", giftsPreferences=" + giftsPreferences
                + ", averageScore=" + averageScore + ", niceScoreHistory=" + niceScoreHistory
                + ", assignedBudget=" + assignedBudget + ", receivedGifts=" + receivedGifts + '}';
    }
}
