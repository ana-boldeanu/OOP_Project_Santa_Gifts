package database;

import enums.Category;
import enums.ElvesType;

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
    /**
     * Elf Type
     */
    private final ElvesType newElfType;

    public ChildUpdateData(final int id, final Double newNiceScore,
                           final List<Category> newGiftsPreferences,
                           final ElvesType newElfType) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftsPreferences = newGiftsPreferences;
        this.newElfType = newElfType;
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

    public ElvesType getNewElfType() {
        return newElfType;
    }
}
