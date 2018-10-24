package nl.edulogo.acslogo.script;

import nl.edulogo.acslogo.script.parser.field.Field;

import java.util.List;

/**
 * Created by Under_Koen on 04/10/2018.
 */
public class Script {
    List<Field> fields;

    public Script(List<Field> fields) {
        this.fields = fields;
    }

    public List<Field> getFields() {
        return fields;
    }
}
