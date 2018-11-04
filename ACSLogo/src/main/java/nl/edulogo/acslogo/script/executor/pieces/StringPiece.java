package nl.edulogo.acslogo.script.executor.pieces;

import nl.edulogo.acslogo.script.parser.Piece;
import nl.edulogo.acslogo.script.parser.PieceType;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class StringPiece implements Piece {
    private String value;

    public StringPiece(Piece string) {
        if (string.getType() != PieceType.STRING) throw new IllegalArgumentException();
        value = string.getPiece().replaceFirst("\"", "");
    }

    @Override
    public PieceType getType() {
        return PieceType.STRING;
    }

    @Override
    public String getValue() {
        return value;
    }
}
