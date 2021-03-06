package nl.edulogo.core;

/**
 * Created by Under_Koen on 19/07/2018.
 */
public class Color {
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color DARK_GRAY = new Color(64, 64, 64);
    public static final Color GRAY = new Color(128, 128, 128);
    public static final Color LIGHT_GRAY = new Color(192, 192, 192);
    public static final Color WHITE = new Color(255, 255, 255);

    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);

    public static final Color PINK = new Color(255, 175, 175);
    public static final Color ORANGE = new Color(255, 200, 0);
    public static final Color YELLOW = new Color(255, 255, 0);
    public static final Color MAGENTA = new Color(255, 0, 255);
    public static final Color CYAN = new Color(0, 255, 255);

    private int r;
    private int g;
    private int b;
    private double a;

    public Color(int r, int g, int b) {
        this(r, g, b, 1.0);
    }

    public Color(int r, int g, int b, double a) {
        if (0 > r || r > 255 || 0 > g || g > 255 || 0 > b || b > 255)
            throw new IllegalArgumentException("RGB should be between 0 and 255.");
        if (0.0 > a || a > 1.0) throw new IllegalArgumentException("Alpha should be between zero and one.");

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
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

    public double getAlpha() {
        return a;
    }

    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                ", a=" + a +
                '}';
    }
}
