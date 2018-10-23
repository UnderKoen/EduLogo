package nl.edulogo.acslogo.script;

import nl.edulogo.acslogo.ConsoleHandler;
import nl.edulogo.acslogo.script.arguments.Argument;
import nl.edulogo.acslogo.script.arguments.ArgumentType;
import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.acslogo.script.commandos.CommandoHandler;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Parser {
    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

    private ConsoleHandler consoleHandler;
    private CommandoHandler commandoHandler;

    public Parser(ConsoleHandler consoleHandler, CommandoHandler commandoHandler) {
        this.consoleHandler = consoleHandler;
        this.commandoHandler = commandoHandler;
    }

    public void parse(Line line) throws ParsingException {
        String code = line.getCode();
        String[] pieces = code.split(" ");
        PieceType[] types = new PieceType[pieces.length];

        int list_depht = 0;
        for (int i = 0; i < pieces.length; i++) {
            String piece = pieces[i];

            if (piece.contains("[")) {
                list_depht += 1;
            }

            if (list_depht > 0) {
                types[i] = PieceType.LIST;

                if (piece.contains("]")) {
                    list_depht -= 1;
                }
                continue;
            } else {
                if (piece.contains("]")) {
                    throw new ParsingException("Can't close list that hasn't been opened.");
                }
            }

            if (piece.startsWith("//") || (i > 0 && types[i - 1] == PieceType.COMMENT)) {
                types[i] = PieceType.COMMENT;
                continue;
            }

            if (piece.matches("^\\S*[+\\-*/<>=]\\S*$")) {
                types[i] = PieceType.STATEMENT;
                continue;
            }

            if (piece.matches("^\"\\S+$")) {
                types[i] = PieceType.STRING;
                continue;
            }

            if (piece.matches("^-?\\d+.?\\d*$")) {
                types[i] = PieceType.NUMBER;
                continue;
            }

            types[i] = PieceType.COMMANDO;
        }
        if (list_depht > 0) {
            throw new ParsingException("Should close list.");
        }

        for (int i = 0; i < pieces.length; i++) {
            String piece = pieces[i];
            PieceType type = types[i];

            System.out.println(piece + " -=- " + type + " -=- " + i);
            if (type == PieceType.COMMANDO) {
                Commando cmd = commandoHandler.getCommando(piece);
                if (cmd == null) continue; //TODO error shit

                ArgumentType[] argumentTypes = cmd.getArguments();

                if (i + argumentTypes.length + 1 > pieces.length) {
                    throw new IllegalArgumentException(String.format("To little arguments for commando: %s", cmd.getName()));
                }

                Argument[] arguments = new Argument[argumentTypes.length];
                for (int a = 0; a < argumentTypes.length; a++) {
                    ArgumentType aType = argumentTypes[a];
                    String aPiece = pieces[i + a + 1];
                    PieceType aPieceType = types[i + a + 1];

                    System.out.println(aPieceType);

                    Argument argument = null;

                    if (aPieceType == PieceType.NUMBER) {
                        argument = parseNumber(aPiece);
                    } else if (aPieceType == PieceType.STATEMENT) {
                        argument = parseStatement(aPiece);
                    }

                    if (argument != null && argument.getType() == aType) {
                        arguments[a] = argument;
                    } else {
                        //TODO error shit
                    }

                    //TODO for the other types
                }

                cmd.call(arguments);
            }
        }
    }

    public void parse(Script script) throws ParsingException {
        for (Line line : script.getLines()) {
            parse(line);
        }
    }

    public void parseSafe(Script script) {
        try {
            for (Line line : script.getLines()) {
                parse(line);
            }
        } catch (ParsingException ex) {
            consoleHandler.error(ex);
        }
    }

    public Argument parseNumber(String string) {
        string = string.replace('.', ',');
        try {
            return new Argument<>(NumberFormat.getInstance().parse(string));
        } catch (ParseException e) {
            System.err.println(String.format("Parse got error from %s", string));
            return null;
        }
    }

    public Argument parseStatement(String string) {
        string = string.replace("=", "==");
        try {
            return new Argument<>(engine.eval(string));
        } catch (ScriptException e) {
            System.err.println(String.format("Parse got error from %s", string));
            return null;
        }
    }

    private enum PieceType {
        COMMENT, STRING, NUMBER, STATEMENT, LIST, COMMANDO
    }
}