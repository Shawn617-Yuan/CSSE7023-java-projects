package mms.personal;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PersonalTest {

    private SimplePersonal personal1;
    private SimplePersonal personal2;
    private SimplePersonal personal3;

    @Before
    public void setUp() {
        personal1 = new SimplePersonal("Owner1");
        personal2 = new SimplePersonal("Owner2", 10, 20, 30);
        personal3 = new SimplePersonal("Owner3", 60, 40, 20);
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

    private HashMap<String, Double> getWHLMap(double width, double height,
                                             double length) {
        HashMap<String, Double> map = new HashMap<>();
        map.put("Width", width);
        map.put("Height", height);
        map.put("Length", length);
        return map;
    }

    private HashMap<String, Double> getWHLMap(Personal personal) {
        return getWHLMap(personal.getWidth(), personal.getHeight(),
                personal.getLength());
    }

    @Deprecated
    @Test
    public void testSetDimensions() {
        String message = "The method setDimensions() does not " +
                "achieve it's objective correctly";
        assertEquals("Personal default constructor does not set it's values " +
                        "correctly.",
                getWHLMap(0., 0., 0.), getWHLMap(personal1));
        personal1.setDimensions(10., 20., 30.);
        assertEquals(message, getWHLMap(10., 20., 30.), getWHLMap(personal1));
        personal1.setDimensions(99.99, 99.99, 99.99);
        assertEquals(message, getWHLMap(99.99, 99.99, 99.99), getWHLMap(personal1));
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