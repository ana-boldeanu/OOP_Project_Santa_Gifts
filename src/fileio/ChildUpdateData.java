package fileio;

import enums.Category;

import java.util.List;

/**
 * Children information that needs to be updated each round (used in AnnualChangesData)
 */
public final class ChildUpdateData {
    /**
     * ID of the child to be updated
     */
    private final int id;
    /**
     * New Nice Score to be added to niceScoresList
     */
    private final Double newNiceScore;
    /**
     * New List of Gifts preferences to be added
     */
    private final List<Category> newGiftsPreferences;

    public ChildUpdateData(final int id, final Double newNiceScore,
                           final List<Category> newGiftsPreferences) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftsPreferences = newGiftsPreferences;
    }

    public int getId() {
        return id;
    }

    public Double getNewNiceScore() {
        return newNiceScore;
    }

    public List<Category> getNewGiftsPreferences() {
        return newGiftsPreferences;
    }
}
