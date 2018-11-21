package nl.edulogo.acslogo.script.parser.pieces;

import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.ExecutorException;
import nl.edulogo.acslogo.script.parser.ParsingException;

/**
 * Created by Under_Koen on 02/11/2018.
 */
public interface Piece {
    PieceType getType();

    Value getValue() throws ParsingException, ExecutorException;

    default Value getValueSafe() throws ParsingException, ExecutorException {
        Value value = getValue();
        return (value != null) ? value : new Value(null);
    }

    default String getPiece() {
        try {
            return getValue().toString();
        } catch (Exception e) {
            return "";
        }
    }
}