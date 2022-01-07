package fileio;

import database.ChildUpdateData;

import java.util.List;

/**
 * Class that contains information that changes in each round
 */
public final class AnnualChangesData {
    /**
     * New Santa's budget
     */
    private final double newSantaBudget;
    /**
     * New Gifts to be added in SantaGiftsList
     */
    private final List<GiftInputData> newGiftsList;
    /**
     * New Children to be added in ChildrenList
     */
    private final List<ChildInputData> newChildrenList;
    /**
     * List of updates for some Children
     */
    private final List<ChildUpdateData> childrenUpdates;

    public AnnualChangesData(final double newSantaBudget, final List<GiftInputData> newGiftsList,
                             final List<ChildInputData> newChildrenList,
                             final List<ChildUpdateData> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGiftsList = newGiftsList;
        this.newChildrenList = newChildrenList;
        this.childrenUpdates = childrenUpdates;
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public List<GiftInputData> getNewGiftsList() {
        return newGiftsList;
    }

    public List<ChildInputData> getNewChildrenList() {
        return newChildrenList;
    }

    public List<ChildUpdateData> getChildrenUpdates() {
        return childrenUpdates;
    }
}
