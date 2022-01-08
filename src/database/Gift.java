package database;

import enums.Category;

public final class Gift {
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

    public Gift(final String productName, final Double price, final Category category) {
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
        return "Gift{" + "productName='" + productName + '\'' + ", price=" + price
                + ", category=" + category + '}';
    }
}
