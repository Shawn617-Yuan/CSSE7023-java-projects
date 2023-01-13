package mms.storage;

import mms.exceptions.BadItemException;
import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.personal.Personal;
import mms.utility.Size;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BagTest {

    private Bag bag1;
    private Bag bag2;
    private Bag bag3;

    @Before
    public void setUp() {
        bag1 = new Bag(1000, 1000, 1000);
        bag2 = new Bag(500, 500, 500, Size.SMALL);
        bag3 = new Bag(10, 10, 10, Size.LARGE);
    }

    @Deprecated
    @Test
    public void testConstructor() {
        try {
            new Bag(-1, -1, -1, Size.SMALL);
            fail("Bag should inherit the exceptions thrown by the super " +
                    "class.");
        } catch (IllegalArgumentException expected) {
        }
        Bag bag = new Bag(1, 1, 1);
        assertEquals("A Bag should have a size of MEDIUM if not supplied",
                Size.MEDIUM, bag.getSize());
    }

//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

    /*
Bare-bones implementation of Personal, used to test default
implementation of methods in the class.
 */
    private static class SimplePersonal extends Personal {

        public SimplePersonal(String owner) {
            super(owner);
        }

        public SimplePersonal(String owner, double width, double height,
                              double length) {
            super(owner, width, height, length);
        }
    }
}