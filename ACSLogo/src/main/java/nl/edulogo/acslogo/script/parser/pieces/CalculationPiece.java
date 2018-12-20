package nl.edulogo.acslogo.script.parser.pieces;

import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.commandos.Value;

import java.util.*;

/**
 * Created by Under_Koen on 03/11/2018.
 */
public class CalculationPiece implements Piece {
    private static List<String> first = Arrays.asList("*", "/");
    private static List<String> second = Arrays.asList("+", "-");
    private Value value = null;
    private Piece left;
    private Piece calculation;
    private Piece right;

    public CalculationPiece(Piece left, Piece calculation, Piece right) throws ParsingException {
        if (testType(left)) throw new IllegalArgumentException();
        if (calculation.getType() != PieceType.CALCULATION) throw new IllegalArgumentException();
        if (testType(right)) throw new IllegalArgumentException();

        this.left = left;
        this.calculation = calculation;
        this.right = right;
    }

    public static List<Piece> calcSteps(List<Piece> pieces) throws ParsingException {
        while (true) {
            List<Piece> piecesT = calcOneStep(pieces);
            if (piecesT == null) break;
            pieces = piecesT;
        }

        return pieces;
    }

    private static List<Piece> calcOneStep(List<Piece> pieces) throws ParsingException {
        pieces = new ArrayList<>(pieces);
        Map<Integer, Piece> calculations = new HashMap<>();
        for (int i = 0; i < pieces.size(); i++) {
            Piece p = pieces.get(i);
            if (p.getType() != PieceType.CALCULATION) continue;
            calculations.put(i, p);
        }

        Map.Entry<Integer, Piece> calc = calculations.entrySet().stream()
                .filter(entry -> first.contains(entry.getValue().getPiece()))
                .findFirst()
                .or(() -> calculations.entrySet().stream()
                        .filter(entry -> second.contains(entry.getValue().getPiece()))
                        .findFirst())
                .orElse(null);

        if (calc == null) return null;

        int key = calc.getKey();
        CalculationPiece calculation = new CalculationPiece(pieces.get(key - 1), calc.getValue(), pieces.get(key + 1));
        pieces.set(key, calculation);
        pieces.remove(key - 1);
        pieces.remove(key);
        return pieces;
    }

    private boolean testType(Piece piece) throws ParsingException {
        if (piece.getType() == PieceType.NUMBER) return false;
        if (piece.getType() == PieceType.STATEMENT) return false;
        if (piece.getType() == PieceType.VARIABLE) return false;
        return piece.getType() != PieceType.COMMANDO;
    }

    @Override
    public PieceType getType() {
        return PieceType.NUMBER;
    }

    public Double calc() throws ExecutorException, ParsingException {
        Double l = getNumber(left);
        Double r = getNumber(right);
        switch (calculation.getPiece()) {
            case "-":
                return l - r;
            case "+":
                return l + r;
            case "*":
                return l * r;
            case "/":
                return l / r;
            default:
                throw new ExecutorException("Calculation should be one of -, +, * or / but is " + calculation.getPiece());
        }
    }

    private Double getNumber(Piece piece) throws ExecutorException, ParsingException {
        Object v = piece.getValue().getValue();
        if (v instanceof Double) {
            return (Double) v;
        } else {
            throw new ExecutorException("Can't do calculations with " + piece.getPiece());
        }
    }

    @Override
    public Value getValue() throws ExecutorException, ParsingException {
        if (value == null) value = new Value(calc());
        return value;
    }

    public Piece getMostRight() {
        return (right instanceof CalculationPiece) ? ((CalculationPiece) right).getMostRight() : right;
    }
}