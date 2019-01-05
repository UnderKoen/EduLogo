package nl.edulogo.acslogo;

import nl.edulogo.acslogo.display.TurtleGraphics;
import nl.edulogo.core.Color;
import nl.edulogo.core.Font;
import nl.edulogo.core.Position;
import nl.edulogo.logo.Turtle;

public class ACSTurtle extends Turtle {
    private TurtleGraphics turtleGraphics;
    private ACSLogo logo;

    public ACSTurtle(Position position, double rotation, ACSLogo acsLogo) {
        super(position, rotation);
        this.logo = acsLogo;
        this.turtleGraphics = logo.getTurtleGraphics();
    }

    @Override
    public Color getFillColor() {
        return getColor();
    }

    @Override
    public void setFillColor(Color fillColor) {
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        position = position.clone();
        position.addPosition(logo.getStart().inverted());
        turtleGraphics.setPosition(position);
    }

    @Override
    public void setRotation(double rotation) {
        super.setRotation(rotation);
        turtleGraphics.setRotation(-rotation);
    }

    @Override
    public void setPenWidth(double penWidth) {
        super.setPenWidth(penWidth);
    }

    @Override
    public Color getColor() {
        return logo.colorHandler.getPenColor();
    }

    @Override
    public Font getFont() {
        Font old = super.getFont();
        Font font = new Font(old.getName(), old.getSize(), getColor());
        setFont(font);
        return super.getFont();
    }
}
