package pizza.ingredients;

/**
 * Sauce interface for managing the types of sauces available
 */
public interface Sauces {
    /**
     * Set (Sauce) utilises an enum with all the types of Sauce available for the Sauce Type.
     * @param sauce enum SauceType depicting the sauces of each Pizza sauce type
     */
    void set(Sauces.Sauce sauce);

    /**
     * enum containing the sauces available for the pizza sauce type。
     */
    enum Sauce {
        /**
         * The classic BBQ sauce
         */
        BBQ,
        /**
         * A strong smelling garlic sauce
         */
        GARLIC,
        /**
         * A special option to represent no sauce
         */
        NONE,
        /**
         * A rich tomato sauce
         */
        TOMATO
    }
}
