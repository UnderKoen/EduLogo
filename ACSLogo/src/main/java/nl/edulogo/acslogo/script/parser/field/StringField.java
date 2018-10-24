package nl.edulogo.acslogo.script.parser.field;

/**
 * Created by Under_Koen on 24/10/2018.
 */
public class StringField extends Field<String> {
    public StringField(String field) {
        super(field, FieldType.STRING);
    }

    @Override
    public String parse() {
        return null;
    }
}
