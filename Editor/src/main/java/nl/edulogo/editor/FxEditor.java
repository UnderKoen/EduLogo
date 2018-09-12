package nl.edulogo.editor;

import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

/**
 * Created by Under_Koen on 11/09/2018.
 */
public class FxEditor extends StackPane implements Editor {
    private TextArea textArea;
    private FxConsole console;

    public FxEditor() {
        super();

        textArea = new TextArea();
        console = new FxConsole();

        textArea.setWrapText(true);
        textArea.maxHeightProperty().bind(heightProperty().subtract(console.heightProperty()));
        setAlignment(textArea, Pos.TOP_CENTER);

        console.setMaxHeight(200);
        setAlignment(console, Pos.BOTTOM_CENTER);

        getChildren().addAll(textArea, console);
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
    public Console getConsole() {
        return console;
    }
}
