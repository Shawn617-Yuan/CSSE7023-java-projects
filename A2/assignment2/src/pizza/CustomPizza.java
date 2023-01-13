package pizza;

import exceptions.TooManyToppingsException;
import pizza.ingredients.Topping;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Pizza allows the addition of extra toppings for a more delicious Pizza.
 */
public class CustomPizza extends Pizza {
    /**
     * Default constructor which creates a medium cheese pizza.
     */
    public CustomPizza() {
        super();
        super.setName("Custom Pizza");
    }

    /**
     * Creating the default requirements of a pizza with no additional toppings.
     *
     * @param size   The size of the pizza base as defined by Bases
     * @param sauce  The sauce on the pizza as defined by Sauces
     * @param cheese The cheese on the pizza as defined by Cheeses
     * @throws IllegalArgumentException if size, sauce or cheese is null.
     */
    public CustomPizza(BaseSize size, Sauce sauce, Cheese cheese) throws IllegalArgumentException {
        super(size, sauce, cheese);
        super.setName("Custom Pizza");
    }

    /**
     * The add method allows toppings to be added to the pizza, limited to the maximum permissible
     * amount of 5.
     *
     * @param toppings list of toppings to be added to the pizza
     * @throws TooManyToppingsException if adding the new toppings causes the number of toppings to
     *                                  exceed the limit of 5
     * @throws IllegalArgumentException if toppings is null
     */
    public void add(List<Topping> toppings)
            throws TooManyToppingsException, IllegalArgumentException {
        if (toppings == null) {
            throw new IllegalArgumentException();
        }

        if (toppings.size() + super.getToppings().size() > 5) {
            throw new TooManyToppingsException("Topping exceeds 5!");
        } else {
            for (int i = 0; i < toppings.size(); i++) {
                super.accessToppings().add(toppings.get(i));
            }
        }
    }

    /**
     * The add method allows a single topping to be added to the pizza, limited to the maximum
     * permissible amount of 5.
     *
     * @param topping topping to be added to the pizza
     * @throws TooManyToppingsException if adding the new toppings causes the number of toppings to
     *                                  exceed the limit of 5
     * @throws IllegalArgumentException if topping is null
     */
    public void add(Topping topping) throws TooManyToppingsException, IllegalArgumentException {
        if (super.getToppings().size() == 5) {
            throw new TooManyToppingsException("Topping exceeds 5");
        }

        if (topping == null) {
            throw new IllegalArgumentException();
        }

        super.accessToppings().add(topping);
    }

    /**
     * The remove method removes the first occurrence of the specified topping from this pizza,
     * if it is present.
     *
     * @param topping topping to be removed from the pizza
     */
    public void remove(Topping topping) {
        super.accessToppings().remove(topping);
    }
}
