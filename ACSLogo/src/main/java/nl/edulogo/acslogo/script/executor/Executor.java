package nl.edulogo.acslogo.script.executor;

import nl.edulogo.acslogo.Catch;
import nl.edulogo.acslogo.handlers.CommandoHandler;
import nl.edulogo.acslogo.handlers.ConsoleHandler;
import nl.edulogo.acslogo.handlers.Variable.VariableHandler;
import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.parser.pieces.Piece;

import java.util.List;

/**
 * Created by Under_Koen on 24/10/2018.
 */
public class Executor {
    private ConsoleHandler consoleHandler;
    private CommandoHandler commandoHandler;
    private VariableHandler variableHandler;

    public Executor(ConsoleHandler consoleHandler, CommandoHandler commandoHandler) {
        this(consoleHandler, commandoHandler, new VariableHandler());
    }

    public Executor(ConsoleHandler consoleHandler, CommandoHandler commandoHandler, VariableHandler variableHandler) {
        this.consoleHandler = consoleHandler;
        this.commandoHandler = commandoHandler;
        this.variableHandler = variableHandler;
    }

    public Value execute(Script script) throws ExecutorException, ParsingException {
        List<Piece> pieces = script.getPieces();

        Value last = new Value(null);
        for (Piece piece : pieces) {
            last = piece.getValueSafe();
        }

        return last;
    }

    public void executeSafe(Script script) {
        try {
            Value out = execute(script);
            if (out != null && out.getValue() != null) consoleHandler.output(out);
        } catch (ExecutorException | ParsingException ex) {
            consoleHandler.error(ex);
        } catch (Catch ex) {
            consoleHandler.error("No catch for: " + ex.getError());
        } catch (Exception ex) {
            ex.printStackTrace();
            //TODO handle this
        }
    }

    public ConsoleHandler getConsoleHandler() {
        return consoleHandler;
    }

    public CommandoHandler getCommandoHandler() {
        return commandoHandler;
    }

    public VariableHandler getVariableHandler() {
        return variableHandler;
    }
}
