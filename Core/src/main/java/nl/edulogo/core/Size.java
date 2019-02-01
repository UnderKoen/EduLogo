package nl.edulogo.core;

/**
 * Created by Under_Koen on 10/09/2018.
 */
public class Size {
    private double w;
    private double h;

    public Size(double w, double h) {
        this.w = w;
        this.h = h;
    }

    public double getWidth() {
        return w;
    }

    public void setWidth(double w) {
        this.w = w;
    }

    public double getHeight() {
        return h;
    }

    public void setHeight(double h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "Size{" +
                "w=" + w +
                ", h=" + h +
                '}';
    }
}
