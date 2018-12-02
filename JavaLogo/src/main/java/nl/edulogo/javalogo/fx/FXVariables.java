package nl.edulogo.javalogo.fx;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXView;

public class FXVariables implements FXView {

    private StackPane pane;

    public FXVariables() {
        pane = new StackPane();
        pane.setPrefSize(300, 300);
        Button abutton = new Button();
        abutton.setText("animatie");
        pane.getChildren().addAll(abutton);

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
