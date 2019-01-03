package nl.edulogo.acslogo.utils;

import nl.edulogo.acslogo.script.commandos.ListObject;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.core.Font;
import nl.edulogo.core.Position;

import java.util.ArrayList;
import java.util.List;

public class ValueUtil {
    public static Value stringToValue(String string) {
        if (string.equalsIgnoreCase("true")) return new Value(true);
        if (string.equalsIgnoreCase("false")) return new Value(false);
        try {
            double d = Double.parseDouble(string);
            return new Value(d);
        } catch (NumberFormatException ignored) {
        }
        if (isList(string)) return stringToListValue(string);
        return new Value(string);
    }

    public static boolean isList(String string) {
        if (string.length() < 2 || string.charAt(0) != '[' || string.charAt(string.length() - 1) != ']') return false;
        long open = string.chars().filter(ch -> ch == '[').count();
        long close = string.chars().filter(ch -> ch == ']').count();
        return open == close;
    }

    public static Value stringToListValue(String string) {
        if (!isList(string)) throw new IllegalArgumentException("String is not a list");
        string = string.replaceFirst("^\\[(.*)\\]$", "$1");
        string = string.replaceAll("([\\[\\]])", " $1 ");
        String[] list = string.split(" ");

        List<Value> returnList = new ArrayList<>();
        int index = 0;
        StringBuffer listTest = new StringBuffer();

        for (int i = 0; i < list.length; i++) {
            String item = list[i];
            if (item.isEmpty()) continue;
            switch (item) {
                case "[":
                    index++;
                    break;
                case "]":
                    index--;
                    if (index == 0) {
                        listTest.append(item);
                        returnList.add(stringToListValue(listTest.toString()));
                        listTest = new StringBuffer();
                    }
                    break;
                default:
                    if (index == 0) {
                        returnList.add(stringToValue(item));
                    }
                    break;
            }

            if (index != 0) {
                listTest.append(item);
                listTest.append(" ");
            }
        }

        return new Value(new ListObject(returnList));
    }

    public static Value fontToValue(String font) {
        return new Value(new ListObject((Object[]) font.split(" ")));
    }

    public static Value fontToValue(Font font) {
        return fontToValue(font.getName());
    }

    public static Value positionToValue(Position position, Position relative) {
        position = position.clone();
        position.addPosition(relative.inverted());
        return new Value(new ListObject(position.getX(), position.getY()));
    }
}
