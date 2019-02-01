package nl.edulogo.acslogo;

import javafx.scene.input.KeyCombination;

public class OS {
    private String name;
    private Type type;

    public OS() {
        name = System.getProperty("os.name").toLowerCase();
    }

    public String getName() {
        return name;
    }

    private void checkType() {
        if (name.contains("win")) {
            type = Type.WINDOWS;
        } else if (name.contains("mac")) {
            type = Type.MAC;
        } else if (name.contains("nix") || name.contains("nux") || name.indexOf("aix") > 0) {
            type = Type.LINUX;
        } else {
            type = Type.OTHER;
        }
    }

    public Type getType() {
        if (type == null) checkType();
        return type;
    }

    public KeyCombination.Modifier getModifier() {
        return (getType() == OS.Type.MAC) ? KeyCombination.META_DOWN : KeyCombination.CONTROL_DOWN;
    }

    public enum Type {
        WINDOWS, MAC, LINUX, OTHER
    }
}
