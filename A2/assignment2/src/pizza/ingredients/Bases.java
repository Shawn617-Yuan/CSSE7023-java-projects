package pizza.ingredients;

/**
 * Base interface for managing the types of base sizes available
 */
public interface Bases {
    /**
     * Set (Bases) utilises an enum with all the types of Bases available for the Bases Type.
     *
     * @param size enum Type depicting the sizes of each Pizza base type
     */
    void set(Bases.BaseSize size);

    /**
     * enum containing the sizes available for the pizza Bases type
     */
    enum BaseSize {
        /**
         * LARGE base size, $7
         */
        LARGE(7),
        /**
         * MEDIUM base size, $5
         */
        MEDIUM(5),
        /**
         * SMALL base size, $3
         */
        SMALL(3);

        /**
         * price of the base
         */
        private double price;

        /**
         * create pizza base size
         *
         * @param price price of the base
         */
        BaseSize(double price) {
            this.price = price;
        }

        /**
         * Returns the price of this base
         *
         * @return the price of the base
         */
        public double getPrice() {
            return this.price;
        }
    }
}
