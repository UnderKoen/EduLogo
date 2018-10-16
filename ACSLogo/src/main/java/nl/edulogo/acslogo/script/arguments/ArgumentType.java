package nl.edulogo.acslogo.script.arguments;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public enum ArgumentType {
    NUMBER(Number.class), BOOLEAN(Boolean.class), STRING(String.class);//, LIST, DATE, TIME

    private Class<?> type;

    ArgumentType(Class type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public boolean isOfType(Object obj) {
        return type.isAssignableFrom(obj.getClass());
    }
}
