package nl.edulogo.acslogo.script.parser.field;

import nl.edulogo.acslogo.script.parser.ParsingException;

/**
 * Created by Under_Koen on 24/10/2018.
 */
public abstract class Field<T> {
    FieldType type;
    String field;

    public Field(String field, FieldType type) {
        this.field = field;
        this.type = type;
    }

    public FieldType getType() {
        return type;
    }

    public String getField() {
        return field;
    }

    public abstract T parse() throws ParsingException;
}
