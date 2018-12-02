package nl.edulogo.acslogo.display.fx;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import nl.edulogo.acslogo.display.Procedures;
import nl.edulogo.acslogo.handlers.procedures.ProcedureHandler;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXView;

public class FXProcedures implements Procedures, FXView {
    private StackPane node;

    private TextArea code;

    public FXProcedures(ProcedureHandler procedureHandler) {
        node = new StackPane();

        StackPane pro = new StackPane();
        pro.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        StackPane.setAlignment(pro, Pos.TOP_LEFT);

        StackPane par = new StackPane();
        par.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        StackPane.setAlignment(par, Pos.TOP_RIGHT);

        code = new TextArea();
        StackPane.setAlignment(code, Pos.BOTTOM_RIGHT);

        StackPane buttons = new StackPane();
        StackPane.setAlignment(buttons, Pos.BOTTOM_CENTER);

        //LAYOUT
        pro.minWidthProperty().bind(node.widthProperty().divide(3));
        pro.maxWidthProperty().bind(pro.minWidthProperty());

        pro.maxHeightProperty().bind(node.heightProperty().subtract(buttons.heightProperty()).subtract(24));

        pro.setTranslateX(16);
        pro.setTranslateY(8);

        par.minWidthProperty().bind(node.widthProperty().divide(3).multiply(2).subtract(48));
        par.maxWidthProperty().bind(par.minWidthProperty());

        par.maxHeightProperty().bind(node.heightProperty().divide(4).subtract(8));
        par.minHeightProperty().bind(par.maxHeightProperty());

        par.setTranslateY(8);
        par.setTranslateX(-16);

        buttons.prefWidthProperty().bind(node.widthProperty());
        buttons.setMaxHeight(32);
        buttons.setTranslateY(-8);

        code.maxWidthProperty().bind(node.widthProperty().subtract(pro.widthProperty()).subtract(48));
        code.maxHeightProperty().bind(node.heightProperty().subtract(par.heightProperty()).subtract(buttons.heightProperty()).subtract(32));

        code.translateYProperty().bind(buttons.heightProperty().add(16).negate());
        code.setTranslateX(-16);

        //BUTTONS
        Button newP = new Button("New");
        newP.setTranslateX(16);
        newP.setPrefWidth(64);
        newP.setOnAction(this::newProcedure);
        StackPane.setAlignment(newP, Pos.CENTER_LEFT);

        Button revert = new Button("Revert");
        revert.setTranslateX(-96);
        revert.setPrefWidth(64);
        revert.setOnAction(this::revert);
        StackPane.setAlignment(revert, Pos.CENTER_RIGHT);

        Button apply = new Button("Apply");
        apply.setTranslateX(-16);
        apply.setPrefWidth(64);
        apply.setOnAction(this::apply);
        StackPane.setAlignment(apply, Pos.CENTER_RIGHT);

        buttons.getChildren().addAll(newP, revert, apply);

        //PROCEDURES
        Label proL = new Label(" Procedures");
        proL.setPrefHeight(24);
        proL.prefWidthProperty().bind(pro.widthProperty());
        proL.setBorder(new Border(new BorderStroke(null, null, Color.GRAY, null,
                null, null, BorderStrokeStyle.SOLID, null, CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));
        StackPane.setAlignment(proL, Pos.TOP_LEFT);

        pro.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        StackPane procedures = new StackPane();
        ScrollPane proS = new ScrollPane(procedures);
        proS.maxHeightProperty().bind(pro.heightProperty().subtract(proL.heightProperty()).subtract(2));
        proS.setStyle("-fx-focus-color: transparent; -fx-background-insets: 0;");
        StackPane.setAlignment(proS, Pos.BOTTOM_CENTER);

        pro.getChildren().addAll(proL, proS);

        //PARAMETERS
        Label parL = new Label(" Parameters");
        parL.setPrefHeight(24);
        parL.prefWidthProperty().bind(par.widthProperty());
        parL.setBorder(new Border(new BorderStroke(null, null, Color.GRAY, null,
                null, null, BorderStrokeStyle.SOLID, null, CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));
        StackPane.setAlignment(parL, Pos.TOP_LEFT);

        par.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        Button newPar = new Button("Add");
        newPar.setPrefWidth(64);
        newPar.setMinHeight(0);
        newPar.setPrefHeight(16);
        newPar.setPadding(Insets.EMPTY);
        newPar.setTranslateY(4);
        newPar.setTranslateX(-16);
        newPar.setOnAction(this::addParameter);
        StackPane.setAlignment(newPar, Pos.TOP_RIGHT);

        StackPane parameters = new StackPane();
        ScrollPane parS = new ScrollPane(parameters);
        parS.maxHeightProperty().bind(par.heightProperty().subtract(parL.heightProperty()).subtract(2));
        parS.setStyle("-fx-focus-color: transparent; -fx-background-insets: 0;");
        StackPane.setAlignment(parS, Pos.BOTTOM_CENTER);

        par.getChildren().addAll(parL, parS, newPar);

        //CODE
        code.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        code.setStyle("-fx-background-insets: 0; -fx-background-color: transparent; -fx-background-radius: 0, 0, 0, 0;");

        node.getChildren().addAll(pro, par, code, buttons);
    }

    public void newProcedure(Event event) {

    }

    public void revert(Event event) {

    }

    public void apply(Event event) {

    }

    public void addParameter(Event event) {

    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public Size getSize() {
        return new Size(node.getWidth(), node.getHeight());
    }
}
