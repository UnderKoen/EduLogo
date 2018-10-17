package nl.edulogo.logo;

import nl.edulogo.core.Canvas;
import nl.edulogo.core.Position;
import nl.edulogo.core.utils.MathUtil;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public abstract class Logo {
    public abstract Canvas getCanvas();
    public abstract Turtle getTurtle();

    public void forward(double amount) {
        Turtle turtle = getTurtle();
        Position newPos = MathUtil.getRelativePosition(turtle.getRotation(), -amount);
        newPos.addPosition(turtle.getPosition());
        getCanvas().drawLine(turtle.getPosition(), newPos);
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