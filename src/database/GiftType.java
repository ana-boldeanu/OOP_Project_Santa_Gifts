package database;

import enums.Category;

/**
 * General information about a Gift
 */
public final class GiftType {
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
    private int quantity;

    public GiftType(final String productName, final Double price, final Category category,
                    final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    /**
     * Decrease the quantity of this GiftType by 1
     */
    public void decreaseQuantity() {
        quantity--;
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

    @Override
    public String toString() {
        return "Gift{" + "productName='" + productName + '\'' + ", price=" + price
                + ", category=" + category + ", quantity=" + quantity + '}';
    }
}
