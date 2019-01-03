package nl.edulogo.logo;

import nl.edulogo.core.Position;

/**
 * Created by Under_Koen on 08/11/2018.
 */
public abstract class AdvancedLogo extends BasicLogo {
    public void arc(double radius, double degree) {
        Turtle turtle = getTurtle();

        getCanvas().arc(turtle.getPosition(), radius, radius, turtle.getRotation() + 90, -degree);
    }

    public void fillPath(Path path) {
        getCanvas().fillPolygon(path.toPolygon(), getTurtle().getFillColor());
    }

    public void writeRotated(String text) {
        Turtle turtle = getTurtle();
        getCanvas().translate(turtle.getPosition());
        getCanvas().rotate(turtle.getRotation());
        getCanvas().write(text, new Position(0, 0), turtle.getFont());
        getCanvas().rotate(-turtle.getRotation());
        getCanvas().translate(turtle.getPosition().inverted());
    }
}
