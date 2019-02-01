package nl.edulogo.acslogo.script.parser.pieces;

import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.commandos.ListObject;


/**
 * Created by Under_Koen on 02/11/2018.
 */
public enum PieceType {
    STRING,         // "hey\ guys, "test
    NUMBER,         // 10, 20.4, 0.1
    BOOLEAN,        // "true, "false, true, false
    VARIABLE,       // :test, :x, :y
    COMPARISON,     // =, >, <
    CALCULATION,    // +, -, *, /
    LIST,           // [10 "de 10]
    STATEMENT,      // (10 * 10)
    COMMANDO,       // forward
    NONE;           // NULL

    public static PieceType getType(Object value) throws ExecutorException {
        if (value == null) {
            return PieceType.NONE;
        } else if (value instanceof Boolean) {
            return PieceType.BOOLEAN;
        } else if (value instanceof Number) {
            return PieceType.NUMBER;
        } else if (value instanceof String) {
            return PieceType.STRING;
        } else if (value instanceof ListObject) {
            return PieceType.LIST;
        } else {
            throw new ExecutorException(String.format("Got a %s returned but this can't happen.", value.getClass()));
        }
    }
}
