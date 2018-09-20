package nl.edulogo.core;

/**
 * Created by Under_Koen on 19/07/2018.
 */
public interface Canvas {
    void drawLine(Position from, Position to);
    void drawLine(Position from, Position to, Color color);

    void drawDot(Position position);

    void drawDot(Position position, Color color);

    void drawImage(Image image, Position position);

    void drawImage(Image image, Position position, Size size);

    Size getSize();

    void setSize(Size size);

    void write(String text, Position position);

    void write(String text, Position position, Font font);

    void fillScreen(Color color);

    void fillPolygon(Polygon polygon, Color color);

    void clear();
}
