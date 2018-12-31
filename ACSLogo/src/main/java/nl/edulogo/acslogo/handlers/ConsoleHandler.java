package nl.edulogo.acslogo.handlers;

import javafx.application.Platform;
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
        Platform.runLater(() -> console.println(obj.toString()));
    }

    public void error(String s) {
        Platform.runLater(() -> console.error(s));
    }

    public void error(Exception ex) {
        Platform.runLater(() -> error(ex.getMessage()));
    }
}
