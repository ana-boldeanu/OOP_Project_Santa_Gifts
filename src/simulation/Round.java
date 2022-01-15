package simulation;

import common.Constants;
import database.AnnualChange;
import database.Child;
import database.ChildUpdateData;
import database.CityScore;
import database.Database;
import database.GiftType;
import database.ReceivedGift;
import enums.AgeCategory;
import enums.Category;
import enums.Cities;
import enums.ElvesType;
import simulation.output.AnnualChildren;
import simulation.output.ChildOutput;

import java.util.ArrayList;
import java.util.Comparator;
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
    private List<Child> currChildrenList = new ArrayList<>();
    /**
     * List of gifts from initialData
     */
    private final List<GiftType> currGiftsList = new ArrayList<>();
    /**
     * List of CityScores
     */
    private final List<CityScore> currCityScores = new ArrayList<>();

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

        // Add Cities to the list, initially each City has averageScore = 0
        for (Cities city : Cities.values()) {
            currCityScores.add(new CityScore(city, 0.0));
        }
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
     * Compute the averageScores of the Cities
     */
    public void computeCityScores() {
        // First sort the childrenList by ID
        ChildrenSorter childrenSorter = new ChildrenSorter();
        childrenSorter.sortById(currChildrenList);

        // Compute averageScore for each City
        for (CityScore cityScore : currCityScores) {
            cityScore.computeCityScore(currChildrenList);
        }
    }

    /**
     * Compute all assignedBudgets for each Child in this Round
     */
    public void computeBudgets() {
        double sumAllScores = 0.0;
        for (Child child : currChildrenList) {
            sumAllScores += child.getAverageScore();
        }

        // Unit used for the current budget
        Double budgetUnit = currBudget / sumAllScores;

        for (Child child : currChildrenList) {
            // Compute childBudget
            double childBudget = child.getAverageScore() * budgetUnit;

            // Apply BLACK or PINK elves bonuses
            if (child.getElfType().equals(ElvesType.BLACK)) {
                childBudget -= childBudget
                        * Constants.BLACK_ELF_PERCENTAGE / Constants.PERCENT;

            } else if (child.getElfType().equals(ElvesType.PINK)) {
                childBudget += childBudget
                        * Constants.PINK_ELF_PERCENTAGE / Constants.PERCENT;
            }

            child.setAssignedBudget(childBudget);
        }
    }

    /**
     * Distribute Gifts to each Child in this Round, according to the desired Strategy
     */
    public void distributeGifts(final String distributionStrategy) {
        // Empty the Lists of receivedGifts
        for (Child child : currChildrenList) {
            child.resetReceivedGifts();
        }

        // Order the list of Children according to the distributionStrategy
        ChildrenSorter childrenSorter = new ChildrenSorter();
        currChildrenList = childrenSorter.sortChildren(currChildrenList, distributionStrategy,
                currCityScores);

        // Distribute the Gifts
        for (Child child : currChildrenList) {
            double budget = child.getAssignedBudget();
            for (Category category : child.getGiftsPreferences()) {
                for (GiftType gift : currGiftsList) {
                    if (gift.getCategory().equals(category) && gift.getQuantity() > 0) {
                        if (gift.getPrice() <= budget) {
                            child.receiveGift(new ReceivedGift(gift.getProductName(),
                                    gift.getPrice(), gift.getCategory()));
                            budget -= gift.getPrice();
                            gift.decreaseQuantity();
                        }
                        break;
                    }
                }
            }
        }

        // Apply YELLOW elf bonus
        yellowElfBonusGifts();
    }

    /**
     * Apply the YELLOW Elf bonus after regular Gifts distribution
     */
    public void yellowElfBonusGifts() {
        for (Child child : currChildrenList) {
            if (child.getElfType().equals(ElvesType.YELLOW)
                    && child.getReceivedGifts().isEmpty()) {
                Category prefCategory = child.getGiftsPreferences().get(0);
                for (GiftType gift : currGiftsList) {
                    if (gift.getCategory().equals(prefCategory)) {
                        if (gift.getQuantity() > 0) {
                            // Receive the cheapest Gift of this Category
                            child.receiveGift(new ReceivedGift(gift.getProductName(),
                                    gift.getPrice(), gift.getCategory()));
                            gift.decreaseQuantity();

                        } // else The cheapest Gift is out of stock
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
            List<ReceivedGift> receivedGifts = new ArrayList<>(child.getReceivedGifts());
            List<Double> niceScoresHistory = new ArrayList<>(child.getNiceScoresList());

            annualChildren.add(new ChildOutput(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getCity(), child.getAge(),
                    giftPreferences, child.getAverageScore(), niceScoresHistory,
                    child.getAssignedBudget(), receivedGifts));
        }

        annualChildren.sort(new Comparator<ChildOutput>() {
            @Override
            public int compare(final ChildOutput o1, final ChildOutput o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

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

        // Apply ChildUpdates
        for (ChildUpdateData childUpdate : changes.getChildrenUpdates()) {
            int id = childUpdate.getId();
            for (Child child : currChildrenList) {
                if (child.getId() == id) {
                    // Update the new giftsPreferences for this Child
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

                    // Add the new niceScore to this Child's list of niceScores
                    if (childUpdate.getNewNiceScore() != null) {
                        child.receiveNiceScore(childUpdate.getNewNiceScore());
                    }

                    // Assign the new Elf to this Child
                    if (childUpdate.getNewElfType() != null) {
                        child.setElfType(childUpdate.getNewElfType());
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
