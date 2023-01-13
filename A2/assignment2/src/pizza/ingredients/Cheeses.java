package pizza.ingredients;

/**
 * Cheeses interface for managing the types of Cheese available
 */
public interface Cheeses {
    /**
     * Set (Cheese) utilises an enum with all the types of Cheese available for the Cheese Type.
     *
     * @param cheese enum Type depicting the cheeses of each Pizza
     */
    void set(Cheeses.Cheese cheese);

    /**
     * enum containing the cheeses available for the pizza cheese type
     */
    enum Cheese {
        /**
         * Classic shredded Mozzarella cheese
         */
        MOZZARELLA,
        /**
         * Vegan friendly cheese
         */
        VEGAN,
        /**
         * A special option to represent no cheese
         */
        NONE
    }
}
