package database;

import enums.Category;

public class ReceivedGift {
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

    public ReceivedGift(String productName, Double price, Category category) {
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
