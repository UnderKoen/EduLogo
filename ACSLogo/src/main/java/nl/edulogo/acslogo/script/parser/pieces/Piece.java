package nl.edulogo.acslogo.script.parser.pieces;

import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.commandos.Value;

/**
 * Created by Under_Koen on 02/11/2018.
 */
public interface Piece {
    PieceType getType() throws ParsingException;

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