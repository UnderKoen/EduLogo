package nl.edulogo.editor.fx;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXView;
import nl.edulogo.editor.Console;
import nl.edulogo.editor.Editor;

/**
 * Created by Under_Koen on 11/09/2018.
 */
public class FXEditor implements Editor, FXView {
    private MenuBar menuBar;
    private StackPane pane;
    private TextArea textArea;
    private FXConsole console;

    public FXEditor() {
        pane = new StackPane();

        menuBar = new MenuBar();
        textArea = new TextArea();
        console = new FXConsole();
        StackPane consolePane = (StackPane) console.getNode();

        pane.getChildren().addAll(menuBar, textArea, consolePane);

        menuBar.prefWidthProperty().bind(pane.widthProperty());
        StackPane.setAlignment(menuBar, Pos.TOP_CENTER);

        textArea.setWrapText(true);
        textArea.maxHeightProperty().bind(pane.heightProperty().subtract(consolePane.heightProperty()).subtract(menuBar.heightProperty()));
        textArea.translateYProperty().bind(menuBar.heightProperty());

        StackPane.setAlignment(textArea, Pos.TOP_CENTER);

        consolePane.setMaxHeight(150);
        StackPane.setAlignment(consolePane, Pos.BOTTOM_CENTER);

        DragResizer.makeResizable(consolePane, pane.heightProperty().subtract(menuBar.heightProperty()));
    }

    @Override
    public void setText(String text) {
        textArea.setText(text);
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

    @Override
    public Node getNode() {
        return pane;
    }

    @Override
    public Size getSize() {
        return new Size(pane.getWidth(), pane.getHeight());
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
