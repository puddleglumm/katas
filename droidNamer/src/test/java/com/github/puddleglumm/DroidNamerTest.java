package com.github.puddleglumm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/* TODO:
 * - 1 word model should have name like X-000
 */
public class DroidNamerTest {

    @Test
    public void testTwoWordModel() {
        String droidName = DroidNamer.abbreviateName("Hair Stylebot", "31F321C2");
        assertEquals("HS-C2", droidName);
    }

    @Test
    public void testThreeWordModel() {
        String droidName = DroidNamer.abbreviateName("Advanced Hair Stylebot", "31F321C3");
        assertEquals("AHS-C3", droidName);
    }

    @Test
    public void testFourWordModel() {
        String droidName = DroidNamer.abbreviateName("Super Advanced Hair Stylebot", "31F321D0");
        assertEquals("SAH-D0", droidName);
    }

    @Test
    public void testOneWordModel() {
        String droidName = DroidNamer.abbreviateName("Stylebot", "31F323D2" );
        assertEquals("S-3D2", droidName);
    }
}
