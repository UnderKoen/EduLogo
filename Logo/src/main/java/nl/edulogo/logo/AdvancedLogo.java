package nl.edulogo.logo;

/**
 * Created by Under_Koen on 08/11/2018.
 */
public abstract class AdvancedLogo extends BasicLogo {
    public void arc(double radius, double degree) {
        Turtle turtle = getTurtle();

        getCanvas().arc(turtle.getPosition(), radius, radius, turtle.getRotation() + 90, -degree);
    }
}
