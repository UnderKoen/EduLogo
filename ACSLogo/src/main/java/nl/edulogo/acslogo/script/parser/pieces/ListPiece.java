package nl.edulogo.acslogo.script.parser.pieces;

import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.utils.ValueUtil;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class ListPiece implements Piece {
    private Value value;

    public ListPiece(Piece list) throws ParsingException {
        if (list.getType() != PieceType.LIST) throw new IllegalArgumentException();
        value = ValueUtil.stringToListValue(list.getPiece());
    }

    @Override
    public PieceType getType() {
        return PieceType.LIST;
    }

    @Override
    public Value getValue() {
        return value;
    }

}