package nl.edulogo.acslogo.script.commandos;

import nl.edulogo.acslogo.script.ExecutorException;

import java.util.Objects;

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
        } else if (value instanceof ListObject) {
            type = ValueType.LIST;
        } else {
            System.out.println(value);
            System.out.println(value.getClass());
            throw new IllegalArgumentException();
        }
    }

    public ValueType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public String getAsString() throws ExecutorException {
        if (type == ValueType.LIST) throw new ExecutorException(value.toString() + " is not a String");
        return value.toString();
    }

    public boolean getAsBoolean() throws ExecutorException {
        if (type != ValueType.BOOLEAN) throw new ExecutorException(value.toString() + " is not a Boolean");
        return (boolean) value;
    }

    public double getAsNumber() throws ExecutorException {
        if (type != ValueType.NUMBER) throw new ExecutorException(value.toString() + " is not a Number");
        return (double) value;
    }

    public ListObject getAsList() throws ExecutorException {
        if (type != ValueType.LIST) throw new ExecutorException(value.toString() + " is not a List");
        return (ListObject) value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value1 = (Value) o;
        return type == value1.type &&
                Objects.equals(value, value1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    public enum ValueType {
        STRING,         // "hey\ guys, "test
        NUMBER,         // 10, 20.4, 0.1
        BOOLEAN,        // "true, "false, true, false
        LIST,           // [10 "de 10]
        NONE            // NULL
    }
}
