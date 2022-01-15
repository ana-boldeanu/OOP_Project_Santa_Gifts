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
    /**
     * Quantity
     */
    private final int quantity;

    public GiftInputData(final String productName, final Double price, final Category category,
                         final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }
}
