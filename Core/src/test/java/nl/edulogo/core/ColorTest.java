package nl.edulogo.core;

import org.junit.Test;

/**
 * Created by Under_Koen on 10/09/2018.
 */
public class ColorTest {
    @Test(expected = IllegalArgumentException.class)
    public void lowR() {
        new Color(-100, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lowG() {
        new Color(0, -1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lowB() {
        new Color(0, 0, -42);
    }

    @Test(expected = IllegalArgumentException.class)
    public void highR() {
        new Color(299, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void highG() {
        new Color(0, 256, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void highB() {
        new Color(0, 0, 9001);
    }

    @Test
    public void normal() {
        new Color(0, 0, 0);
        new Color(255, 255, 255);
        new Color(42, 42, 42);
        new Color(255, 0, 255);
        new Color(250, 72, 002);
    }
}