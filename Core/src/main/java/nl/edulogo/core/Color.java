package nl.edulogo.core;

/**
 * Created by Under_Koen on 19/07/2018.
 */
public class Color {
    //TODO add colors
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);

    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);

    private int r;
    private int g;
    private int b;

    public Color(int r, int g, int b) {
        if (0 > r || r > 255 || 0 > g || g > 255 || 0 > b || b > 255)
            throw new IllegalArgumentException("RGB should be between 0 and 255.");

        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getRed() {
        return r;
    }

    public int getGreen() {
        return g;
    }

    public int getBlue() {
        return b;
    }

    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }
}
