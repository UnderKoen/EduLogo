package nl.edulogo.javalogo.utils;

import nl.edulogo.core.Color;

public class ColorUtil {

    public static Color fromString(String color) {
        switch (color.toLowerCase()) {
            case "rood":
                return Color.RED;
            case "groen":
                return Color.GREEN;
            case "blauw":
                return Color.BLUE;
            case "geel":
                return Color.YELLOW;
            case "cyaan":
                return Color.CYAN;
            case "roze":
                return Color.PINK;
            case "zwart":
                return Color.BLACK;
            case "grijs":
                return Color.GRAY;
            case "lichtgrijs":
                return Color.LIGHT_GRAY;
            case "magenta":
                return Color.MAGENTA;
            case "wit":
                return Color.WHITE;
            case "oranje":
                return Color.ORANGE;
            default:
                return Color.BLACK;
        }
    }

}
