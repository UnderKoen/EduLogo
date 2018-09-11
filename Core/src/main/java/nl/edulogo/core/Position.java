package nl.edulogo.core;

/**
 * Created by Under_Koen on 19/07/2018.
 */
public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void addX(double x) {
        setX(this.x + x);
    }

    public void addY(double y) {
        setY(this.y + y);
    }

    public void addPosition(Position position) {
        addX(position.getX());
        addY(position.getY());
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}