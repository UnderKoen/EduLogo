package nl.edulogo.acslogo;

import nl.edulogo.acslogo.script.parser.ParsingException;
import nl.edulogo.editor.Console;

/**
 * Created by Under_Koen on 18/10/2018.
 */
public class ConsoleHandler {
    private Console console;

    public ConsoleHandler(Console console) {
        this.console = console;
    }

    public void output(Object obj) {
        console.println(obj.toString());
    }

    public void error(ParsingException ex) {
        console.error(ex.getMessage());
    }

    public void error(Exception ex) {
        console.error(ex.getMessage());
    }
}
