package database;

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
    private final List<Gift> initialSantaGiftsList = new ArrayList<>();
    /**
     * List of annual changes (updates for each round)
     */
    private final List<AnnualChange> annualChangesList = new ArrayList<>();

    /**
     * This class is a Singleton
     * The constructor makes copies of the lists given as Input
     */
    private Database(final Input input) {
        this.numberOfYears = input.getNumberOfYears();
        this.initialSantaBudget = input.getSantaBudget();

        for (ChildInputData child : input.getInitialChildrenList()) {
            List<Category> giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
            List<Double> niceScores = new ArrayList<>();
            niceScores.add(child.getNiceScore());

            Child newChild = new Child(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getAge(), child.getAgeCategory(), child.getCity(),
                    giftsPreferences, niceScores);
            // Set the averageScore Strategy for this Child
            newChild.setAverageScoreStrategy();

            initialChildrenList.add(newChild);
        }

        for (GiftInputData gift : input.getInitialSantaGiftsList()) {
            initialSantaGiftsList.add(new Gift(gift.getProductName(), gift.getPrice(),
                    gift.getCategory()));
        }

        for (AnnualChangesData annualChange : input.getAnnualChangesList()) {
            List<Child> newChildrenList = new ArrayList<>();
            List<Gift> newGiftsList = new ArrayList<>();

            for (ChildInputData child : annualChange.getNewChildrenList()) {
                List<Category> giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
                List<Double> niceScores = new ArrayList<>();
                niceScores.add(child.getNiceScore());

                Child newChild = new Child(child.getId(), child.getLastName(),
                        child.getFirstName(), child.getAge(), child.getAgeCategory(),
                        child.getCity(), giftsPreferences, niceScores);
                // Set the averageScore Strategy for this Child
                newChild.setAverageScoreStrategy();

                newChildrenList.add(newChild);
            }

            for (GiftInputData gift : annualChange.getNewGiftsList()) {
                newGiftsList.add(new Gift(gift.getProductName(), gift.getPrice(),
                        gift.getCategory()));
            }

            annualChangesList.add(new AnnualChange(annualChange.getNewSantaBudget(),
                    newGiftsList, newChildrenList, annualChange.getChildrenUpdates()));
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

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public Double getInitialSantaBudget() {
        return initialSantaBudget;
    }

    public List<Child> getInitialChildrenList() {
        return initialChildrenList;
    }

    public List<Gift> getInitialSantaGiftsList() {
        return initialSantaGiftsList;
    }

    public List<AnnualChange> getAnnualChangesList() {
        return annualChangesList;
    }
}
