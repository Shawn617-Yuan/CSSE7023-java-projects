package io;

import exceptions.PizzaFormatException;
import exceptions.TooManyToppingsException;
import menu.Menu;
import pizza.CustomPizza;
import pizza.MenuPizza;
import pizza.Pizza;
import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class to provide management for the loading, saving and parsing of text data retrieved from
 * the PizzaMenu.txt file, stored in the assets package.
 */
public class MenuLoader {
    /**
     * provides folder location for text-file PizzaMenu.txt, this can be later improved to search
     * for the file instead of hard coding.
     */
    public static final String PATH = "./src/assets/";
    /**
     * exit integer code indicating could not open file
     */
    private static final int COULD_NOT_OPEN_FILE = 1;
    /**
     * exit integer code indicating file format error
     */
    private static final int FILE_FORMAT_ERROR = 2;
    /**
     * exit integer code indicating too many toppings
     */
    private static final int TOO_MANY_TOPPINGS = 4;
    /**
     * exit integer code indicating missing number of pizzas
     */
    private static final int MISSING_NUMBER_OF_PIZZAS = 5;
    /**
     * exit integer code indicating cannot read line
     */
    private static final int CANNOT_READ_LINE = 6;

    /**
     * Inherited default constructor, not used in this class
     */
    public MenuLoader() {
    }

    /**
     * Creates a BufferedReader, utilizing a FileReader to be parsed by the getMenu method.
     *
     * @param filename String representing the file to be read
     * @return a parsed Menu type containing the list of Pizzas found in the menu text file.
     */
    public static Menu load(String filename) {
        try {
            FileReader fileReader = new FileReader(PATH + filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return getMenu(bufferedReader);
        } catch (FileNotFoundException fnf) {
            System.exit(COULD_NOT_OPEN_FILE);
        } catch (PizzaFormatException pf) {
            System.exit(FILE_FORMAT_ERROR);
        } catch (TooManyToppingsException tt) {
            System.exit(TOO_MANY_TOPPINGS);
        } catch (IOException io) {
            System.exit(CANNOT_READ_LINE);
        } catch (IndexOutOfBoundsException ioob) {
            System.exit(MISSING_NUMBER_OF_PIZZAS);
        }
        return null;
    }

    /**
     * Used by the load method to manage the parsing of the loaded data.
     *
     * @param reader BufferedReader used to read file
     * @return Menu item that has loaded all the pizza's from the file
     * @throws PizzaFormatException      if the given reader is `null` or empty, if the name on the
     *                                   first line is not "PizzaMenu", if the space is missing
     *                                   after the name, if the number of pizza's can not be
     *                                   parsed, if a blank line does not follow the first line,
     *                                   if a topping line contains an invalid topping name, if a
     *                                   blank line does not follow the vegan topping line, if a
     *                                   pizza line contains an invalid topping (such that, it was
     *                                   not mentioned in any topping line).
     * @throws TooManyToppingsException  if a pizza line has too many toppings.
     * @throws IOException               if an error occurs when trying to read a line.
     * @throws IndexOutOfBoundsException if the number of pizza lines given in the first line does
     *                                   not match the number of pizza lines present in the file.
     */
    public static Menu getMenu(BufferedReader reader) throws PizzaFormatException,
            TooManyToppingsException, IOException, IndexOutOfBoundsException {
        if (reader == null || Objects.equals(reader, "")) {
            throw new PizzaFormatException("given reader is `null` or empty",
                    new Throwable().getStackTrace()[0].getLineNumber());
        }

        String line = reader.readLine();
        if (!line.contains("PizzaMenu")) {
            throw new PizzaFormatException("name on first line is not PizzaMenu",
                    new Throwable().getStackTrace()[0].getLineNumber());
        }

        String[] menuItem = line.split(" ");
        int pizzaNum;
        try {
            pizzaNum = Integer.parseInt(menuItem[1]);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new PizzaFormatException("the space is missing after name",
                    new Throwable().getStackTrace()[0].getLineNumber(), e);
        }

        String line2 = reader.readLine();
        readEmptyLine(line2);

        // load the non-vegan and vegan topping, throw right exception if there is other exception
        try {
            String line3 = reader.readLine();
            String[] nonVeganToppings = line3.split(", ");

            for (String nonVeganTopping : nonVeganToppings) {
                Topping.createTopping(nonVeganTopping, false);
            }

            String line4 = reader.readLine();
            String[] veganToppings = line4.split(", ");

            for (String veganTopping : veganToppings) {
                Topping.createTopping(veganTopping, true);
            }
        } catch (IllegalArgumentException iae) {
            throw new PizzaFormatException("a topping line contains an invalid topping name",
                    new Throwable().getStackTrace()[0].getLineNumber(), iae);
        }

        String line5 = reader.readLine();
        readEmptyLine(line5);

        // load the pizza
        for (int i = 0; i < pizzaNum; i++) {
            line = reader.readLine();
            if (line != null) {
                String[] pizzaInfo = line.split(" \\[");
                Pizza pizza = loadPizza(pizzaInfo[0], pizzaInfo[1]);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        // throw exception when the pizza number is not matched
        line = reader.readLine();
        if (line != null) {
            throw new IndexOutOfBoundsException();
        }

        return Menu.getInstance();
    }

    /**
     * Check whether this line is an empty line
     *
     * @param line line to be checked
     * @throws PizzaFormatException this line is not an empty line
     */
    private static void readEmptyLine(String line) throws PizzaFormatException {
        if (line == null || !line.equals("")) {
            throw new PizzaFormatException("a blank line does not follow the previous line",
                    new Throwable().getStackTrace()[0].getLineNumber());
        }
    }

    /**
     * Load the pizza information
     *
     * @param name    pizza name
     * @param topping toppings of the pizza
     * @return pizza with modified name and topping
     * @throws TooManyToppingsException topping exceeds 5
     */
    private static Pizza loadPizza(String name, String topping)
            throws TooManyToppingsException {
        List<Topping> toppings = loadToppings(topping);

        if (name.contains("CustomPizza")) {
            CustomPizza pizza = new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO,
                    Cheeses.Cheese.MOZZARELLA);
            pizza.add(toppings);
            pizza.setName(name);
            return pizza;
        } else {
            MenuPizza pizza = new MenuPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO,
                    Cheeses.Cheese.MOZZARELLA, toppings);
            pizza.setName(name);
            return pizza;
        }

    }

    /**
     * Load toppings information
     *
     * @param toppingInfo String
     * @return a list of toppings
     */
    private static List<Topping> loadToppings(String toppingInfo) {
        List<Topping> toppings = new ArrayList<>();

        toppingInfo = toppingInfo.substring(0, toppingInfo.length() - 1);
        String[] toppingsCollection = toppingInfo.split(", ");
        for (String topping : toppingsCollection) {
            toppings.add(Topping.valueOf(topping));
        }

        return toppings;
    }
}
