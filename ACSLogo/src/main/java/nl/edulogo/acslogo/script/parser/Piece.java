package nl.edulogo.acslogo.script.parser;

/**
 * Created by Under_Koen on 02/11/2018.
 */
public class Piece {
    protected String piece;
    private PieceType type;

    public Piece() {
        this("");
    }

    public Piece(String piece) {
        this.piece = piece;
    }

    public String getPiece() {
        return piece;
    }

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
                if (!chechEquality('[', ']')) throw new ParsingException("List is incorrect.");
                break;
            case '(':
                type = PieceType.STATEMENT;
                if (!chechEquality('(', ')')) throw new ParsingException("Statement is incorrect.");
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
                type = PieceType.OPERATOR;
                break;
            case '+':
            case '-':
            case '*':
            case '/':
                type = PieceType.CALCULATION;
                break;
            default:
                type = (piece.matches("^\\d+\\.?\\d*$")) ? PieceType.NUMBER : PieceType.COMMANDO;
                break;
        }
    }

    private boolean chechEquality(char o, char c) {
        long open = piece.chars().filter(ch -> ch == o).count();
        long close = piece.chars().filter(ch -> ch == c).count();
        return open == close;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "piece='" + piece + '\'' +
                ", type=" + type +
                '}';
    }
}
