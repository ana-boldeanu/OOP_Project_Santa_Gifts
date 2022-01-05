package fileio;

import enums.Category;

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
}
