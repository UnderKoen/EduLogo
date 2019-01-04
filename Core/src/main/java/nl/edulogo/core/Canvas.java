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

    void arc(Position center, double radiusX, double radiusY, double startAngle, double length);

    void arc(Position center, double radiusX, double radiusY, double startAngle, double length, Color color);

    void rotate(double degrees);

    void setPenWidth(double width);

    void translate(Position position);

    void clear();
}
