package com.github.puddleglumm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/* TODO:
 */
public class DroidNamerTest {

    @Test
    public void testTwoWordModel() {
        String droidName = DroidNamer.abbreviateDroidName("Hair Stylebot", "31F321C2");
        assertEquals("HS-C2", droidName);
    }

    @Test
    public void testThreeWordModel() {
        String droidName = DroidNamer.abbreviateDroidName("Advanced Hair Stylebot", "31F321C3");
        assertEquals("AHS-C3", droidName);
    }

    @Test
    public void testFourWordModel() {
        String droidName = DroidNamer.abbreviateDroidName("Super Advanced Hair Stylebot", "31F321D0");
        assertEquals("SAH-D0", droidName);
    }

    @Test
    public void testOneWordModel() {
        String droidName = DroidNamer.abbreviateDroidName("Stylebot", "31F323D2" );
        assertEquals("S-3D2", droidName);
    }

    @Test
    public void testSingleRepeatInModel() {
        String droidName = DroidNamer.abbreviateDroidName("Super Stylebot XL", "31F323D2" );
        assertEquals("S2X-D2", droidName);
    }

    @Test
    public void testQuadRepeatInModel() {
        String droidName = DroidNamer.abbreviateDroidName("Super Spaceman Spiff Saucer XL", "31F323D4" );
        assertEquals("S4X-D4", droidName);
    }
}
