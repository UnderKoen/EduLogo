package nl.edulogo.acslogo;

import nl.edulogo.acslogo.display.TurtleGraphics;
import nl.edulogo.core.Color;
import nl.edulogo.core.Position;
import nl.edulogo.logo.Turtle;

public class ACSTurtle extends Turtle {
    private TurtleGraphics turtleGraphics;
    private Position start;

    public ACSTurtle(Position position, double rotation, TurtleGraphics turtleGraphics) {
        super(position, rotation);
        start = position.clone();
        this.turtleGraphics = turtleGraphics;
    }

    @Override
    public Color getFillColor() {
        return super.getColor();
    }

    @Override
    public void setFillColor(Color fillColor) {
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        position = position.clone();
        position.addPosition(start.inverted());
        turtleGraphics.setPosition(position);
    }

    @Override
    public void setRotation(double rotation) {
        super.setRotation(rotation);
        turtleGraphics.setRotation(-rotation);
    }
}
