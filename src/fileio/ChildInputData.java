package fileio;

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
