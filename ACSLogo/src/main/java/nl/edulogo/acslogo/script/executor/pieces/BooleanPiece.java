package nl.edulogo.acslogo.script.executor.pieces;

import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.parser.Piece;
import nl.edulogo.acslogo.script.parser.PieceType;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class BooleanPiece implements Piece {
    private Value value;

    public BooleanPiece(Piece bool) {
        if (bool.getType() != PieceType.BOOLEAN) throw new IllegalArgumentException();
        String b = bool.getPiece().replaceFirst("\"", "");
        value = new Value(b.equalsIgnoreCase("true"));
    }

    @Override
    public PieceType getType() {
        return PieceType.BOOLEAN;
    }

    @Override
    public Value getValue() {
        return value;
    }
}
