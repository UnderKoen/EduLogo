package nl.edulogo.acslogo;

import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.editor.Console;

/**
 * Created by Under_Koen on 18/10/2018.
 */
public class ConsoleHandler {
    private Console console;

    public ConsoleHandler(Console console) {
        this.console = console;
    }

    public void error(ParsingException ex) {
        console.error(ex.getMessage());
    }

}
