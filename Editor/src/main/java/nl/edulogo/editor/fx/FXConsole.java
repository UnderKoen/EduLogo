package nl.edulogo.editor.fx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXView;
import nl.edulogo.editor.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 12/09/2018.
 */
public class FXConsole implements Console, FXView {
    private StackPane pane;

    private ScrollPane scroll;
    private StackPane lines;
    private List<Label> textLines;
    private boolean error = false;

    public FXConsole() {
        pane = new StackPane();

        scroll = new ScrollPane();
        lines = new StackPane();
        textLines = new ArrayList<>();

        scroll.setContent(lines);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        lines.setAlignment(Pos.TOP_CENTER);

        pane.getChildren().addAll(scroll);
    }

    @Override
    public void print(String text) {
        if (textLines.isEmpty() || error) {
            println(text);
        } else {
            Label label = textLines.get(textLines.size() - 1);
            label.setText(label.getText() + text);
        }
    }

    @Override
    public void println(String text) {
        Label label = new Label(text);

        label.minWidthProperty().bind(pane.widthProperty().subtract(2));
        label.maxWidthProperty().bind(label.minWidthProperty());
        label.setWrapText(true);

        if (textLines.size() % 2 == 0) {
            label.setBackground(background(Color.gray(0.9)));
        }

        if (!textLines.isEmpty()) {
            Label line = textLines.get(textLines.size() - 1);
            label.translateYProperty().bind(line.translateYProperty().add(line.heightProperty()));
        }

        textLines.add(label);
        lines.getChildren().add(label);
        lines.prefHeightProperty().bind(label.translateYProperty().add(label.heightProperty()));

        error = false;
    }

    @Override
    public void error(String text) {
        println(text);
        Label line = textLines.get(textLines.size() - 1);
        line.setTextFill(Color.RED);
        line.setBackground(background(Color.rgb(255, 0, 0, 0.1 + (textLines.size() % 2) / 20.0)));
        error = true;
    }

    private Background background(Color color) {
        return new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY));
    }

    @Override
    public Node getNode() {
        return pane;
    }

    @Override
    public Size getSize() {
        return new Size(pane.getWidth(), pane.getHeight());
    }
}
