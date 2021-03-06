package nl.edulogo.acslogo.script.parser.pieces;

import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.Executor;
import nl.edulogo.acslogo.script.parser.Parser;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class StatementPiece implements Piece {
    private Parser parser;
    private Executor executor;
    private String script;
    private PieceType type = PieceType.STATEMENT;
    private boolean run = false;
    private Value value = null;

    public StatementPiece(Piece statement, Parser parser, Executor executor) throws ParsingException {
        if (statement.getType() != PieceType.STATEMENT) throw new IllegalArgumentException();
        this.script = statement.getPiece().replaceAll("^\\((.*)\\)$", "$1");
        this.parser = parser;
        this.executor = executor;
    }

    public void run() throws ParsingException, ExecutorException {
        Script code = parser.parse(script);
        value = executor.execute(code);
        type = PieceType.getType(value.getValue());
        run = true;
    }

    @Override
    public Value getValue() throws ParsingException, ExecutorException {
        if (!run) run();
        return value;
    }

    @Override
    public PieceType getType() {
        return type;
    }
}
