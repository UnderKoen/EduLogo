package nl.edulogo.javalogo.fx;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXView;
import nl.edulogo.javalogo.TekenApplet;

public class FXVariables implements FXView {
    private StackPane pane;

    public FXVariables(TekenApplet applet) {
        pane = new StackPane();
        pane.setPrefSize(300, 300);
        if (applet.isAnimatieMogelijk()) {
            Button aButton = new Button();

            aButton.setText("Animatie");
            aButton.setOnAction(event -> {
                if (!applet.animatieLopend()) {
                    applet.beginAnimatie();
                    aButton.setText("stoppen");
                } else {
                    applet.onderbreekAnimatie();
                    aButton.setText("animatie");
                }
            });
            pane.getChildren().addAll(aButton);
        } else {
            Label replaceme = new Label();
            replaceme.setText("Animatie is niet mogelijk :3");
            pane.getChildren().add(replaceme);
        }

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
