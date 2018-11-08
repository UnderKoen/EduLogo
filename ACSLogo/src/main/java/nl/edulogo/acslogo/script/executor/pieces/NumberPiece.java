package nl.edulogo.acslogo.script.executor.pieces;

import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.parser.Piece;
import nl.edulogo.acslogo.script.parser.PieceType;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class NumberPiece implements Piece {
    private Value value;

    public NumberPiece(Piece number) {
        if (number.getType() != PieceType.NUMBER) throw new IllegalArgumentException();
        value = new Value(new Double(number.getPiece()));
    }

    @Override
    public PieceType getType() {
        return PieceType.NUMBER;
    }

    @Override
    public Value getValue() {
        return value;
    }
}
