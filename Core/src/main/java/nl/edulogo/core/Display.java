package nl.edulogo.core;

/**
 * Created by Under_Koen on 19/07/2018.
 */
public interface Display {
    void drawLine(Position from, Position to);

    void drawLine(Position from, Position to, Color color);

    void clear();
}
