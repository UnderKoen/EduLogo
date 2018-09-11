package nl.edulogo.editor;

import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

import java.util.function.Consumer;

/**
 * Created by Under_Koen on 11/09/2018.
 */
public class FxEditor extends StackPane implements Editor {
    public TextArea textArea;

    public FxEditor() {
        super();
        textArea = new TextArea("1234567891\n1234567892\n1234567893");
        textArea.setWrapText(true);

        getChildren().addAll(textArea);
    }

    @Override
    public String getText() {
        return textArea.getText();
    }

    @Override
    public String getSelectedText() {
        return textArea.getSelectedText();
    }

    @Override
    public String getCurrentLine() {
        int pos = textArea.getCaretPosition();
        for (String line : getText().split("\n")) {
            line = line + "\n";
            pos -= line.length();
            if (pos < 0) return line;
        }
        return null;
    }

    @Override
    public void print(String text) {

    }

    @Override
    public void println(String text) {

    }

    @Override
    public void registerOnRun(Consumer<String> onRun) {

    }
}
