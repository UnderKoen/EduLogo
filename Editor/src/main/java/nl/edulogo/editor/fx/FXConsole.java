package nl.edulogo.editor.fx;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
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
    private VBox lines;
    private List<Label> textLines;
    private boolean error = false;
    private TextField input;
    private KeyListener listener;

    public FXConsole() {
        pane = new StackPane();

        lines = new VBox();
        scroll = new ScrollPane(lines);
        textLines = new ArrayList<>();
        input = new TextField();

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.maxHeightProperty().bind(pane.heightProperty().subtract(input.heightProperty()));

        lines.setAlignment(Pos.TOP_LEFT);

        lines.heightProperty().addListener((observable, oldValue, newValue) -> scroll.setVvalue(scroll.getVmax()));

        StackPane.setAlignment(input, Pos.BOTTOM_LEFT);
        input.setMinHeight(0);
        input.setPrefHeight(0);
        input.setOnKeyPressed(event -> {
            if (listener != null) listener.run(event.getCode());
        });

        StackPane.setAlignment(scroll, Pos.TOP_LEFT);

        pane.getChildren().addAll(scroll, input);
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

        lines.getChildren().add(label);

        textLines.add(label);
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

    @Override
    public void enableInput() {
        input.setEditable(true);
        input.setPrefHeight(24);
        Platform.runLater(() -> input.requestFocus());
    }

    @Override
    public void disableInput() {
        input.setEditable(false);
        input.setPrefHeight(0);
    }

    @Override
    public String getInputText() {
        return input.getText();
    }

    @Override
    public void clearInput() {
        input.setText("");
    }

    @Override
    public void setOnKey(KeyListener listener) {
        this.listener = listener;
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
