package nl.edulogo.acslogo;

import nl.edulogo.core.Color;
import nl.edulogo.core.Font;
import nl.edulogo.core.Position;
import nl.edulogo.logo.Path;
import nl.edulogo.logo.Turtle;

public class ACSTurtle extends Turtle {
    public ACSTurtle(Turtle turtle) {
        super(turtle);
    }

    public ACSTurtle(Position position, double rotation) {
        super(position, rotation);
    }

    public ACSTurtle(Position position, double rotation, Path path, Color color, Color fillColor, Font font, boolean penDown) {
        super(position, rotation, path, color, fillColor, font, penDown);
    }

    @Override
    public Color getFillColor() {
        return super.getColor();
    }

    @Override
    public void setFillColor(Color fillColor) {
    }
}
