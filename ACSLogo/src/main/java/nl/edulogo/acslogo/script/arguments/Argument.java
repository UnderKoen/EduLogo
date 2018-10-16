package nl.edulogo.acslogo.script.arguments;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Argument<T> {
    private ArgumentType type;
    private T value;

    public Argument(T value) {
        for (ArgumentType type : ArgumentType.values()) {
            if (type.isOfType(value)) {
                this.type = type;
                this.value = value;
                break;
            }
        }
        if (type == null) {
            throw new IllegalArgumentException("value should be a ArgumentType");
        }
    }

    public ArgumentType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }
}
