package nl.edulogo.javalogo;

import nl.edulogo.core.Color;

public class Font extends nl.edulogo.core.Font {

    public final static String PLAIN = "";
    public final static String ITALIC = " Italic";
    public final static String BOLD = " Bold";

    public Font(String naam, String stijl, double grootte) {
        super(String.format("%s%s", naam, stijl).trim(), grootte, Color.BLACK);
    }

    public Font(String naam, String stijl, double grootte, Color color) {
        super(String.format("%s%s", naam, stijl).trim(), grootte, color);
    }
}
