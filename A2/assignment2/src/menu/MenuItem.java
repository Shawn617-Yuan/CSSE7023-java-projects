package menu;

/**
 * Represents an item that can be ordered from the menu.
 */
public interface MenuItem {
    /**
     * Registers this item with the Menu singleton class.
     */
    default void registerMenuItem() {
        Menu.getInstance().registerMenuItem(this);
    }

    /**
     * Returns the price of a menu item.
     *
     * @return the price of the menu item
     */
    double getTotalPrice();

    /**
     * Returns the name of this menu item.
     *
     * @return name of this menu item
     */
    String getName();

    /**
     * A functional interface to allow a lambda function to be passed to an Order.
     */
    public static interface Discount {
        /**
         * Apply a discount to the given price.
         *
         * @param price the input price
         * @return the discounted price
         */
        double applyDiscount(double price);
    }
}
