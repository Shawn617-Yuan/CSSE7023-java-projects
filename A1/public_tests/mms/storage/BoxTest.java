package mms.storage;

import mms.furniture.Furniture;
import mms.furniture.FurnitureType;
import mms.personal.Laptop;
import mms.utility.Packable;
import mms.utility.Size;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public class BoxTest {

    private Box box1;
    private Box box2;
    private Box box3;

    @Before
    public void setUp() {
        box1 = new Box(1000, 1000, 1000, "");
        box2 = new Box(500, 500, 500, Size.SMALL, "comment2");
        box3 = new Box(100, 100, 100, Size.LARGE, "comment3");
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

    @Deprecated
    @Test
    public void testGetMultiplier() {
        String message = "The integer value returned from getMultiplier() does not" +
                " match what is expected: ";
        assertEquals(message, 2, box1.getMultiplier());
        assertEquals(message, 2, box2.getMultiplier());
        assertEquals(message, 2, box3.getMultiplier());
    }

    /*
    Bare-bones implementation of Packable.
     */
    private static class SimplePackable implements Packable {
        private final double width;
        private final double height;
        private final double length;

        public SimplePackable(double width, double height, double length) {
            this.width = width;
            this.height = height;
            this.length = length;
        }

        @Override
        public double getWidth() {
            return width;
        }

        @Override
        public double getHeight() {
            return height;
        }

        @Override
        public double getLength() {
            return length;
        }
    }
}