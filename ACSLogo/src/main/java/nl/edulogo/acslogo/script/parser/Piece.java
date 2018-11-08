package nl.edulogo.acslogo.script.parser;

import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.ExecutorException;

/**
 * Created by Under_Koen on 02/11/2018.
 */
public interface Piece {
    PieceType getType();

    Value getValue() throws ParsingException, ExecutorException;

    default Value getValueSafe() {
        try {
            return getValue();
        } catch (ParsingException | ExecutorException e) {
            return new Value(null);
        }
    }

    default String getPiece() {
        try {
            return getValue().toString();
        } catch (Exception e) {
            return "";
        }
    }
}