package nl.edulogo.javalogo.fx;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXDisplay;
import nl.edulogo.display.fx.FXView;
import nl.edulogo.javalogo.AnimationHandler;
import nl.edulogo.javalogo.TraceHandler;

public class FXVariables implements FXView {
    private StackPane pane;
    private boolean started = false;
    private AnimationHandler animationHandler;
    private TraceHandler traceHandler;

    public FXVariables(AnimationHandler animationHandler, TraceHandler traceHandler) {
        pane = new StackPane();
        pane.setPrefSize(300, 300);
        this.animationHandler = animationHandler;
        this.traceHandler = traceHandler;
    }

    public void start() {
        if (started) return;
        FXDisplay<FXVariables> vardisplay = new FXDisplay<>(this);
        vardisplay.show();
        draw();
        started = true;
    }

    public void draw() {
        if (animationHandler.isMogelijk()) {
            Button animationButton = new Button();

            animationButton.setText("Animatie");
            animationButton.setOnAction(event -> {
                if (!animationHandler.isAnimation()) {
                    animationHandler.startAnimation();
                    animationButton.setText("stoppen");
                } else {
                    animationHandler.stopAnimation();
                    animationButton.setText("animatie");
                }
            });
            pane.getChildren().addAll(animationButton);
        }
        if (traceHandler.isMogelijk()) {
            Button traceButton = new Button();
            traceButton.setText("trace aanschakelen");

            Button beginButton = new Button();
            beginButton.setText("begin");
            beginButton.setOnAction(event -> {
                traceHandler.handleAllTracesTest();
            });

            Button stepButton = new Button();
            stepButton.setText("stap");

            Button backButton = new Button();
            backButton.setText("terug");

            Label traceLabel = new Label();
            traceLabel.setText("¯\\_(ツ)_/¯");

            Button loopButton = new Button();
            loopButton.setText("loop");

            traceButton.setOnAction(event -> {
                if (!traceHandler.isBezig()) {
                    traceHandler.setBezig(true);
                    traceButton.setText("trace uitschakelen");
                } else {
                    traceHandler.setBezig(false);
                    traceButton.setText("trace aanschakelen");
                }
            });
            pane.getChildren().addAll(traceButton);
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
