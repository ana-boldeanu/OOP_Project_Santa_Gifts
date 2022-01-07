package fileio;

import common.Constants;
import enums.AgeCategory;
import enums.Category;

import java.util.List;

/**
 * Input information about a Child.
 * Do not modify.
 */
public final class ChildInputData {
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
     * City
     */
    private final String city;
    /**
     * Initial Nice Score of the child
     */
    private final Double niceScore;
    /**
     * List of Gifts preferences
     */
    private final List<Category> giftsPreferences;

    public ChildInputData(final int id, final String lastName, final String firstName,
                          final int age, final String city,
                          final Double niceScore, final List<Category> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * Compute and return the AgeCategory enum according to the age of the Child given as input
     */
    public AgeCategory getAgeCategory() {
        if (age <= Constants.BABY_MAX_AGE) {
            return AgeCategory.BABY;

        } else if (age <= Constants.KID_MAX_AGE) {
            return AgeCategory.KID;

        } else if (age <= Constants.TEEN_MAX_AGE) {
            return AgeCategory.TEEN;

        } else {
            return AgeCategory.YOUNG_ADULT;
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

    public String getCity() {
        return city;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }
}
