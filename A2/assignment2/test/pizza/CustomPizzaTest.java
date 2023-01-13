package pizza;

import exceptions.TooManyToppingsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pizza.ingredients.Bases;
import pizza.ingredients.Cheeses;
import pizza.ingredients.Sauces;
import pizza.ingredients.Topping;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CustomPizzaTest {
    private CustomPizza customPizza1;
    @Before
    public void setUp() throws Exception {
        Topping.createTopping("onions",true);
        Topping.createTopping("bacon",false);
        Topping.createTopping("ham",false);

        this.customPizza1 = new CustomPizza();
    }

    @After
    public void tearDown() throws Exception {
        Topping.resetToppings();
    }

    @Test
    public void defaultSetUp() {
        assertTrue(customPizza1.equals(new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO, Cheeses.Cheese.MOZZARELLA)));
        assertEquals(customPizza1.getToppings(), new ArrayList<>());
        assertEquals(customPizza1.getToppings().size(), 0);
        assertEquals(customPizza1.getTotalPrice(), 5, 0.02);
        assertEquals(customPizza1.getName(), "Custom Pizza");
    }

    @Test (expected = IllegalArgumentException.class)
    public void specificSetUpWithNullBase() {
        CustomPizza customPizzaInvalid = new CustomPizza(null, Sauces.Sauce.BBQ, Cheeses.Cheese.VEGAN);
    }

    @Test (expected = IllegalArgumentException.class)
    public void specificSetUpWithNullSauce() {
        CustomPizza customPizzaInvalid = new CustomPizza(Bases.BaseSize.SMALL, null, Cheeses.Cheese.VEGAN);
    }

    @Test (expected = IllegalArgumentException.class)
    public void specificSetUpWithNullCheese() {
        CustomPizza customPizzaInvalid = new CustomPizza(Bases.BaseSize.SMALL, Sauces.Sauce.BBQ, null);
    }

    @Test
    public void specificSetUp() {
        CustomPizza customPizza2 = new CustomPizza(Bases.BaseSize.SMALL, Sauces.Sauce.BBQ, Cheeses.Cheese.VEGAN);

        assertTrue(customPizza2.equals(new CustomPizza(Bases.BaseSize.SMALL, Sauces.Sauce.BBQ, Cheeses.Cheese.VEGAN)));
        assertEquals(customPizza2.getToppings(), new ArrayList<>());
        assertEquals(customPizza2.getToppings().size(), 0);
        assertEquals(customPizza2.getTotalPrice(), 3, 0.02);
        assertEquals(customPizza2.getName(), "Custom Pizza");

        CustomPizza customPizza3 = new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.GARLIC, Cheeses.Cheese.MOZZARELLA);

        assertTrue(customPizza3.equals(new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.GARLIC, Cheeses.Cheese.MOZZARELLA)));
        assertEquals(customPizza3.getToppings(), new ArrayList<>());
        assertEquals(customPizza3.getToppings().size(), 0);
        assertEquals(customPizza3.getTotalPrice(), 5, 0.02);
        assertEquals(customPizza3.getName(), "Custom Pizza");

        CustomPizza customPizza4 = new CustomPizza(Bases.BaseSize.LARGE, Sauces.Sauce.NONE, Cheeses.Cheese.NONE);

        assertTrue(customPizza4.equals(new CustomPizza(Bases.BaseSize.LARGE, Sauces.Sauce.NONE, Cheeses.Cheese.NONE)));
        assertEquals(customPizza4.getToppings(), new ArrayList<>());
        assertEquals(customPizza4.getToppings().size(), 0);
        assertEquals(customPizza4.getTotalPrice(), 7, 0.02);
        assertEquals(customPizza4.getName(), "Custom Pizza");
    }

    @Test (expected = IllegalArgumentException.class)
    public void addSingleWithNull() throws TooManyToppingsException {
        Topping topping = null;
        customPizza1.add(topping);
    }

    @Test (expected = TooManyToppingsException.class)
    public void addSingleExceedMax() throws TooManyToppingsException {
        Topping topping1 = Topping.valueOf("onions");

        customPizza1.add(topping1);
        customPizza1.add(topping1);
        customPizza1.add(topping1);
        customPizza1.add(topping1);
        customPizza1.add(topping1);
        assertEquals(customPizza1.getToppings().size(), 5);
        assertEquals(customPizza1.getTotalPrice(), 15, 0.02);

        customPizza1.add(topping1);
    }

    @Test
    public void testSingleAdd() throws TooManyToppingsException {
        Topping topping1 = Topping.valueOf("onions");
        Topping topping2 = Topping.valueOf("bacon");
        Topping topping3 = Topping.valueOf("ham");

        customPizza1.add(topping1);
        assertEquals(customPizza1.getToppings(), new ArrayList<>(Arrays.asList(topping1)));
        assertEquals(customPizza1.getToppings().size(), 1);
        assertEquals(customPizza1.getTotalPrice(), 7, 0.02);

        customPizza1.add(topping2);
        assertEquals(customPizza1.getToppings(), new ArrayList<>(Arrays.asList(topping1, topping2)));
        assertEquals(customPizza1.getToppings().size(), 2);
        assertEquals(customPizza1.getTotalPrice(), 9, 0.02);

        customPizza1.add(topping3);
        assertEquals(customPizza1.getToppings(), new ArrayList<>(Arrays.asList(topping1, topping2, topping3)));
        assertEquals(customPizza1.getToppings().size(), 3);
        assertEquals(customPizza1.getTotalPrice(), 11, 0.02);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addMultiWithNull() throws TooManyToppingsException {
        List<Topping> toppings = null;
        customPizza1.add(toppings);
    }

    @Test (expected = TooManyToppingsException.class)
    public void addMultiExceedMax() throws TooManyToppingsException {
        Topping topping1 = Topping.valueOf("onions");
        Topping topping2 = Topping.valueOf("bacon");
        Topping topping3 = Topping.valueOf("ham");
        List<Topping> toppings = new ArrayList<>(Arrays.asList(topping1, topping2, topping3));

        customPizza1.add(toppings);
        assertEquals(customPizza1.getToppings(), new ArrayList<>(toppings));
        assertEquals(customPizza1.getToppings().size(), 3);
        assertEquals(customPizza1.getTotalPrice(), 11, 0.02);

        customPizza1.add(toppings);
    }

    @Test
    public void testMultiAdd() throws TooManyToppingsException {
        Topping topping1 = Topping.valueOf("onions");
        Topping topping2 = Topping.valueOf("bacon");
        Topping topping3 = Topping.valueOf("ham");
        List<Topping> toppings1 = new ArrayList<>(Arrays.asList(topping1, topping2));
        List<Topping> toppings2 = new ArrayList<>(Arrays.asList(topping2, topping3));


        customPizza1.add(toppings1);
        assertEquals(customPizza1.getToppings(), new ArrayList<>(toppings1));
        assertEquals(customPizza1.getToppings().size(), 2);
        assertEquals(customPizza1.getTotalPrice(), 9, 0.02);

        customPizza1.add(toppings2);
        assertEquals(customPizza1.getToppings(), new ArrayList<>(Arrays.asList(topping1, topping2, topping2, topping3)));
        assertEquals(customPizza1.getToppings().size(), 4);
        assertEquals(customPizza1.getTotalPrice(), 13, 0.02);
    }

    @Test
    public void remove() throws TooManyToppingsException {
        Topping topping1 = Topping.valueOf("onions");
        Topping topping2 = Topping.valueOf("bacon");
        Topping topping3 = Topping.valueOf("ham");
        List<Topping> toppings = new ArrayList<>(Arrays.asList(topping1, topping2, topping1, topping3, topping2));

        customPizza1.add(toppings);
        assertEquals(customPizza1.getToppings(), new ArrayList<>(toppings));
        assertEquals(customPizza1.getToppings().size(), 5);
        assertEquals(customPizza1.getTotalPrice(), 15, 0.02);

        customPizza1.remove(topping1);

        assertEquals(customPizza1.getToppings(), new ArrayList<>(Arrays.asList(topping2, topping1, topping3, topping2)));
        assertEquals(customPizza1.getToppings().size(), 4);
        assertEquals(customPizza1.getTotalPrice(), 13, 0.02);

        customPizza1.remove(topping2);

        assertEquals(customPizza1.getToppings(), new ArrayList<>(Arrays.asList(topping1, topping3, topping2)));
        assertEquals(customPizza1.getToppings().size(), 3);
        assertEquals(customPizza1.getTotalPrice(), 11, 0.02);
    }

    @Test
    public void getToppings() {
        List<Topping> toppings1 = customPizza1.getToppings();
        assertEquals(toppings1.size(), 0);
        assertEquals(toppings1, new ArrayList<>());

        toppings1.add(Topping.valueOf("ONIONS"));

        List<Topping> toppings2 = customPizza1.getToppings();
        assertEquals(toppings2.size(), 0);
        assertEquals(toppings2, new ArrayList<>());
    }

    @Test
    public void getTotalPrice() throws TooManyToppingsException {
        Topping topping1 = Topping.valueOf("ham");
        CustomPizza customPizza2 = new CustomPizza(Bases.BaseSize.LARGE, Sauces.Sauce.NONE, Cheeses.Cheese.NONE);
        assertEquals(customPizza2.getTotalPrice(), 7, 0.02);
        customPizza2.add(topping1);
        assertEquals(customPizza2.getTotalPrice(), 9, 0.02);
        customPizza2.add(topping1);
        assertEquals(customPizza2.getTotalPrice(), 11, 0.02);
        customPizza2.add(topping1);
        assertEquals(customPizza2.getTotalPrice(), 13, 0.02);
        customPizza2.add(topping1);
        assertEquals(customPizza2.getTotalPrice(), 15, 0.02);
        customPizza2.add(topping1);
        assertEquals(customPizza2.getTotalPrice(), 17, 0.02);

        CustomPizza customPizza3 = new CustomPizza(Bases.BaseSize.SMALL, Sauces.Sauce.NONE, Cheeses.Cheese.NONE);
        assertEquals(customPizza3.getTotalPrice(), 3, 0.02);
        customPizza3.add(topping1);
        assertEquals(customPizza3.getTotalPrice(), 5, 0.02);
        customPizza3.add(topping1);
        assertEquals(customPizza3.getTotalPrice(), 7, 0.02);
        customPizza3.add(topping1);
        assertEquals(customPizza3.getTotalPrice(), 9, 0.02);
        customPizza3.add(topping1);
        assertEquals(customPizza3.getTotalPrice(), 11, 0.02);
        customPizza3.add(topping1);
        assertEquals(customPizza3.getTotalPrice(), 13, 0.02);
    }

    @Test
    public void getName() {
        assertEquals(customPizza1.getName(), "Custom Pizza");
    }

    @Test (expected = IllegalArgumentException.class)
    public void setNameWithNull() {
        customPizza1.setName(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setNameWithBlank() {
        customPizza1.setName("");
    }

    @Test
    public void setName() {
        assertEquals(customPizza1.getName(), "Custom Pizza");
        customPizza1.setName("Test Pizza 1");
        assertEquals(customPizza1.getName(), "Test Pizza 1");
    }

    @Test
    public void setBase() {
        assertTrue(customPizza1.equals(new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO, Cheeses.Cheese.MOZZARELLA)));
        customPizza1.set(Bases.BaseSize.LARGE);
        assertTrue(customPizza1.equals(new CustomPizza(Bases.BaseSize.LARGE, Sauces.Sauce.TOMATO, Cheeses.Cheese.MOZZARELLA)));
    }

    @Test
    public void setSauce() {
        assertTrue(customPizza1.equals(new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO, Cheeses.Cheese.MOZZARELLA)));
        customPizza1.set(Sauces.Sauce.BBQ);
        assertTrue(customPizza1.equals(new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.BBQ, Cheeses.Cheese.MOZZARELLA)));
    }

    @Test
    public void setCheese() {
        assertTrue(customPizza1.equals(new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO, Cheeses.Cheese.MOZZARELLA)));
        customPizza1.set(Cheeses.Cheese.VEGAN);
        assertTrue(customPizza1.equals(new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO, Cheeses.Cheese.VEGAN)));
    }

    @Test
    public void testHashCode() {
        CustomPizza customPizza2 = new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO, Cheeses.Cheese.MOZZARELLA);
        CustomPizza customPizza3 = new CustomPizza(Bases.BaseSize.LARGE, Sauces.Sauce.TOMATO, Cheeses.Cheese.MOZZARELLA);

        assertEquals(customPizza1.hashCode(), customPizza2.hashCode());
        assertNotEquals(customPizza1.hashCode(), customPizza3.hashCode());
    }

    @Test
    public void testEqualsWithNull() {
        assertFalse(customPizza1.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() throws TooManyToppingsException {
        Topping topping1 = Topping.valueOf("onions");
        Topping topping2 = Topping.valueOf("bacon");
        List<Topping> toppings = new ArrayList<>(Arrays.asList(topping1, topping2));

        MenuPizza menuPizza = new MenuPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO, Cheeses.Cheese.MOZZARELLA, toppings);

        assertFalse(customPizza1.equals(menuPizza));
    }

    @Test
    public void testEqualsWithSameObject() {
        assertTrue(customPizza1.equals(customPizza1));
    }

    @Test
    public void testEqualsWithOtherObject() {
        CustomPizza customPizza2 = new CustomPizza(Bases.BaseSize.MEDIUM, Sauces.Sauce.TOMATO, Cheeses.Cheese.MOZZARELLA);
        assertTrue(customPizza1.equals(customPizza2));
    }

    @Test
    public void testToString() {
        assertEquals(customPizza1.toString(), "Custom Pizza: is a 'MEDIUM' sized base with 'TOMATO' sauce and 'MOZZARELLA' cheese $5.00");

        CustomPizza customPizza2 = new CustomPizza(Bases.BaseSize.LARGE, Sauces.Sauce.GARLIC, Cheeses.Cheese.NONE);
        assertEquals(customPizza2.toString(), "Custom Pizza: is a 'LARGE' sized base with 'GARLIC' sauce and 'NONE' cheese $7.00");

        CustomPizza customPizza3 = new CustomPizza(Bases.BaseSize.SMALL, Sauces.Sauce.BBQ, Cheeses.Cheese.VEGAN);
        assertEquals(customPizza3.toString(), "Custom Pizza: is a 'SMALL' sized base with 'BBQ' sauce and 'VEGAN' cheese $3.00");

    }

    @Test
    public void accessToppings() {
        List<Topping> toppings1 = customPizza1.accessToppings();
        assertEquals(toppings1.size(), 0);
        assertEquals(toppings1, new ArrayList<>());

        toppings1.add(Topping.valueOf("ONIONS"));

        List<Topping> toppings2 = customPizza1.getToppings();
        assertEquals(toppings2.size(), 1);
        assertEquals(toppings2, new ArrayList<>(Arrays.asList(Topping.valueOf("ONIONS"))));
    }
}