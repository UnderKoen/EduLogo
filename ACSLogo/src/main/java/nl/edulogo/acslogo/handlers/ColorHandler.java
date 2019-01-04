package nl.edulogo.acslogo.handlers;

import nl.edulogo.core.Color;
import nl.edulogo.logo.Turtle;

import java.util.HashMap;
import java.util.Map;

public class ColorHandler {
    private Turtle turtle;
    private Map<Integer, Color> colors;
    private int background;
    private int penColor;

    public ColorHandler(Turtle turtle, Color... colors) {
        this.turtle = turtle;
        this.colors = new HashMap<>();
        for (int i = 0; i < colors.length; i++) {
            this.colors.put(i, colors[i]);
        }
        penColor = 1;
        background = 0;
    }

    public Color getColor(int i) {
        if (colors.containsKey(i)) return colors.get(i);
        return Color.BLACK;
    }

    public void setColor(int i, Color color) {
        colors.put(i, color);
    }

    public int getBackground() {
        return background;
    }

    public Color getBackgroundColor() {
        return getColor(getBackground());
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getPen() {
        return penColor;
    }

    public Color getPenColor() {
        return getColor(penColor);
    }

    public void setPenColor(int penColor) {
        this.penColor = penColor;
        turtle.setColor(getPenColor());
    }
}