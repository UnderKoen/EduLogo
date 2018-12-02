package nl.edulogo.logo;

import nl.edulogo.core.Color;
import nl.edulogo.core.Font;
import nl.edulogo.core.Position;

/**
 * Created by Under_Koen on 24/09/2018.
 */
public class Turtle {
    private Position position;
    private double rotation;

    private Path path;

    private Color color;
    private Color fillColor;

    private Font font;

    private boolean penDown;

    public Turtle(Turtle turtle) {
        this(turtle.getPosition(), turtle.getRotation(), turtle.path, turtle.color, turtle.fillColor, turtle.font, turtle.penDown);
    }

    public Turtle(Position position, double rotation) {
        this(position, rotation, new Path(), Color.BLACK, null, Font.SYSTEM_REGULAR, true);
    }

    public Turtle(Position position, double rotation, Path path, Color color, Color fillColor, Font font, boolean penDown) {
        this.position = position;
        this.rotation = rotation;
        this.path = path;
        this.color = color;
        this.fillColor = fillColor;
        this.font = font;
        this.penDown = penDown;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
        if (isPenDown()) getPath().addPoint(position.clone());
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
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

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public boolean isPenDown() {
        return penDown;
    }

    public void setPenDown(boolean penDown) {
        this.penDown = penDown;
    }

    @Override
    public Turtle clone() {
        return new Turtle(this);
    }
}