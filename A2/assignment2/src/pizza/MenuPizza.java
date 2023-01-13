package pizza;

import exceptions.TooManyToppingsException;
import menu.MenuItem;
import pizza.ingredients.Topping;

import java.util.ArrayList;
import java.util.List;

/**
 * MenuPizza provides a selection for ordering pre-organised Pizzas from the menu.
 */
public class MenuPizza extends Pizza {
    /**
     * Creating a Menu Pizza with a set base size, sauce, cheese and list of toppings.
     *
     * @param size     The size of the pizza base as defined by Bases
     * @param sauce    The size of the pizza base as defined by Bases
     * @param cheese   The cheese on the pizza as defined by Cheeses
     * @param toppings The list of toppings on the Pizza, List of Toppings
     * @throws TooManyToppingsException if toppings.size() > 5
     * @throws IllegalArgumentException if size, sauce, cheese or toppings is null.
     */
    public MenuPizza(BaseSize size, Sauce sauce, Cheese cheese, List<Topping> toppings)
            throws TooManyToppingsException, IllegalArgumentException {
        super(size, sauce, cheese, toppings);
    }

    /**
     * Returns the human-readable string representation of this Menu Pizza.
     *
     * @return string representation of this Menu Pizza
     */
    @Override
    public String toString() {
        String output = String.format("[MenuPizza] %s", super.toString());
        return output;
    }
}
