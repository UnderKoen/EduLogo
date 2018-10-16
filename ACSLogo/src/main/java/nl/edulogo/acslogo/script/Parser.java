package nl.edulogo.acslogo.script;

import nl.edulogo.acslogo.script.arguments.Argument;
import nl.edulogo.acslogo.script.arguments.ArgumentType;
import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.acslogo.script.commandos.CommandoHandler;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Parser {
    //TODO CONSOLE HANDLER
    CommandoHandler handler;

    public Parser(CommandoHandler handler) {
        this.handler = handler;
    }

    public void parse(Line line) {
        String code = line.getCode();
        String[] pieces = code.split(" ");
        PieceType[] types = new PieceType[pieces.length];

        for (int i = 0; i < pieces.length; i++) {
            String piece = pieces[i];

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

        for (int i = 0; i < pieces.length; i++) {
            String piece = pieces[i];
            PieceType type = types[i];

            System.out.println(piece + " -=- " + type + " -=- " + i);
            if (type == PieceType.COMMANDO) {
                Commando cmd = handler.getCommando(piece);
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

                    if (aPieceType == PieceType.NUMBER) {
                        arguments[a] = new Argument<>(parseNumber(aPiece));
                    }

                    //TODO for the other types
                }

                cmd.call(arguments);
            }
        }
    }

    public void parse(Script script) {
        for (Line line : script.getLines()) {
            parse(line);
        }
    }

    public Number parseNumber(String string) {
        string = string.replace('.', ',');
        try {
            return NumberFormat.getInstance().parse(string);
        } catch (ParseException e) {
            System.err.println(String.format("Parse got error from %s", string));
            return 0;
        }
    }

    public void parseStatement(String string) {

    }

    private enum PieceType {
        COMMENT, STRING, NUMBER, STATEMENT, LIST, COMMANDO
    }
}