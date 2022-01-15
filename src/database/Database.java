package database;

import distribution_strategies.DistributionStrategyFactory;
import enums.Category;
import fileio.AnnualChangesData;
import fileio.ChildInputData;
import fileio.GiftInputData;
import fileio.Input;

import java.util.ArrayList;
import java.util.List;

public final class Database {
    /**
     * Unique instance of this Singleton class
     */
    private static Database instance = null;
    /**
     * Number of years (rounds to run the simulation)
     */
    private final int numberOfYears;
    /**
     * Santa's initial budget (used in round 0)
     */
    private final Double initialSantaBudget;
    /**
     * List of children from initialData
     */
    private final List<Child> initialChildrenList = new ArrayList<>();
    /**
     * List of gifts from initialData
     */
    private final List<GiftType> initialSantaGiftsList = new ArrayList<>();
    /**
     * List of annual changes (updates for each round)
     */
    private final List<AnnualChange> annualChangesList = new ArrayList<>();

    /**
     * The constructor makes copies of the lists given as Input
     */
    public Database(final Input input) {
        this.numberOfYears = input.getNumberOfYears();
        this.initialSantaBudget = input.getSantaBudget();

        // Build the list of Child objects based on the list of ChildInputData objects
        for (ChildInputData child : input.getInitialChildrenList()) {
            List<Category> giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
            List<Double> niceScores = new ArrayList<>();
            niceScores.add(child.getNiceScore());

            Child newChild = new Child(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getAge(), child.getAgeCategory(), child.getCity(),
                    giftsPreferences, niceScores, child.getNiceScoreBonus(), child.getElfType());
            // Set the averageScore Strategy for this Child
            newChild.setAverageScoreStrategy();

            initialChildrenList.add(newChild);
        }

        // Build the list of Gift objects
        for (GiftInputData gift : input.getInitialSantaGiftsList()) {
            initialSantaGiftsList.add(new GiftType(gift.getProductName(), gift.getPrice(),
                    gift.getCategory(), gift.getQuantity()));
        }

        // Build the list of AnnualChange objects
        for (AnnualChangesData annualChange : input.getAnnualChangesList()) {
            List<Child> newChildrenList = new ArrayList<>();
            List<GiftType> newGiftsList = new ArrayList<>();

            for (ChildInputData child : annualChange.getNewChildrenList()) {
                List<Category> giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
                List<Double> niceScores = new ArrayList<>();
                niceScores.add(child.getNiceScore());

                Child newChild = new Child(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getAge(), child.getAgeCategory(),
                        child.getCity(), giftsPreferences, niceScores, child.getNiceScoreBonus(),
                        child.getElfType());
                // Set the averageScore Strategy for this Child
                newChild.setAverageScoreStrategy();

                newChildrenList.add(newChild);
            }

            for (GiftInputData gift : annualChange.getNewGiftsList()) {
                newGiftsList.add(new GiftType(gift.getProductName(), gift.getPrice(),
                        gift.getCategory(), gift.getQuantity()));
            }

            DistributionStrategyFactory strategyFactory = new DistributionStrategyFactory();

            annualChangesList.add(new AnnualChange(annualChange.getNewSantaBudget(),
                    newGiftsList, newChildrenList, annualChange.getChildrenUpdates(),
                    strategyFactory.createStrategy(annualChange.getDistributionStrategy())));
        }
    }

    /**
     * Method to get the unique instance of this class
     */
    public static Database getInstance(final Input input) {
        if (instance == null) {
            instance = new Database(input);
        }
        return instance;
    }

    /**
     * Use this method to clear the current Database before running next Test
     */
    public void clearDatabase() {
        initialChildrenList.clear();
        initialSantaGiftsList.clear();
        annualChangesList.clear();
        instance = null;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public Double getInitialSantaBudget() {
        return initialSantaBudget;
    }

    public List<Child> getInitialChildrenList() {
        return initialChildrenList;
    }

    public List<GiftType> getInitialSantaGiftsList() {
        return initialSantaGiftsList;
    }

    public List<AnnualChange> getAnnualChangesList() {
        return annualChangesList;
    }
}
