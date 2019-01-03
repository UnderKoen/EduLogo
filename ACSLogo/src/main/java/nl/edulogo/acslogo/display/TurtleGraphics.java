package nl.edulogo.acslogo.display;

import nl.edulogo.core.Canvas;
import nl.edulogo.core.Image;
import nl.edulogo.core.Position;

public interface TurtleGraphics<T extends Canvas> {
    void setRotation(double degrees);

    void setPosition(Position position);

    void setAlpha(double alpha);

    void setImage(Image image);

    void show();

    void hide();

    void addToCanvas(T canvas);
}
