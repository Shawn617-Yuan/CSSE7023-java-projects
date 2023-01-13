package mms.furniture;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;

public class FurnitureTypeTest {

//
//
//
//
//
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
    public void testHeightAttribute() {
        String message = " height has not been assigned correctly";
        assertEquals(FurnitureType.CHAIR + message,
                1.5, FurnitureType.CHAIR.height, 0.01);
        assertEquals(FurnitureType.TABLE + message,
                5.0, FurnitureType.TABLE.height, 0.01);
        assertEquals(FurnitureType.BED + message,
                2.0, FurnitureType.BED.height, 0.01);
        assertEquals(FurnitureType.DESK + message,
                2.0, FurnitureType.DESK.height, 0.01);
        assertEquals(FurnitureType.TELEVISION + message,
                0.75, FurnitureType.TELEVISION.height, 0.01);
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

}