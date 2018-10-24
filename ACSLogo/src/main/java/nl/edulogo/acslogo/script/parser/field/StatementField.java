package nl.edulogo.acslogo.script.parser.field;

import nl.edulogo.acslogo.script.Statement;
import nl.edulogo.acslogo.script.parser.ParsingException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Under_Koen on 24/10/2018.
 */
public class StatementField extends Field<Statement> {
    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

    public StatementField(String field) {
        super(field, FieldType.STATEMENT);
    }

    public void appendPiece(String piece) {
        field = String.format("%s %s", piece, field);
    }

    public void addPiece(String piece) {
        field = String.format("%s %s", field, piece);
    }

    @Override
    public Statement parse() throws ParsingException {
        String statement = getField();
        statement = statement.replace("=", "==");
        try {
            engine.eval(statement); //TODO return statement
        } catch (ScriptException e) {
            throw new ParsingException(String.format("(%s) This statement isn't right.", statement));
        }
        return null;
    }
}
