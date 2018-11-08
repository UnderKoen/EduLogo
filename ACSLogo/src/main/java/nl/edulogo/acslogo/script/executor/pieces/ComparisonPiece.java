package nl.edulogo.acslogo.script.executor.pieces;

import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.ExecutorException;
import nl.edulogo.acslogo.script.parser.ParsingException;
import nl.edulogo.acslogo.script.parser.Piece;
import nl.edulogo.acslogo.script.parser.PieceType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class ComparisonPiece implements Piece {
    private Value value;
    private Piece left;
    private Piece comparison;
    private Piece right;

    public ComparisonPiece(Piece left, Piece comparison, Piece right) {
        if (testType(left)) throw new IllegalArgumentException();
        if (comparison.getType() != PieceType.COMPARISON) throw new IllegalArgumentException();
        if (testType(right)) throw new IllegalArgumentException();

        this.left = left;
        this.comparison = comparison;
        this.right = right;
    }

    public static List<Piece> calcSteps(List<Piece> pieces) {
        while (true) {
            List<Piece> piecesT = calcOneStep(pieces);
            if (piecesT == null) break;
            pieces = piecesT;
        }

        return pieces;
    }

    private static List<Piece> calcOneStep(List<Piece> pieces) {
        pieces = new ArrayList<>(pieces);
        Map<Integer, Piece> comparisons = new HashMap<>();
        for (int i = 0; i < pieces.size(); i++) {
            Piece p = pieces.get(i);
            if (p.getType() != PieceType.COMPARISON) continue;
            comparisons.put(i, p);
        }

        Map.Entry<Integer, Piece> calc = comparisons.entrySet().stream()
                .findFirst()
                .orElse(null);

        if (calc == null) return null;

        int key = calc.getKey();
        ComparisonPiece comparison = new ComparisonPiece(pieces.get(key - 1), calc.getValue(), pieces.get(key + 1));
        pieces.set(key, comparison);
        pieces.remove(key - 1);
        pieces.remove(key);
        return pieces;
    }

    private boolean testType(Piece piece) {
        return piece.getType() == PieceType.NONE;
    }

    public Boolean calc() throws ExecutorException, ParsingException {
        Object l = left.getValue();
        Object r = right.getValue();

        switch (comparison.getPiece()) {
            case ">":
                return l.hashCode() > r.hashCode();
            case "<":
                return l.hashCode() < r.hashCode();
            case "=":
                return l.equals(r);
            default:
                throw new ExecutorException("Comparison should be one of <, > or = but is " + comparison.getPiece());
        }
    }

    @Override
    public PieceType getType() {
        return PieceType.BOOLEAN;
    }

    @Override
    public Value getValue() throws ParsingException, ExecutorException {
        if (value == null) value = new Value(calc());
        return value;
    }
}
