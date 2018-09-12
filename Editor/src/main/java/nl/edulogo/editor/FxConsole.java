package nl.edulogo.editor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 12/09/2018.
 */
public class FxConsole extends StackPane implements Console {
    private ScrollPane scroll;
    private StackPane lines;
    private List<Label> textLines;

    public FxConsole() {
        super();

        scroll = new ScrollPane();
        lines = new StackPane();
        textLines = new ArrayList<>();

        scroll.setContent(lines);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        lines.setAlignment(Pos.TOP_CENTER);

        getChildren().addAll(scroll);
    }

    @Override
    public void print(String text) {
        if (textLines.isEmpty()) {
            println(text);
        } else {
            Label label = textLines.get(textLines.size() - 1);
            label.setText(label.getText() + text);
        }
    }

    @Override
    public void println(String text) {
        Label label = new Label(text);

        label.minWidthProperty().bind(widthProperty().subtract(2));
        label.maxWidthProperty().bind(label.minWidthProperty());
        label.setWrapText(true);

        if (textLines.size() % 2 == 0) {
            label.setBackground(new Background(new BackgroundFill(Color.gray(0.9), CornerRadii.EMPTY, Insets.EMPTY)));
        }

        if (!textLines.isEmpty()) {
            Label line = textLines.get(textLines.size() - 1);
            label.translateYProperty().bind(line.translateYProperty().add(line.heightProperty()));
        }

        textLines.add(label);
        lines.getChildren().add(label);
        lines.prefHeightProperty().bind(label.translateYProperty().add(label.heightProperty()));
    }

    @Override
    public void error(String text) {
        lines.getChildren().addAll(new Text(text));
    }
}
