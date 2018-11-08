package nl.edulogo.acslogo.script.executor.pieces;

import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.parser.Piece;
import nl.edulogo.acslogo.script.parser.PieceType;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class StringPiece implements Piece {
    private Value value;

    public StringPiece(Piece string) {
        if (string.getType() != PieceType.STRING) throw new IllegalArgumentException();
        value = new Value(string.getPiece().replaceFirst("\"", ""));
    }

    @Override
    public PieceType getType() {
        return PieceType.STRING;
    }

    @Override
    public Value getValue() {
        return value;
    }
}
