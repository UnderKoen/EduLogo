package nl.edulogo.logo;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public interface Logo {
    Turtle getTurtle();

    void forward(double amount);

    void right(double rotation);

    void left(double rotation);
}