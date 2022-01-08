package simulation;


import database.AnnualChange;
import database.Child;
import database.Database;
import database.Gift;
import database.ChildUpdateData;
import enums.AgeCategory;
import enums.Category;
import simulation.output.AnnualChildren;
import simulation.output.ChildOutput;

import java.util.ArrayList;
import java.util.List;

/**
 * A round in the simulation
 */
public final class Round {
    /**
     * Database for Round 0
     */
    private final Database database;
    /**
     * Current Round
     */
    private int currRound;
    /**
     * Santa's budget
     */
    private Double currBudget;
    /**
     * List of children
     */
    private final List<Child> currChildrenList = new ArrayList<>();
    /**
     * List of gifts from initialData
     */
    private final List<Gift> currGiftsList = new ArrayList<>();

    public Round(final Database database) {
        this.database = database;
        this.currRound = 0;
        this.currBudget = database.getInitialSantaBudget();
        this.currGiftsList.addAll(database.getInitialSantaGiftsList());
        this.currChildrenList.addAll(database.getInitialChildrenList());

        // Keep only Babies, Kids and Teens
        currChildrenList.removeIf(child -> child.getAgeCategory().equals(AgeCategory.YOUNG_ADULT));

        // Sort Gifts according to their price, in ascending order
        currGiftsList.sort((o1, o2) -> {
            if (o1.getCategory().equals(o2.getCategory())) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
            return o1.getCategory().compareTo(o2.getCategory());
        });
    }

    /**
     * Compute the averageScores of each Child in this Round
     */
    public void computeAverageScores() {
        for (Child child : currChildrenList) {
            child.computeAverageScore();
        }
    }

    /**
     * Compute the budgetUnit and all assignedBudgets for each Child in this Round
     */
    public void computeBudgets() {
        double sumAllScores = 0.0;
        for (Child child : currChildrenList) {
            sumAllScores += child.getAverageScore();
        }

        // Unit used for the current budget
        Double budgetUnit = currBudget / sumAllScores;

        for (Child child : currChildrenList) {
            double childBudget = child.getAverageScore() * budgetUnit;
            child.setAssignedBudget(childBudget);
        }
    }

    /**
     * Distribute Gifts to each Child in this Round
     */
    public void distributeGifts() {
        // Empty the Lists of receivedGifts
        for (Child child :currChildrenList) {
            child.resetReceivedGifts();
        }

        // Distribute new Gifts
        for (Child child : currChildrenList) {
            double budget = child.getAssignedBudget();
            for (Category category : child.getGiftsPreferences()) {
                for (Gift gift : currGiftsList) {
                    if (gift.getCategory().equals(category)) {
                        if (gift.getPrice() <= budget) {
                            child.receiveGift(gift);
                            budget -= gift.getPrice();
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * Return a list of the results regarding Children from this Round
     */
    public AnnualChildren getResults() {
        List<ChildOutput> annualChildren = new ArrayList<>();

        for (Child child : currChildrenList) {
            List<Category> giftPreferences = new ArrayList<>(child.getGiftsPreferences());
            List<Gift> receivedGifts = new ArrayList<>(child.getReceivedGifts());
            List<Double> niceScoresHistory = new ArrayList<>(child.getNiceScoresList());

            annualChildren.add(new ChildOutput(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getCity(), child.getAge(),
                    giftPreferences, child.getAverageScore(), niceScoresHistory,
                    child.getAssignedBudget(), receivedGifts));
        }

        return new AnnualChildren(annualChildren);
    }

    /**
     * Update the ages for each Child, used in updateRound method
     */
    public void updateAges() {
        for (Child child : currChildrenList) {
            child.incrementAge();
        }
    }

    /**
     * Update current Round according to the list of annual changes
     */
    public void updateRound(final AnnualChange changes) {
        currRound++;
        updateAges();
        currChildrenList.addAll(changes.getNewChildrenList());
        currGiftsList.addAll(changes.getNewGiftsList());
        currBudget = changes.getNewSantaBudget();

        for (ChildUpdateData childUpdate : changes.getChildrenUpdates()) {
            int id = childUpdate.getId();
            for (Child child : currChildrenList) {
                if (child.getId() == id) {
                    if (childUpdate.getNewGiftsPreferences().size() != 0) {
                        List<Category> newGiftsPreferences = new ArrayList<>();
                        for (Category category : childUpdate.getNewGiftsPreferences()) {
                            if (!newGiftsPreferences.contains(category)) {
                                newGiftsPreferences.add(category);
                            }
                        }
                        for (Category category : child.getGiftsPreferences()) {
                            if (!newGiftsPreferences.contains(category)) {
                                newGiftsPreferences.add(category);
                            }
                        }
                        child.setGiftsPreferences(newGiftsPreferences);
                    }
                    if (childUpdate.getNewNiceScore() != null) {
                        child.receiveNiceScore(childUpdate.getNewNiceScore());
                    }
                }
            }
        }

        // Remove Young Adults
        currChildrenList.removeIf(child -> child.getAgeCategory().equals(AgeCategory.YOUNG_ADULT));

        // Sort Gifts according to their price, in ascending order
        currGiftsList.sort((o1, o2) -> {
            if (o1.getCategory().equals(o2.getCategory())) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
            return o1.getCategory().compareTo(o2.getCategory());
        });
    }

    public Database getDatabase() {
        return database;
    }

    @Override
    public String toString() {
        return "Round{" + "currRound=" + currRound + ", currBudget=" + currBudget
                + ", currChildrenList=" + currChildrenList
                + ", currGiftsList=" + currGiftsList + '}';
    }
}
