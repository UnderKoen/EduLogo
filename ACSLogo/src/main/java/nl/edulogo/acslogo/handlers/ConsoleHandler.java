package nl.edulogo.acslogo.handlers;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import nl.edulogo.editor.Console;

/**
 * Created by Under_Koen on 18/10/2018.
 */
public class ConsoleHandler {
    private Console console;
    private Condition condition = (text, key) -> true;
    private Waitable<String> input;

    public ConsoleHandler(Console console) {
        this.console = console;
        this.input = new Waitable<>();
        console.setOnKey(key -> {
            if (condition.check(console.getInputText(), key)) {
                input.setDone(console.getInputText());
            }
        });
    }

    public void type(Object obj) {
        Platform.runLater(() -> console.print(obj.toString()));
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

    public void waitFor(Condition condition) {
        this.condition = condition;
    }

    public void enableInput() {
        input.start();
        console.enableInput();
    }

    public void disableInput() {
        input.reset();
        console.disableInput();
        console.clearInput();
    }

    public Waitable<String> getInput() {
        return input;
    }

    public interface Condition {
        boolean check(String text, KeyCode key);
    }
}
