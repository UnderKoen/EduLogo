package nl.edulogo.core.utils;

import nl.edulogo.core.Position;
import org.junit.Assert;

/**
 * Created by Under_Koen on 19/07/2018.
 */
public class PositionTest {
    public static void assertPosition(double expectedX, double expectedY, Position value) {
        if (value == null) {
            Assert.fail("Value was null");
            return;
        }
        if (expectedX != value.getX() || expectedY != value.getY()) {
            Assert.fail("Expected {x: " + expectedX + ", y: " + expectedY + "}, Got " + value.toString());
        }
    }
}
