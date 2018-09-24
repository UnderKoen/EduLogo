package nl.edulogo.logo;

import nl.edulogo.core.Color;
import nl.edulogo.core.Position;

/**
 * Created by Under_Koen on 24/09/2018.
 */
public class Turtle {
    private Position position;
    private double rotation;

    private Color color;
    private Color fillColor;

    private Path path;

    private boolean penDown;

    public Turtle(Position position, double rotation) {
        this(position, rotation, Color.BLACK, null, new Path(), true);
    }

    public Turtle(Position position, double rotation, Color color, Color fillColor, Path path, boolean penDown) {
        this.position = position;
        this.rotation = rotation;
        this.color = color;
        this.fillColor = fillColor;
        this.path = path;
        this.penDown = penDown;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public boolean isPenDown() {
        return penDown;
    }

    public void setPenDown(boolean penDown) {
        this.penDown = penDown;
    }
}