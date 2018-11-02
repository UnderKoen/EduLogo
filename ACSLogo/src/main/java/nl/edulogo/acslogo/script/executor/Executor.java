package nl.edulogo.acslogo.script.executor;

import nl.edulogo.acslogo.ConsoleHandler;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.CommandoHandler;
import nl.edulogo.acslogo.script.parser.ParsingException;
import nl.edulogo.acslogo.script.parser.Piece;

import java.util.List;

/**
 * Created by Under_Koen on 24/10/2018.
 */
public class Executor {
    private ConsoleHandler consoleHandler;
    private CommandoHandler commandoHandler;

    public Executor(ConsoleHandler consoleHandler, CommandoHandler commandoHandler) {
        this.consoleHandler = consoleHandler;
        this.commandoHandler = commandoHandler;
    }


    public void execute(Script script) throws ExecutorException, ParsingException {
        List<Piece> pieces = script.getPieces();
    }

    public void executeSafe(Script script) {
        try {
            execute(script);
        } catch (ExecutorException | ParsingException ex) {
            consoleHandler.error(ex);
        }
    }
}
