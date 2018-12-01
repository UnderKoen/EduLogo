package nl.edulogo.acslogo.script.executor;

import nl.edulogo.acslogo.Catch;
import nl.edulogo.acslogo.ConsoleHandler;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.CommandoHandler;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.parser.ParsingException;
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
        this.consoleHandler = consoleHandler;
        this.commandoHandler = commandoHandler;
        this.variableHandler = new VariableHandler();
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
            //TODO make console better <- fx element
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

    public CommandoHandler getCommandoHandler() {
        return commandoHandler;
    }

    public VariableHandler getVariableHandler() {
        return variableHandler;
    }
}
