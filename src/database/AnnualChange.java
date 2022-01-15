package database;

import distribution_strategies.DistributionStrategy;

import java.util.List;

public final class AnnualChange {
    /**
     * New Santa's budget
     */
    private final double newSantaBudget;
    /**
     * New Gifts to be added in SantaGiftsList
     */
    private final List<GiftType> newGiftsList;
    /**
     * New Children to be added in ChildrenList
     */
    private final List<Child> newChildrenList;
    /**
     * List of updates for some Children
     */
    private final List<ChildUpdateData> childrenUpdates;
    /**
     * Strategy used to distribute Gifts
     */
    private final DistributionStrategy distributionStrategy;

    public AnnualChange(final double newSantaBudget, final List<GiftType> newGiftsList,
                        final List<Child> newChildrenList,
                        final List<ChildUpdateData> childrenUpdates,
                        final DistributionStrategy distributionStrategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGiftsList = newGiftsList;
        this.newChildrenList = newChildrenList;
        this.childrenUpdates = childrenUpdates;
        this.distributionStrategy = distributionStrategy;
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public List<GiftType> getNewGiftsList() {
        return newGiftsList;
    }

    public List<Child> getNewChildrenList() {
        return newChildrenList;
    }

    public List<ChildUpdateData> getChildrenUpdates() {
        return childrenUpdates;
    }

    public DistributionStrategy getDistributionStrategy() {
        return distributionStrategy;
    }

    @Override
    public String toString() {
        return "AnnualChange{" + "newSantaBudget=" + newSantaBudget
                + ", newGiftsList=" + newGiftsList + ", newChildrenList=" + newChildrenList
                + ", childrenUpdates=" + childrenUpdates
                + ", distributionStrategy=" + distributionStrategy + '}';
    }
}
