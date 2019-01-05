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
        if (!started) {
            FXDisplay<FXVariables> vardisplay = new FXDisplay<>(this);
            vardisplay.show();
            started = true;
        }
        clearscreen();
        draw();
    }

    private void clearscreen() {
        pane.getChildren().clear();
    }

    private void draw() {
        if (animationHandler.isMogelijk()) {
            Button animationButton = new Button();

            animationButton.setText("Animatie");
            animationButton.setTranslateY(-130);
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
            traceButton.setTranslateY(-100);

            Button beginButton = new Button();
            beginButton.setText("begin");
            beginButton.setTranslateY(-50);
            beginButton.setOnAction(event -> {
                traceHandler.handleAllTracesTest();
            });

            Button stepButton = new Button();
            stepButton.setText("stap");
            stepButton.setTranslateX(-25);
            stepButton.setTranslateY(-20);

            Button backButton = new Button();
            backButton.setText("terug");
            backButton.setTranslateX(25);
            backButton.setTranslateY(-20);

            Label traceLabel = new Label();
            traceLabel.setText("¯\\_(ツ)_/¯");
            traceLabel.setTranslateY(20);

            Button loopButton = new Button();
            loopButton.setText("loop");

            traceButton.setOnAction(event -> {
                if (!traceHandler.isBezig()) {
                    traceHandler.setBezig(true);
                    traceButton.setText("trace uitschakelen");
                    pane.getChildren().addAll(beginButton, stepButton, traceLabel, backButton);
                } else {
                    traceHandler.setBezig(false);
                    traceButton.setText("trace aanschakelen");
                    pane.getChildren().removeAll(beginButton, stepButton, traceLabel, backButton);
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
