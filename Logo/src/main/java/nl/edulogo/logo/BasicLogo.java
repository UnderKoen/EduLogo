package nl.edulogo.logo;

import nl.edulogo.core.Color;
import nl.edulogo.core.Image;
import nl.edulogo.core.Polygon;
import nl.edulogo.core.Position;
import nl.edulogo.core.utils.MathUtil;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public abstract class BasicLogo implements Logo {
    public void clearScreen() {
        getCanvas().clear();
    }

    public void fillScreen(Color color) {
        getCanvas().fillScreen(color);
    }

    public void drawImage(Image image) {
        getCanvas().drawImage(image, getTurtle().getPosition());
    }

    public void resetPath() {
        getTurtle().getPath().clear();
    }

    public void fillPath() {
        Turtle turtle = getTurtle();
        getCanvas().fillPolygon(new Polygon(turtle.getPath().getPoints()), turtle.getFillColor());
    }

    public void write(String text) {
        Turtle turtle = getTurtle();
        getCanvas().write(text, turtle.getPosition(), turtle.getFont());
    }

    public void forward(double amount) {
        Turtle turtle = getTurtle();
        Position newPos = MathUtil.getRelativePosition(turtle.getRotation(), -amount);
        newPos.addPosition(turtle.getPosition());
        if (turtle.isPenDown()) getCanvas().drawLine(turtle.getPosition(), newPos);
        turtle.setPosition(newPos);
    }

    public void right(double rotation) {
        Turtle turtle = getTurtle();
        double cRotation = turtle.getRotation();
        cRotation -= rotation;
        cRotation = cRotation % 360;
        if (cRotation < 0) cRotation = 360 + cRotation;
        turtle.setRotation(cRotation);
    }

    public void left(double rotation) {
        right(-rotation);
    }
}