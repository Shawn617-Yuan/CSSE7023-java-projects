package pizza.ingredients;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToppingTest {

    @Before
    public void setUp() throws Exception {
        Topping.createTopping("onions",true);
        Topping.createTopping("bacon",false);
    }

    @After
    public void tearDown() throws Exception {
        Topping.resetToppings();
    }

    @Test (expected = IllegalArgumentException.class)
    public void createToppingWithNull() {
        Topping.createTopping(null,true);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createToppingWithBlank() {
        Topping.createTopping("",true);
    }

    @Test
    public void createTopping() {
        assertEquals(Topping.values().length, 2);

        Topping topping1 = Topping.values()[0];
        Topping topping2 = Topping.values()[1];

        assertEquals(topping1.toString(), "ONIONS");
        assertEquals(topping2.toString(), "BACON");
        assertTrue(topping1.isVegan());
        assertFalse(topping2.isVegan());
    }

    @Test (expected = IllegalArgumentException.class)
    public void createDuplicateTopping() {
        Topping.createTopping("bacon",false);
    }

    @Test (expected = NullPointerException.class)
    public void valueOfWithNull() {
        Topping.valueOf(null);
    }

    @Test
    public void valueOf() {
        Topping topping1 = Topping.valueOf("onions");
        Topping topping2 = Topping.valueOf("bacon");

        assertEquals(topping1.toString(), "ONIONS");
        assertEquals(topping2.toString(), "BACON");
        assertTrue(topping1.isVegan());
        assertFalse(topping2.isVegan());
    }

    @Test (expected = IllegalArgumentException.class)
    public void valueOfWithNotAddedTopping() {
        Topping.valueOf("ham");
    }

    @Test
    public void values() {
        assertEquals(Topping.values().length, 2);

        Topping topping1 = Topping.values()[0];
        Topping topping2 = Topping.values()[1];

        assertEquals(topping1.toString(), "ONIONS");
        assertEquals(topping2.toString(), "BACON");
        assertTrue(topping1.isVegan());
        assertFalse(topping2.isVegan());

        Topping.createTopping("ham",false);
        assertEquals(Topping.values().length, 3);
        Topping topping3 = Topping.values()[2];
        assertEquals(topping3.toString(), "HAM");
        assertFalse(topping3.isVegan());
    }

    @Test
    public void resetToppings() {
        assertEquals(Topping.values().length, 2);
        Topping.resetToppings();
        assertEquals(Topping.values().length, 0);
    }

    @Test
    public void testToString() {
        Topping topping1 = Topping.valueOf("onions");
        Topping topping2 = Topping.valueOf("bacon");

        assertEquals(topping1.toString(), "ONIONS");
        assertEquals(topping2.toString(), "BACON");
    }

    @Test
    public void isVegan() {
        Topping topping1 = Topping.valueOf("onions");
        Topping topping2 = Topping.valueOf("bacon");

        assertTrue(topping1.isVegan());
        assertFalse(topping2.isVegan());
    }
}