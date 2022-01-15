package fileio;

import enums.Category;
import enums.Cities;
import enums.ElvesType;

/**
 * Utility Class
 */
public final class Utils {
    private Utils() { }

    /**
     * Convert a String value to a Category Enum
     */
    public static Category stringToCategory(final String category) {
        return switch (category) {
            case "Board Games" -> Category.BOARD_GAMES;
            case "Books" -> Category.BOOKS;
            case "Clothes" -> Category.CLOTHES;
            case "Sweets" -> Category.SWEETS;
            case "Technology" -> Category.TECHNOLOGY;
            case "Toys" -> Category.TOYS;
            default -> null;
        };
    }

    /**
     * Convert a String value to an ElvesType Enum
     */
    public static ElvesType stringToElves(final String elf) {
        return switch (elf) {
            case "yellow" -> ElvesType.YELLOW;
            case "black" -> ElvesType.BLACK;
            case "pink" -> ElvesType.PINK;
            case "white" -> ElvesType.WHITE;
            default -> null;
        };
    }
}
