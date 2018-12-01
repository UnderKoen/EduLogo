package nl.edulogo.acslogo;

import nl.edulogo.core.Color;

import java.util.HashMap;
import java.util.Map;

public class ColorHandler {
    private Map<Integer, Color> colors;
    private int background;

    public ColorHandler(Color... colors) {
        this.colors = new HashMap<>();
        for (int i = 0; i < colors.length; i++) {
            this.colors.put(i, colors[i]);
        }
        background = 3;
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
}