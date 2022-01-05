package fileio;

import enums.Category;

/**
 * Input information about a Gift.
 * Do not modify.
 */
public final class GiftInputData {
    /**
     * Name of the gift
     */
    private final String productName;
    /**
     * Price
     */
    private final Double price;
    /**
     * Category
     */
    private final Category category;

    public GiftInputData(final String productName, final Double price, final Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
