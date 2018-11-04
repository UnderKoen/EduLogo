package nl.edulogo.acslogo.script.parser;

import nl.edulogo.acslogo.script.executor.ExecutorException;

/**
 * Created by Under_Koen on 02/11/2018.
 */
public interface Piece {
    PieceType getType();

    Object getValue() throws ParsingException, ExecutorException;

    default String getPiece() {
        try {
            return getValue().toString();
        } catch (Exception e) {
            return "";
        }
    }
}