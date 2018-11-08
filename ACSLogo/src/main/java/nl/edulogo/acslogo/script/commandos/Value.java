package nl.edulogo.acslogo.script.commandos;

import nl.edulogo.acslogo.script.parser.pieces.ListPiece;

/**
 * Created by Under_Koen on 07/11/2018.
 */
public class Value {
    private ValueType type;
    private Object value;

    public Value(Object value) {
        this.value = value;

        if (value == null) {
            type = ValueType.NONE;
        } else if (value instanceof Boolean) {
            type = ValueType.BOOLEAN;
        } else if (value instanceof Number) {
            type = ValueType.NUMBER;
        } else if (value instanceof String) {
            type = ValueType.STRING;
        } else if (value instanceof ListPiece.ListObject) {
            type = ValueType.LIST;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public ValueType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public enum ValueType {
        STRING,         // "hey\ guys, "test
        NUMBER,         // 10, 20.4, 0.1
        BOOLEAN,        // "true, "false, true, false
        LIST,           // [10 "de 10]
        NONE            // NULL
    }
}
