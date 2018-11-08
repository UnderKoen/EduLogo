package nl.edulogo.acslogo.script.executor;

import nl.edulogo.acslogo.ConsoleHandler;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.CommandoHandler;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.pieces.*;
import nl.edulogo.acslogo.script.parser.Parser;
import nl.edulogo.acslogo.script.parser.ParsingException;
import nl.edulogo.acslogo.script.parser.Piece;

import java.util.List;

/**
 * Created by Under_Koen on 24/10/2018.
 */
public class Executor {
    private Parser parser;
    private ConsoleHandler consoleHandler;
    private CommandoHandler commandoHandler;
    private VariableHandler variableHandler;

    public Executor(Parser parser, ConsoleHandler consoleHandler, CommandoHandler commandoHandler) {
        this.parser = parser;
        this.consoleHandler = consoleHandler;
        this.commandoHandler = commandoHandler;
        this.variableHandler = new VariableHandler();
    }

    public Value execute(Script script) throws ExecutorException, ParsingException {
        List<Piece> pieces = script.getPieces();
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            switch (piece.getType()) {
                case NUMBER:
                    piece = new NumberPiece(piece);
                    break;
                case STATEMENT:
                    piece = new StatementPiece(piece, parser, this);
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
                    piece = new VariablePiece(piece, variableHandler);
                    break;
                case COMMANDO:
                    piece = new CommandoPiece(piece, commandoHandler);
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

        return pieces.get(pieces.size() - 1).getValue(); //todo
    }

    public void executeSafe(Script script) {
        try {
            //TODO make console better <- fx element
            Value out = execute(script);
            if (out != null && out.getValue() != null) consoleHandler.output(out);
        } catch (ExecutorException | ParsingException ex) {
            consoleHandler.error(ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            //TODO handle this
        }
    }
}
