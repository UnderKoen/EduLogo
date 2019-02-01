package nl.edulogo.core;

/**
 * Created by Under_Koen on 19/07/2018.
 */
public class Position {
    private double x;
    private double y;

    public Position(Position position) {
        this(position.getX(), position.getY());
    }

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

    public Position inverted() {
        return new Position(-x, -y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (Double.compare(position.x, x) != 0) return false;
        return Double.compare(position.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public Position clone() {
        return new Position(this);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}