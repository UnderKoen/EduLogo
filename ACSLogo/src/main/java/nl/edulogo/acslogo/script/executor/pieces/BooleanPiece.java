package nl.edulogo.acslogo.script.executor.pieces;

import nl.edulogo.acslogo.script.parser.Piece;
import nl.edulogo.acslogo.script.parser.PieceType;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class BooleanPiece implements Piece {
    private Boolean value;

    public BooleanPiece(Piece bool) {
        if (bool.getType() != PieceType.BOOLEAN) throw new IllegalArgumentException();
        String b = bool.getPiece().replaceFirst("\"", "");
        value = b.equalsIgnoreCase("true");
    }

    @Override
    public PieceType getType() {
        return PieceType.BOOLEAN;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
