package nl.edulogo.javalogo.fx;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXView;
import nl.edulogo.javalogo.TekenApplet;

public class FXVariables implements FXView {
    private StackPane pane;
    private Thread animation;

    public FXVariables(TekenApplet applet) {
        pane = new StackPane();
        pane.setPrefSize(300, 300);
        Button aButton = new Button();
        aButton.setText("Animatie");
        aButton.setOnAction(event -> {
            if (!applet.animatieLopend()) {
                animation = new Thread(applet::animatie);
                animation.start();

                aButton.setText("stoppen");
            } else {
                animation.interrupt();
                aButton.setText("animation");
            }
        });
        pane.getChildren().addAll(aButton);
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
