package nl.edulogo.javalogo.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXView;
import nl.edulogo.javalogo.TekenApplet;

public class FXVariables implements FXView {

    private StackPane pane;

    public FXVariables(TekenApplet applet) {
        pane = new StackPane();
        pane.setPrefSize(300, 300);
        Button abutton = new Button();
        abutton.setText("animatie");
        abutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                applet.traceHandler.animation = !applet.traceHandler.animation; //Kom op Daan je kunt het.
                if (applet.traceHandler.animation) {
                    new Thread(new Runnable() {
                        public void run() {
                            applet.animatie();
                        }
                    }).start();

                    abutton.setText("stoppen");
                } else {
                    abutton.setText("animatie");
                }

            }
        });
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
