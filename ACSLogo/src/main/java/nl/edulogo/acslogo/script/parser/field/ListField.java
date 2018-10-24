package nl.edulogo.acslogo.script.parser.field;

/**
 * Created by Under_Koen on 24/10/2018.
 */
public class ListField extends Field<String> {
    private boolean done = false;

    public ListField(String field) {
        super(field, FieldType.LIST);
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void addPiece(String piece) {
        field = String.format("%s %s", field, piece);
    }

    @Override
    public String parse() {
        return null;
    }
}
