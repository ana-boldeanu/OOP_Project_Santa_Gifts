package fileio;

import common.Constants;
import database.ChildUpdateData;
import enums.Category;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class reads and parses information from the input tests and stores it in Input.
 * Do not modify.
 */
public final class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Method that reads from the input test
     * @return an Input object
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();

        // Data to be read
        int numberOfYears = 0;
        double santaBudget = 0;
        List<ChildInputData> initialChildrenList = new ArrayList<>();
        List<GiftInputData> initialSantaGiftsList = new ArrayList<>();
        List<AnnualChangesData> annualChangesList = new ArrayList<>();

        try {
            // Parse the contents of the JSON file
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));

            // Read values for numberOfYears and santaBudget
            numberOfYears = Integer.parseInt(jsonObject.get(Constants.NUMBER_OF_YEARS).toString());
            santaBudget = Double.parseDouble(jsonObject.get(Constants.SANTA_BUDGET).toString());

            JSONObject jsonInitialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
            JSONArray jsonChildren = (JSONArray) jsonInitialData.get(Constants.CHILDREN);
            JSONArray jsonGifts = (JSONArray) jsonInitialData.get(Constants.SANTA_GIFTS);
            JSONArray jsonAnnualChanges = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);

            // Build initialChildrenList
            if (jsonChildren != null) {
                for (Object jsonChild : jsonChildren) {
                    List<Category> giftsPreferences = new ArrayList<>();
                    JSONArray jsonGiftsPreferences =
                            (JSONArray) ((JSONObject) jsonChild).get(Constants.GIFTS_PREFERENCES);

                    if (jsonGiftsPreferences != null) {
                        for (Object jsonGift : jsonGiftsPreferences) {
                            giftsPreferences.add(Utils.stringToCategory(jsonGift.toString()));
                        }
                    }

                    initialChildrenList.add(new ChildInputData(
                            Integer.parseInt(((JSONObject) jsonChild).get(Constants.ID).toString()),
                            ((JSONObject) jsonChild).get(Constants.LAST_NAME).toString(),
                            ((JSONObject) jsonChild).get(Constants.FIRST_NAME).toString(),
                            Integer.parseInt(((JSONObject) jsonChild)
                                    .get(Constants.AGE).toString()),
                            ((JSONObject) jsonChild).get(Constants.CITY).toString(),
                            Double.parseDouble(((JSONObject) jsonChild)
                                    .get(Constants.NICE_SCORE).toString()),
                            giftsPreferences));
                }
            }

            // Build initialSantaGiftsList
            if (jsonGifts != null) {
                for (Object jsonGift : jsonGifts) {
                    initialSantaGiftsList.add(new GiftInputData(
                            ((JSONObject) jsonGift).get(Constants.PRODUCT_NAME).toString(),
                            Double.parseDouble(((JSONObject) jsonGift)
                                    .get(Constants.PRICE).toString()),
                            Utils.stringToCategory(((JSONObject) jsonGift)
                                    .get(Constants.CATEGORY).toString())));
                }
            }

            // Build annualChangesList
            if (jsonAnnualChanges != null) {
                for (Object jsonAnnualChange : jsonAnnualChanges) {
                    double newSantaBudget = Double.parseDouble(((JSONObject) jsonAnnualChange)
                            .get(Constants.NEW_SANTA_BUDGET).toString());
                    List<GiftInputData> newGiftsList = new ArrayList<>();
                    List<ChildInputData> newChildrenList = new ArrayList<>();
                    List<ChildUpdateData> childrenUpdates = new ArrayList<>();

                    // Data in each annualChange
                    JSONArray jsonNewGifts =
                            (JSONArray) ((JSONObject) jsonAnnualChange).get(Constants.NEW_GIFTS);
                    JSONArray jsonNewChildren =
                            (JSONArray) ((JSONObject) jsonAnnualChange).get(Constants.NEW_CHILDREN);
                    JSONArray jsonChildrenUpdates =
                            (JSONArray) ((JSONObject) jsonAnnualChange)
                                    .get(Constants.CHILDREN_UPDATES);

                    // Build newGiftsList for current annualChange
                    if (jsonNewGifts != null) {
                        for (Object jsonNewGift : jsonNewGifts) {
                            newGiftsList.add(new GiftInputData(
                                    ((JSONObject) jsonNewGift)
                                            .get(Constants.PRODUCT_NAME).toString(),
                                    Double.parseDouble(((JSONObject) jsonNewGift)
                                            .get(Constants.PRICE).toString()),
                                    Utils.stringToCategory(((JSONObject) jsonNewGift)
                                            .get(Constants.CATEGORY).toString())));
                        }
                    }

                    // Build newChildrenList for current annualChange
                    if (jsonNewChildren != null) {
                        for (Object jsonNewChild : jsonNewChildren) {
                            List<Category> giftsPreferences = new ArrayList<>();
                            JSONArray jsonGiftsPreferences =
                                    (JSONArray) ((JSONObject) jsonNewChild)
                                            .get(Constants.GIFTS_PREFERENCES);

                            if (jsonGiftsPreferences != null) {
                                for (Object jsonGift : jsonGiftsPreferences) {
                                    giftsPreferences
                                            .add(Utils.stringToCategory(jsonGift.toString()));
                                }
                            }

                            newChildrenList.add(new ChildInputData(
                                    Integer.parseInt(((JSONObject) jsonNewChild)
                                            .get(Constants.ID).toString()),
                                    ((JSONObject) jsonNewChild)
                                            .get(Constants.LAST_NAME).toString(),
                                    ((JSONObject) jsonNewChild)
                                            .get(Constants.FIRST_NAME).toString(),
                                    Integer.parseInt(((JSONObject) jsonNewChild)
                                            .get(Constants.AGE).toString()),
                                    ((JSONObject) jsonNewChild)
                                            .get(Constants.CITY).toString(),
                                    Double.parseDouble(((JSONObject) jsonNewChild)
                                            .get(Constants.NICE_SCORE).toString()),
                                    giftsPreferences));
                        }
                    }

                    // Build childrenUpdates for current annualChange
                    if (jsonChildrenUpdates != null) {
                        for (Object jsonChildUpdate : jsonChildrenUpdates) {
                            List<Category> newGiftsPreferences = new ArrayList<>();
                            JSONArray jsonGiftsPreferences =
                                    (JSONArray) ((JSONObject) jsonChildUpdate)
                                            .get(Constants.GIFTS_PREFERENCES);

                            if (jsonGiftsPreferences != null) {
                                for (Object jsonGift : jsonGiftsPreferences) {
                                    newGiftsPreferences
                                            .add(Utils.stringToCategory(jsonGift.toString()));
                                }
                            }

                            Double newNiceScore = null;
                            Object jsonNiceScore = ((JSONObject) jsonChildUpdate)
                                    .get(Constants.NICE_SCORE);
                            if (jsonNiceScore != null) {
                                newNiceScore = Double.parseDouble(jsonNiceScore.toString());
                            }

                            childrenUpdates.add(new ChildUpdateData(
                                    Integer.parseInt(((JSONObject) jsonChildUpdate)
                                            .get(Constants.ID).toString()),
                                    newNiceScore, newGiftsPreferences));
                        }
                    }

                    annualChangesList.add(new AnnualChangesData(newSantaBudget, newGiftsList,
                            newChildrenList, childrenUpdates));
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfYears, santaBudget, initialChildrenList, initialSantaGiftsList,
                annualChangesList);
    }
}
