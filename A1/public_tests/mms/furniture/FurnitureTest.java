package mms.furniture;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

public class FurnitureTest {
    private static final int conversionAmount = 100;

    private Furniture furniture1;
    private Furniture furniture2;
    private Furniture furniture3;

    @Before
    public void setUp() {
        furniture1 = new Furniture(FurnitureType.BED);
        furniture2 = new Furniture(FurnitureType.TELEVISION);
        furniture3 = new Furniture(FurnitureType.TABLE);
    }

    @Deprecated
    @Test
    public void testGetType() {
        String message = "The type returned from getType() does not match " +
                "what is expected: ";
        assertEquals(message, FurnitureType.BED, furniture1.getType());
        assertEquals(message, FurnitureType.TELEVISION, furniture2.getType());
        assertEquals(message, FurnitureType.TABLE, furniture3.getType());
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
    @Deprecated
    @Test
    public void testGetLength() {
        String message = "The double value returned from getLength() does not " +
                "match what is expected: ";
        assertEquals(message, FurnitureType.BED.length * conversionAmount,
                furniture1.getLength(), 0.01);
        assertEquals(message, FurnitureType.TELEVISION.length * conversionAmount,
                furniture2.getLength(), 0.01);
        assertEquals(message, FurnitureType.TABLE.length * conversionAmount,
                furniture3.getLength(), 0.01);
    }
}