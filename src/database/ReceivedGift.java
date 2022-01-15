package database;

import enums.Category;

/**
 * General information about a Gift that a Child can receive (it's the same as GiftType, but it
 * doesn't contain the quantity field)
 */
public final class ReceivedGift {
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

    public ReceivedGift(final String productName, final Double price, final Category category) {
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

    @Override
    public String toString() {
        return "ReceivedGift{" + "productName='" + productName + '\'' + ", price=" + price
                + ", category=" + category + '}';
    }
}
