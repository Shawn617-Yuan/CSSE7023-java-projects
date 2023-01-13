package pizza.ingredients;

import java.util.ArrayList;
import java.util.List;

/**
 * The Topping class represents possible toppings that can be placed on a Pizza.
 */
public class Topping {
    /**
     * The price of the topping is 2.0.
     */
    public static final double PRICE = 2.0;

    /**
     * The toppings have specified before
     */
    private static final List<Topping> TOPPINGS = new ArrayList<>();

    /**
     * The name of the topping
     */
    private String name;

    /**
     * If the topping is vegan or not
     */
    private boolean isVegan;

    /**
     * Private constructor to create a new topping with the specified name and vegan boolean state.
     *
     * @param name    name of the topping
     * @param isVegan if the toppping is vegan or not
     */
    private Topping(String name, boolean isVegan) {
        this.name = name;
        this.isVegan = isVegan;
    }

    /**
     * Creates a new topping with the specified name and vegan boolean state.
     *
     * @param name    name of the topping
     * @param isVagan if the toppping is vegan or not
     * @throws IllegalArgumentException if name == `null` or topping with that name has already
     * been created or name.isBlank() is true
     */
    public static void createTopping(String name, boolean isVagan)
            throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }

        for (Topping topping : TOPPINGS) {
            if (topping.name.equals(name.toUpperCase())) {
                throw new IllegalArgumentException();
            }
        }
        TOPPINGS.add(new Topping(name.toUpperCase(), isVagan));
    }

    /**
     * Returns a Topping that has previously been defined by this class with the specified name.
     *
     * @param name the name of the topping to be returned.
     * @return the topping with the specified name
     * @throws IllegalArgumentException if this class has no topping with the specified name
     * @throws NullPointerException     if the argument is null
     */
    public static Topping valueOf(String name)
            throws IllegalArgumentException, NullPointerException {
        if (name == null) {
            throw new NullPointerException();
        }

        for (Topping topping : TOPPINGS) {
            if (topping.name.equals(name.toUpperCase())) {
                return topping;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Returns an array containing the Topping's that have been defined by this class,
     * in the order they are declared.
     *
     * @return an array containing the topping's that have been defined by this class,
     *          in the order they are declared.
     */
    public static Topping[] values() {
        Topping[] toppings = new Topping[TOPPINGS.size()];
        for (int i = 0; i < TOPPINGS.size(); i++) {
            toppings[i] = TOPPINGS.get(i);
        }
        return toppings;
    }

    /**
     * Resets Topping such that values() returns an empty Topping[].
     */
    public static void resetToppings() {
        TOPPINGS.clear();
    }

    /**
     * Returns the topping's name
     *
     * @return topping name
     */
    public String toString() {
        return name;
    }

    /**
     * Returns the vegan boolean property value
     *
     * @return boolean for vegan friendly item
     */
    public boolean isVegan() {
        return isVegan;
    }
}
