package nl.edulogo.acslogo.script.executor.pieces;

import nl.edulogo.acslogo.script.parser.Piece;
import nl.edulogo.acslogo.script.parser.PieceType;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class NumberPiece implements Piece {
    private BigDecimal value;

    public NumberPiece(Piece number) {
        if (number.getType() != PieceType.NUMBER) throw new IllegalArgumentException();
        value = new BigDecimal(number.getPiece(), MathContext.DECIMAL64);
    }

    @Override
    public PieceType getType() {
        return PieceType.NUMBER;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }
}
