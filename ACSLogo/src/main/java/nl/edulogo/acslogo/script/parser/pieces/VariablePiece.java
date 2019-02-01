package nl.edulogo.acslogo.script.parser.pieces;

import nl.edulogo.acslogo.ACSLogo;
import nl.edulogo.acslogo.handlers.variable.LocalVariableHandler;
import nl.edulogo.acslogo.handlers.variable.VariableHandler;
import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.commandos.Value;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class VariablePiece implements Piece {
    private VariableHandler handler;
    private ACSLogo logo;
    private String key;
    private Value value = null;
    private PieceType type = PieceType.VARIABLE;

    public VariablePiece(Piece piece, VariableHandler handler, ACSLogo logo) throws ParsingException {
        if (piece.getType() != PieceType.VARIABLE) throw new IllegalArgumentException();
        this.handler = handler;
        this.key = piece.getPiece().replaceFirst(":", "");
        this.logo = logo;
    }

    @Override
    public PieceType getType() {
        return type;
    }

    @Override
    public Value getValue() throws ExecutorException {
        if (value == null) {
            VariableHandler variableHandler = logo.getCurrent();
            if (variableHandler == null) variableHandler = handler;
            value = variableHandler.getVariable(key);
            type = PieceType.getType(value.getValue());
        }
        return value;
    }
}
