package nl.edulogo.acslogo.script.parser;

import nl.edulogo.acslogo.script.commandos.Value;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class ParentPiece implements Piece {
    protected String piece;
    private PieceType type;

    public ParentPiece() {
        this("");
    }

    public ParentPiece(String piece) {
        this.piece = piece;
    }

    @Override
    public String getPiece() {
        return piece;
    }

    @Override
    public PieceType getType() {
        return type;
    }

    protected void append(String string) {
        piece += string;
    }

    protected void append(char ch) {
        piece += ch;
    }

    protected void checkType() throws ParsingException {
        switch (piece.charAt(0)) {
            case '[':
                type = PieceType.LIST;
                if (!checkEquality('[', ']')) throw new ParsingException("List is incorrect.");
                break;
            case '(':
                type = PieceType.STATEMENT;
                if (!checkEquality('(', ')')) throw new ParsingException("Statement is incorrect.");
                break;
            case '"':
                type = (piece.matches("(?i)\"(?:true|false)")) ? PieceType.BOOLEAN : PieceType.STRING;
                break;
            case ':':
                type = PieceType.VARIABLE;
                break;
            case '=':
            case '<':
            case '>':
                type = PieceType.COMPARISON;
                break;
            case '-':
                if (piece.equals("-")) {
                    type = PieceType.CALCULATION;
                } else if (piece.matches("^-\\d+\\.?\\d*$")) {
                    type = PieceType.NUMBER;
                } else {
                    throw new ParsingException("Unexpected object -");
                }
                break;
            case '+':
            case '*':
            case '/':
                type = PieceType.CALCULATION;
                break;
            default:
                type = (piece.matches("^\\d+\\.?\\d*$")) ? PieceType.NUMBER : PieceType.COMMANDO;
                break;
        }
    }

    private boolean checkEquality(char o, char c) {
        long open = piece.chars().filter(ch -> ch == o).count();
        long close = piece.chars().filter(ch -> ch == c).count();
        return open == close;
    }

    @Override
    public Value getValue() {
        return new Value(null);
    }
}
