package database;

import java.util.List;

public final class AnnualChange {
    /**
     * New Santa's budget
     */
    private final double newSantaBudget;
    /**
     * New Gifts to be added in SantaGiftsList
     */
    private final List<Gift> newGiftsList;
    /**
     * New Children to be added in ChildrenList
     */
    private final List<Child> newChildrenList;
    /**
     * List of updates for some Children
     */
    private final List<ChildUpdateData> childrenUpdates;

    public AnnualChange(final double newSantaBudget, final List<Gift> newGiftsList,
                        final List<Child> newChildrenList,
                        final List<ChildUpdateData> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGiftsList = newGiftsList;
        this.newChildrenList = newChildrenList;
        this.childrenUpdates = childrenUpdates;
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public List<Gift> getNewGiftsList() {
        return newGiftsList;
    }

    public List<Child> getNewChildrenList() {
        return newChildrenList;
    }

    public List<ChildUpdateData> getChildrenUpdates() {
        return childrenUpdates;
    }
}
