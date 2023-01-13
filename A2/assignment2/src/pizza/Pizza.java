package pizza;

import exceptions.TooManyToppingsException;
import menu.Menu;
import menu.MenuItem;
import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Pizza combines the required basic elements of a pizza, being the base, sauce and cheese,
 * and 5 additional toppings.
 */
public abstract class Pizza implements Bases, Sauces, Cheeses, MenuItem {
    /**
     * The maximum number of toppings that can be placed on a pizza. (5)
     */
    public static final int MAX_TOPPINGS = 5;

    /**
     * The size of the pizza base as defined by Bases
     */
    private BaseSize size;
    /**
     * The sauce on the pizza as defined by Sauces
     */
    private Sauce sauce;
    /**
     * The cheese on the pizza as defined by Cheeses
     */
    private Cheese cheese;
    /**
     * The list of toppings on the Pizza, List of Toppings
     */
    private List<Topping> toppings;
    /**
     * The name of the pizza
     */
    private String name;

    /**
     * Default constructor which creates a medium cheese pizza with tomato sauce.
     */
    public Pizza() {
        this.size = BaseSize.MEDIUM;
        this.sauce = Sauce.TOMATO;
        this.cheese = Cheese.MOZZARELLA;
        this.toppings = new ArrayList<>();
        this.name = "Dr Java's Pizza";
        this.registerMenuItem();
    }

    /**
     * Creating the base requirements of the pizza with no toppings.
     *
     * @param size   The size of the pizza base as defined by Bases
     * @param sauce  The sauce on the pizza as defined by Sauces
     * @param cheese The cheese on the pizza as defined by Cheeses
     * @throws IllegalArgumentException if size, sauce or cheese is null.
     */
    public Pizza(BaseSize size, Sauce sauce, Cheese cheese) throws IllegalArgumentException {
        if (size == null || sauce == null || cheese == null) {
            throw new IllegalArgumentException();
        }

        this.size = size;
        this.sauce = sauce;
        this.cheese = cheese;
        this.toppings = new ArrayList<>();
        this.name = "Dr Java's Pizza";
        this.registerMenuItem();
    }

    /**
     * Creating a Pizza with a set base size, sauce, cheese and list of toppings.
     *
     * @param size     The size of the pizza base as defined by Bases
     * @param sauce    The sauce on the pizza as defined by Sauces
     * @param cheese   The cheese on the pizza as defined by Cheeses
     * @param toppings The list of toppings on the Pizza, List of Toppings
     * @throws TooManyToppingsException if toppings.size() > 5
     * @throws IllegalArgumentException if size, sauce, cheese or toppings is null.
     */
    public Pizza(BaseSize size, Sauce sauce, Cheese cheese, List<Topping> toppings) throws
            TooManyToppingsException, IllegalArgumentException {
        if (size == null || sauce == null || cheese == null || Objects.equals(toppings, null)) {
            throw new IllegalArgumentException();
        }

        if (toppings.size() > MAX_TOPPINGS) {
            throw new TooManyToppingsException("Toppings' size exceeds 5.");
        }


        this.size = size;
        this.sauce = sauce;
        this.cheese = cheese;
        this.toppings = toppings;
        this.name = "Dr Java's Pizza";
        this.registerMenuItem();
    }

    /**
     * Returns the list of toppings that are on this pizza.
     *
     * @return the list of toppings on this pizza.
     */
    public List<Topping> getToppings() {
        return new ArrayList<>(toppings);
    }

    /**
     * Returns the price of the pizza base size, defined in the BaseSize enum, and adds the price
     * of each topping on the pizza.
     *
     * @return the price of the pizza.
     */
    public double getTotalPrice() {
        return this.size.getPrice() + Topping.PRICE * toppings.size();
    }

    /**
     * Returns the name of this pizza.
     *
     * @return a String name of this pizza
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the pizza.
     *
     * @param name String providing a replacement name of the pizza
     * @throws IllegalArgumentException if name is null or name.isBlank().
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }

        this.name = name;
    }

    /**
     * Set the size of this pizza base.
     *
     * @param size the size of this pizza base.
     */
    public void set(Bases.BaseSize size) {
        this.size = size;
    }

    /**
     * Set the sauce on this pizza.
     *
     * @param sauce the sauce on this pizza.
     */
    public void set(Sauces.Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * Set the cheese on this pizza.
     *
     * @param cheese the cheese on this pizza.
     */
    public void set(Cheeses.Cheese cheese) {
        this.cheese = cheese;
    }

    /**
     * Returns the hash code of this pizza.
     *
     * @return hash code of this pizza.
     */
    @Override
    public int hashCode() {
        int toppingHashCode = 0;
        for (int i = 0; i < toppings.size(); i++) {
            toppingHashCode += toppings.get(i).hashCode();
        }
        return 6 * size.hashCode() + 9 * sauce.hashCode() + 7 * cheese.hashCode()
                + 8 * toppingHashCode;
    }

    /**
     * Returns true if and only if this pizza is equal to the other given object.
     *
     * @param other the reference object with which to compare
     * @return true if this pizza is the same as the other argument; false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        Pizza pizza = (Pizza) other;
        return this.hashCode() == pizza.hashCode();
    }

    /**
     * Returns the human-readable string representation of this Pizza.
     *
     * @return string representation of this Pizza
     */
    @Override
    public String toString() {
        String outputWithoutToppings = String.format("%s: is a '%s' sized base with '%s' sauce "
                + "and '%s' cheese ", name, size.toString(), sauce.toString(), cheese.toString());

        if (toppings.isEmpty()) {
            outputWithoutToppings += String.format("$%.2f", this.getTotalPrice());
            return outputWithoutToppings;
        } else {
            // to get the toString for toppings if there is toppings
            String toppings = "";
            for (int i = 0; i < getToppings().size(); i++) {
                if (i != getToppings().size() - 1) {
                    toppings += getToppings().get(i).toString();
                    toppings += ", ";
                } else {
                    toppings += getToppings().get(i).toString();
                }
            }
            String outputWithToppings = String.format("%s- Toppings: [%s]",
                    outputWithoutToppings, toppings);
            outputWithToppings += String.format(" $%.2f", this.getTotalPrice());
            return outputWithToppings;
        }
    }

    /**
     * Returns the list of toppings that are on this pizza.
     *
     * @return the list of toppings on this pizza.
     */
    protected List<Topping> accessToppings() {
        return toppings;
    }
}
