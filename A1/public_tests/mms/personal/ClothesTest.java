package mms.personal;

import mms.utility.Size;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

public class ClothesTest {

    private Clothes clothes1;
    private Clothes clothes2;
    private Clothes clothes3;
    private Clothes clothes4;

    @Before
    public void setUp() {
        clothes1 = new Clothes("Owner1", Size.SMALL, ClotheType.SHIRT);
        clothes2 = new Clothes("Owner2", Size.MEDIUM, ClotheType.PANTS);
        clothes3 = new Clothes("Owner3", Size.LARGE, ClotheType.SHORTS);
        clothes4 = new Clothes("Owner4", Size.SMALL, ClotheType.SOCKS);
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

    @Deprecated
    @Test
    public void testToString() {
        String message = "The string returned from toString() does not match " +
                "what is expected: ";
        assertEquals(message, "Clothes (Owner1) (SMALL, SHIRT)",
                clothes1.toString());
        assertEquals(message, "Clothes (Owner2) (MEDIUM, PANTS)",
                clothes2.toString());
        assertEquals(message, "Clothes (Owner3) (LARGE, SHORTS)",
                clothes3.toString());
        assertEquals(message, "Clothes (Owner4) (SMALL, SOCKS)",
                clothes4.toString());
    }
}