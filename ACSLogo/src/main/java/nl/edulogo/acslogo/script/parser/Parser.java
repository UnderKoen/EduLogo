package nl.edulogo.acslogo.script.parser;

import nl.edulogo.acslogo.handlers.ConsoleHandler;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.executor.Executor;
import nl.edulogo.acslogo.script.parser.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Parser {
    //TODO make class for all special chars
    private static List<Character> dividers = Arrays.asList(' ', '\n');
    private static List<Character> dividersAdd = Arrays.asList('[', '(', '-');
    private static List<Character> dividersAddOne = Arrays.asList('+', '*', '/', '<', '>', '=', ']', ')');
    private static List<Character> skip = Arrays.asList('\\', ' ', '\n');

    private ConsoleHandler consoleHandler;
    private Executor executor;

    public Script parseSafe(String code) {
        try {
            return parse(code);
        } catch (ParsingException ex) {
            consoleHandler.error(ex);
        }
        return null;
    }

    public Parser(ConsoleHandler consoleHandler, Executor executor) {
        this.consoleHandler = consoleHandler;
        this.executor = executor;
    }

    public Script parse(String code) throws ParsingException {
        code = makeReady(code);
        List<Piece> pieces = toPieces(code);
        return new Script(pieces);
    }

    private String makeReady(String string) {
        //remove all comments
        string = string.replaceAll("(?<!\\\\)//.*", "");

        return string;
    }

    public List<Piece> toPieces(String string) throws ParsingException {
        List<ParentPiece> pieces = new ArrayList<>();
        char[] chars = string.toCharArray();

        List<Character> allDividers = new ArrayList<>();
        allDividers.addAll(dividers);
        allDividers.addAll(dividersAdd);
        allDividers.addAll(dividersAddOne);

        ParentPiece piece = new ParentPiece();
        boolean backslash = false;
        int list = 0;
        int statement = 0;
        boolean skipNext = false;

        for (char c : chars) {
            boolean canAdd = list == 0 && statement == 0;

            if (skipNext || (allDividers.contains(c) && !backslash && canAdd)) {
                pieces.add(piece);
                piece = new ParentPiece();
            }

            if (!skip.contains(c) || !canAdd || backslash || (dividersAdd.contains(c) || dividersAddOne.contains(c))) {
                piece.append(c);
            }

            skipNext = dividersAddOne.contains(c) && !backslash && canAdd;

            list += (c == '[' && !backslash && statement == 0) ? 1 : 0;
            list -= (c == ']' && !backslash && statement == 0) ? 1 : 0;

            statement += (c == '(' && !backslash && list == 0) ? 1 : 0;
            statement -= (c == ')' && !backslash && list == 0) ? 1 : 0;

            backslash = c == '\\' && !backslash;
        }

        pieces.add(piece);
        pieces.removeIf(p -> p.getPiece().isEmpty() || p.getPiece().equals(" "));
        for (ParentPiece p : pieces) {
            p.checkType();
        }

        return toPiecesOfType(pieces);
    }

    private List<Piece> toPiecesOfType(List<ParentPiece> parentPieces) throws ParsingException {
        List<Piece> pieces = new ArrayList<>(parentPieces);
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            switch (piece.getType()) {
                case NUMBER:
                    piece = new NumberPiece(piece);
                    break;
                case STATEMENT:
                    piece = new StatementPiece(piece, this, executor);
                    break;
                case LIST:
                    piece = new ListPiece(piece);
                    break;
                case STRING:
                    piece = new StringPiece(piece);
                    break;
                case BOOLEAN:
                    piece = new BooleanPiece(piece);
                    break;
                case VARIABLE:
                    piece = new VariablePiece(piece, executor.getVariableHandler());
                    break;
                case COMMANDO:
                    piece = new CommandoPiece(piece, executor.getCommandoHandler());
                    break;
                case NONE:
                    throw new IllegalArgumentException("There should not be a piece in script with type NONE.");
                case COMPARISON:
                case CALCULATION:
                default:
                    continue;
            }
            pieces.set(i, piece);
        }

        pieces = CalculationPiece.calcSteps(pieces);
        pieces = ComparisonPiece.calcSteps(pieces);
        pieces = CommandoPiece.findArguments(pieces);

        return pieces;
    }
}