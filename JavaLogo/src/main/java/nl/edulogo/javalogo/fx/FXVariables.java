package nl.edulogo.javalogo.fx;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXDisplay;
import nl.edulogo.display.fx.FXView;
import nl.edulogo.javalogo.AnimationHandler;
import nl.edulogo.javalogo.TekenApplet;
import nl.edulogo.javalogo.TraceHandler;
import nl.edulogo.javalogo.variabele.InvoerVariabele;
import nl.edulogo.javalogo.variabele.SchuifInvoerVariabele;

import java.util.ArrayList;
import java.util.List;

public class FXVariables implements FXView {
    private StackPane pane;
    private TekenApplet applet;
    private boolean started = false;
    private AnimationHandler animationHandler;
    private TraceHandler traceHandler;
    private List<StackPane> invoerVars;
    private int varHeight = -240;

    public FXVariables(TekenApplet applet, AnimationHandler animationHandler, TraceHandler traceHandler) {
        pane = new StackPane();
        pane.setPrefSize(300, 700);
        this.invoerVars = new ArrayList<StackPane>();
        this.applet = applet;
        this.animationHandler = animationHandler;
        this.traceHandler = traceHandler;
    }

    public void makeVisible(InvoerVariabele iv) {
        if (iv instanceof SchuifInvoerVariabele) {
            SchuifInvoerVariabele.SchuifInvoerVariabeleNode var = new SchuifInvoerVariabele.SchuifInvoerVariabeleNode(applet, (SchuifInvoerVariabele) iv);
            var.setTranslateY(varHeight);
            pane.getChildren().add(var);
            invoerVars.add(var);
        } else {
            InvoerVariabele.InvoerVariabeleNode var = new InvoerVariabele.InvoerVariabeleNode(applet, iv);
            var.setTranslateY(varHeight);
            pane.getChildren().add(var);
            invoerVars.add(var);
        }
        varHeight += 70;

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

            animationButton.setText("animatie");
            animationButton.setTranslateY(-330);
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
            traceButton.setTranslateY(-300);
            Label traceLabel = new Label();
            traceLabel.setText("");
            traceLabel.setTranslateY(20);

            Button beginButton = new Button();
            beginButton.setText("begin");
            beginButton.setTranslateY(-50);
            beginButton.setOnAction(event -> {
                traceLabel.setText(traceHandler.getCurrentTraceString());
                traceHandler.begin();
            });

            Button stepButton = new Button();
            stepButton.setText("stap");
            stepButton.setTranslateX(-25);
            stepButton.setTranslateY(-20);
            stepButton.setOnAction(event -> {
                traceLabel.setText(traceHandler.getCurrentTraceString());
                traceHandler.next();
            });

            Button backButton = new Button();
            backButton.setText("terug");
            backButton.setTranslateX(25);
            backButton.setTranslateY(-20);
            backButton.setOnAction(event -> {
                traceLabel.setText(traceHandler.getCurrentTraceString());
                traceHandler.back();
            });

            Button loopButton = new Button();
            loopButton.setText("loop");
            loopButton.setTranslateY(50);
            loopButton.setOnAction(event -> {
                traceHandler.loop();
            });

            traceButton.setOnAction(event -> {
                if (!traceHandler.isBezig()) {
                    traceHandler.setBezig(true);
                    traceHandler.begin();
                    traceButton.setText("trace uitschakelen");
                    pane.getChildren().removeAll(invoerVars);
                    pane.getChildren().addAll(beginButton, stepButton, traceLabel, backButton, loopButton);
                } else {
                    traceHandler.setBezig(false);
                    traceButton.setText("trace aanschakelen");
                    traceHandler.done();
                    pane.getChildren().addAll(invoerVars);
                    pane.getChildren().removeAll(beginButton, stepButton, traceLabel, backButton, loopButton);
                }
            });
            pane.getChildren().addAll(traceButton);

            ((Stage) pane.getScene().getWindow()).close();
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
