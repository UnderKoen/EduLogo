package nl.edulogo.acslogo.script.parser;

import nl.edulogo.acslogo.ConsoleHandler;
import nl.edulogo.acslogo.script.Script;

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

    public Script parseSafe(String code) {
        try {
            return parse(code);
        } catch (ParsingException ex) {
            consoleHandler.error(ex);
        }
        return null;
    }

    public Parser(ConsoleHandler consoleHandler) {
        this.consoleHandler = consoleHandler;
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

        return new ArrayList<>(pieces);
    }
}