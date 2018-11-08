package nl.edulogo.acslogo.script.parser.pieces;

import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.ExecutorException;
import nl.edulogo.acslogo.script.executor.VariableHandler;
import nl.edulogo.acslogo.script.parser.Piece;
import nl.edulogo.acslogo.script.parser.PieceType;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class VariablePiece implements Piece {
    private VariableHandler handler;
    private String key;
    private Value value = null;
    private PieceType type = PieceType.VARIABLE;

    public VariablePiece(Piece piece, VariableHandler handler) {
        if (piece.getType() != PieceType.VARIABLE) throw new IllegalArgumentException();
        this.handler = handler;
        this.key = piece.getPiece().replaceFirst(":", "");
    }

    @Override
    public PieceType getType() {
        return type;
    }

    @Override
    public Value getValue() throws ExecutorException {
        if (value == null) {
            value = handler.getVariable(key);
            type = PieceType.getType(value);
        }
        return value;
    }
}
