package fileio;

import java.util.List;

/**
 * This class contains information parsed from the input files.
 * Do not modify.
 */
public final class Input {
    /**
     * Number of years (rounds to run the simulation)
     */
    private int numberOfYears;
    /**
     * Santa's initial budget (used in round 0)
     */
    private Double santaBudget;
    /**
     * List of children from initialData
     */
    private List<ChildInputData> initialChildrenList;
    /**
     * List of gifts from initialData
     */
    private List<GiftInputData> initialSantaGiftsList;
    /**
     * List of annual changes (updates for each round)
     */
    private final List<AnnualChangesData> annualChangesList;

    public Input(final int numberOfYears, final Double santaBudget,
                 final List<ChildInputData> initialChildrenList,
                 final List<GiftInputData> initialSantaGiftsList,
                 final List<AnnualChangesData> annualChangesList) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.initialChildrenList = initialChildrenList;
        this.initialSantaGiftsList = initialSantaGiftsList;
        this.annualChangesList = annualChangesList;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public List<ChildInputData> getInitialChildrenList() {
        return initialChildrenList;
    }

    public List<GiftInputData> getInitialSantaGiftsList() {
        return initialSantaGiftsList;
    }

    public List<AnnualChangesData> getAnnualChangesList() {
        return annualChangesList;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public void setInitialChildrenList(List<ChildInputData> initialChildrenList) {
        this.initialChildrenList = initialChildrenList;
    }

    public void setInitialSantaGiftsList(List<GiftInputData> initialSantaGiftsList) {
        this.initialSantaGiftsList = initialSantaGiftsList;
    }

    @Override
    public String toString() {
        return "Input{" +
                "numberOfYears=" + numberOfYears +
                ", santaBudget=" + santaBudget +
                ", initialChildrenList=" + initialChildrenList +
                ", initialSantaGiftsList=" + initialSantaGiftsList +
                ", annualChangesList=" + annualChangesList +
                '}';
    }
}
