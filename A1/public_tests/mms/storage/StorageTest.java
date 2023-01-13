package mms.storage;

import mms.exceptions.PackingException;
import mms.exceptions.StorageFullException;
import mms.utility.Packable;
import mms.utility.Size;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import static org.junit.Assert.*;

public class StorageTest {

    private SimpleStorage storage1;
    private SimpleStorage storage2;
    private SimpleStorage storage3;

    @Before
    public void setUp() {
        storage1 = new SimpleStorage(1, 1, 1);
        storage2 = new SimpleStorage(100, 100, 100, Size.SMALL);
        storage3 = new SimpleStorage(1000, 1000, 1000, Size.LARGE);
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

    @Deprecated
    @Test
    public void testGetElementsOfType() {
        String message = "The List returned does not match what was expected: ";
        assertEquals("Newly initialised storage should have an empty inventory",
                new ArrayList<>(), storage3.getElements());
        Packable packable = new SimplePackable(1, 2, 3);
        Packable packable2 = new SimplePackable2(3, 2, 1);
        try {
            storage3.pack(packable);
        } catch (PackingException e) {
            fail("You should be able to pack valid item's into a storage " +
                    "object.\n" + e);
        }
        assertEquals(message, List.of(packable),
                storage3.getElementsOfType(packable));
        assertEquals(message,
                new ArrayList<>(), storage3.getElementsOfType(packable2));
        try {
            storage3.pack(packable);
        } catch (PackingException e) {
            fail("You should be able to pack valid item's into a storage " +
                    "object.\n" + e);
        }
        assertEquals(message, List.of(packable, packable),
                storage3.getElementsOfType(packable));
        assertEquals(message,
                new ArrayList<>(), storage3.getElementsOfType(packable2));
        try {
            storage3.pack(packable2);
        } catch (PackingException e) {
            fail("You should be able to pack valid item's into a storage " +
                    "object.\n" + e);
        }
        assertEquals(message, List.of(packable, packable),
                storage3.getElementsOfType(packable));
        assertEquals(message,
                List.of(packable2), storage3.getElementsOfType(packable2));

        String modified = "Adding or removing items from this list should not" +
                " affect the storage's internal list of items.";
        List<Packable> changed = storage3.getElementsOfType(packable);
        changed.add(packable);
        assertEquals(modified, List.of(packable, packable), storage3.getElementsOfType(packable));
        assertNotEquals(modified, changed, storage3.getElementsOfType(packable));
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
//
//
//
//

    @Deprecated
    @Test
    public void testGetOccupiedCapacity() {
        String message = "The integer returned from getOccupiedCapacity() " +
                "does not match what is expected: ";
        assertEquals(message, 0, storage2.getOccupiedCapacity());
        Packable packable = new SimplePackable(1, 2, 3);
        Packable packable2 = new SimplePackable2(3, 2, 1);
        try {
            storage2.pack(packable);
        } catch (PackingException e) {
            fail("You should be able to pack valid item's into a storage " +
                    "object.\n" + e);
        }
        assertEquals(message, 1, storage2.getOccupiedCapacity());
        try {
            storage2.pack(packable2);
        } catch (PackingException e) {
            fail("You should be able to pack valid item's into a storage " +
                    "object.\n" + e);
        }
        assertEquals(message, 2, storage2.getOccupiedCapacity());
        storage2.unpack();
        assertEquals(message, 1, storage2.getOccupiedCapacity());
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

    /*
    Bare-bones implementation of Storage, used to test default
    implementation of methods in the class.
     */
    private static class SimpleStorage extends Storage {

        public SimpleStorage(double width, double height, double length) throws IllegalArgumentException {
            super(width, height, length);
        }

        public SimpleStorage(double width, double height, double length, Size size) throws IllegalArgumentException {
            super(width, height, length, size);
        }

        @Override
        protected int getMultiplier() {
            return 1;
        }
    }

    /*
    Bare-bones implementation of Storage, used to test default
    implementation of methods in the class.
     */
    private static class SimpleStorage2 extends Storage implements Packable {

        public SimpleStorage2(double width, double height, double length) throws IllegalArgumentException {
            super(width, height, length);
        }

        public SimpleStorage2(double width, double height, double length,
                              Size size) throws IllegalArgumentException {
            super(width, height, length, size);
        }

        @Override
        protected int getMultiplier() {
            return 2;
        }
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

        @Override
        public String toString() {
            return String.format("%s (%.2f, %.2f, %.2f)",
                    getClass().getSimpleName(),
                    width,
                    height,
                    length);
        }
    }

    private static class SimplePackable2 implements Packable {
        private final double width;
        private final double height;
        private final double length;

        public SimplePackable2(double width, double height, double length) {
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