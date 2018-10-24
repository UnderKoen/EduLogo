package nl.edulogo.acslogo.script.parser;

import nl.edulogo.acslogo.ConsoleHandler;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.CommandoHandler;
import nl.edulogo.acslogo.script.parser.field.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Parser {
    private ConsoleHandler consoleHandler;
    private CommandoHandler commandoHandler;

    public Parser(ConsoleHandler consoleHandler, CommandoHandler commandoHandler) {
        this.consoleHandler = consoleHandler;
        this.commandoHandler = commandoHandler;
    }

    private boolean isComment;
    private int listDepht;
    private int statementDepht;

    public Script parseSafe(String code) {
        try {
            return parse(code);
        } catch (ParsingException ex) {
            consoleHandler.error(ex);
        }
        return null;
    }

    public Script parse(String code) throws ParsingException {
        code = makeReady(code);

        String[] pieces = code.split(" ");
        List<Field> fields = new ArrayList<>();

        listDepht = 0;
        statementDepht = 0;
        isComment = false;
        for (String piece : pieces) {
            Field field = toField(piece);
            if (field != null) fields.add(field);
        }

        if (listDepht > 0) {
            throw new ParsingException("Should close list.");
        }

        if (statementDepht > 0) {
            throw new ParsingException("Should close statement.");
        }

        fields = processFields(fields);

        return new Script(fields);
    }

    private List<Field> processFields(List<Field> fields) {
        List<Field> processedFields = processList(fields);
        processedFields = processStatement(processedFields);
        processedFields = processCommandos(processedFields);

        return processedFields;
    }

    private List<Field> processList(List<Field> fields) {
        List<Field> processedFields = new ArrayList<>();

        int listIndex = 0;
        ListField lastList = null;
        for (Field field : fields) {
            if (field.getType() == FieldType.LIST) {
                if (field.getField().equals("[")) listIndex += 1;
                if (field.getField().equals("]")) listIndex -= 1;

                if (lastList == null) {
                    lastList = (ListField) field;
                    processedFields.add(field);
                } else if (listIndex > 0) {
                    lastList.addPiece(field.getField());
                } else if (listIndex == 0) {
                    lastList.addPiece(field.getField());
                    lastList = null;
                }
            } else {
                processedFields.add(field);
            }
        }

        return processedFields;
    }

    private List<Field> processStatement(List<Field> fields) {
        List<Field> processedFields = new ArrayList<>();

        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            if (field.getType() == FieldType.STATEMENT) {
                StatementField statementField = (StatementField) field;
                if (!statementField.getField().startsWith("(") && i > 0) {
                    Field fieldAdd = fields.get(i - 1);
                    statementField.appendPiece(fieldAdd.getField());
                    processedFields.remove(fieldAdd);
                }

                if (statementField.getField().matches("^.*[+\\-*/><=]$") && fields.size() > i + 1) {
                    Field fieldAdd = fields.get(i + 1);
                    if (fieldAdd.getType() != FieldType.STATEMENT || fieldAdd.getField().startsWith("(")) {
                        statementField.addPiece(fieldAdd.getField());
                        fields.remove(fieldAdd);
                    }
                }
            }
            processedFields.add(field);
        }

        return processedFields;
    }

    private List<Field> processCommandos(List<Field> fields) {
        List<Field> processedFields = new ArrayList<>();

        for (int i = fields.size() - 1; i >= 0; i--) {
            Field field = fields.get(i);
            if (field.getType() == FieldType.COMMANDO) {
                CommandoField commandoField = (CommandoField) field;

                Field[] arguments = commandoField.getArguments();
                for (int j = 0; j < arguments.length; j++) {
                    arguments[j] = fields.get(i + j + 1);
                    processedFields.remove(arguments[j]);
                }
            }
            processedFields.add(0, field);
        }

        return processedFields;
    }

    private String makeReady(String string) {
        String[] replace = {"\n", "\\[", "\\]", "\\+", "\\-", "\\*", "(?<!/)/(?!/)", "\\<", "\\>", "\\=", "\\(", "\\)"};
        for (String s : replace) {
            string = string.replaceAll(String.format("(%s)", s), " $1 ");
        }
        return string;
    }

    private Field toField(String piece) throws ParsingException {
        if (piece.contains("\n")) {
            isComment = false;
            return null;
        }

        if (piece.isEmpty()) return null;

        FieldType type = checkFieldType(piece);

        switch (type) {
            case STRING:
                return new StringField(piece);
            case NUMBER:
                return new NumberField(piece);
            case STATEMENT:
                return new StatementField(piece);
            case LIST:
                return new ListField(piece);
            case COMMANDO:
                return new CommandoField(piece, commandoHandler.getCommando(piece));
            case COMMENT:
            default:
                return null;
        }
    }

    public FieldType checkFieldType(String piece) throws ParsingException {
        if (checkComment(piece)) {
            return FieldType.COMMENT;
        } else if (checkForList(piece)) {
            return FieldType.LIST;
        } else if (checkForStatement(piece)) {
            return FieldType.STATEMENT;
        } else if (checkForString(piece)) {
            return FieldType.STRING;
        } else if (checkForNumber(piece)) {
            return FieldType.NUMBER;
        } else if (checkForCommando(piece)) {
            return FieldType.COMMANDO;
        } else {
            throw new ParsingException(String.format("Unknown function %s", piece));
        }
    }

    public boolean checkComment(String piece) {
        if (isComment) return true;
        String test = piece.replace("\\/", "a");
        isComment = test.contains("//");
        return isComment;
    }

    public boolean checkForList(String piece) throws ParsingException {
        switch (piece) {
            case "[":
                listDepht += 1;
                return true;
            case "]":
                if (listDepht <= 0) throw new ParsingException("Can't close list that hasn't been opened yet.");
                listDepht -= 1;
                return true;
            default:
                return listDepht > 0;
        }
    }

    public boolean checkForStatement(String piece) throws ParsingException {
        switch (piece) {
            case "(":
                statementDepht += 1;
                return true;
            case ")":
                if (statementDepht <= 0)
                    throw new ParsingException("Can't close statement that hasn't been opened yet.");
                statementDepht -= 1;
                return true;
            case "+":
            case "-":
            case "*":
            case "/":
            case "<":
            case ">":
            case "=":
                return true;
            default:
                return statementDepht > 0;
        }
    }

    public boolean checkForString(String piece) {
        return piece.matches("^\"\\S+$");
    }

    public boolean checkForNumber(String piece) {
        return piece.matches("^\\d+.?\\d*$");
    }

    public boolean checkForCommando(String piece) {
        return commandoHandler.getCommando(piece) != null;
    }
}