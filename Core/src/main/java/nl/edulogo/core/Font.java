package nl.edulogo.core;

/**
 * Created by Under_Koen on 10/09/2018.
 */
public class Font {
    //TODO add more fonts
    public static final Font SYSTEM_REGULAR = new Font("System Regular", 13.0, Color.BLACK);

    private String name = "System Regular";
    private double size = 13.0;
    private Color color = Color.BLACK;

    public Font(String name) {
        this.name = name;
    }

    public Font(double size) {
        this.size = size;
    }

    public Font(Color color) {
        this.color = color;
    }

    public Font(double size, Color color) {
        this.size = size;
        this.color = color;
    }

    public Font(String name, double size, Color color) {
        this.name = name;
        this.size = size;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Font{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", color=" + color +
                '}';
    }
}
