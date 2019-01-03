package nl.edulogo.acslogo.display.fx;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import nl.edulogo.acslogo.display.Procedures;
import nl.edulogo.acslogo.handlers.procedures.EditableProcedure;
import nl.edulogo.acslogo.handlers.procedures.Procedure;
import nl.edulogo.acslogo.handlers.procedures.ProcedureHandler;
import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FXProcedures implements Procedures, FXView {
    private StackPane node;

    private VBox procedures;
    private VBox parameters;
    private Button newPar;
    private TextArea code;

    private ProcedureHandler procedureHandler;
    private List<Procedure> proceduresOrg;
    private List<EditableProcedure> proceduresNew;

    private EditableLabel<EditableProcedure> selected;
    private EditableLabel<?> selectedParam;

    public FXProcedures(ProcedureHandler procedureHandler) {
        draw();
        this.procedureHandler = procedureHandler;
        reload();
        init();
    }

    public void newProcedure(Event event) {
        int i = 0;
        String name = "untitled";
        while (contains(name)) {
            i += 1;
            name = String.format("untitled%s", i);
        }

        EditableProcedure procedure = new EditableProcedure(name, new ArrayList<>(), "", procedureHandler.getLogo());
        proceduresNew.add(procedure);

        displayProcedure(procedure, null);
    }

    private boolean contains(String name) {
        return proceduresNew.stream()
                .map(Commando::getName)
                .anyMatch(s -> s.equalsIgnoreCase(name));
    }

    private void displayProcedure(EditableProcedure procedure, Procedure orginal) {
        EditableLabel<EditableProcedure> label = new EditableLabel<>(procedure.getName(), procedure);
        boolean even = procedures.getChildren().size() % 2 == 0;
        if (even)
            label.setBackground(new Background(new BackgroundFill(Color.gray(0.9), CornerRadii.EMPTY, Insets.EMPTY)));

        label.prefWidthProperty().bind(procedures.widthProperty());

        procedures.getChildren().add(label);

        label.textProperty().addListener((observable, oldValue, newValue) -> procedure.setName(newValue));

        label.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                select(label);
            } else {
                if (even)
                    label.setBackground(new Background(new BackgroundFill(Color.gray(0.9), CornerRadii.EMPTY, Insets.EMPTY)));
                else label.setBackground(null);
            }
        });

        ContextMenu contextMenu = new ContextMenu();

        MenuItem delete = new MenuItem("Delete");

        if (orginal != null) {
            MenuItem revert = new MenuItem("Revert");
            revert.setOnAction(event -> {
                EditableProcedure org = new EditableProcedure(orginal);
                proceduresNew.set(proceduresNew.indexOf(label.getHolder()), org);
                label.setHolder(org);
                label.textProperty().setValue(org.getName());
                select(label);
            });
            contextMenu.getItems().add(revert);
        }

        contextMenu.getItems().add(delete);

        delete.setOnAction(event -> {
            select(null);
            proceduresNew.remove(procedures.getChildren().indexOf(label));
            procedures.getChildren().remove(label);
        });


        label.setContextMenu(contextMenu);
    }

    private void displayParameter(String parameter) {
        EditableLabel<?> label = new EditableLabel<>(parameter, null);

        boolean even = parameters.getChildren().size() % 2 == 0;
        if (even)
            label.setBackground(new Background(new BackgroundFill(Color.gray(0.9), CornerRadii.EMPTY, Insets.EMPTY)));

        label.prefWidthProperty().bind(parameters.widthProperty());

        parameters.getChildren().add(label);

        label.textProperty().addListener((observable, oldValue, newValue) -> {
            List<String> par = selected.getHolder().getParameters();
            par.set(parameters.getChildren().indexOf(label), newValue);
        });

        label.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (selectedParam != null) selectedParam.selectedProperty().setValue(false);
                label.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                selectedParam = label;
            } else {
                if (even)
                    label.setBackground(new Background(new BackgroundFill(Color.gray(0.9), CornerRadii.EMPTY, Insets.EMPTY)));
                else label.setBackground(null);
            }
        });

        MenuItem delete = new MenuItem("Delete");

        delete.setOnAction(event -> {
            List<String> par = selected.getHolder().getParameters();
            par.remove(parameters.getChildren().indexOf(label));

            parameters.getChildren().remove(label);
        });

        label.setContextMenu(new ContextMenu(delete));
    }

    private void select(EditableLabel<EditableProcedure> label) {
        if (selected != null) selected.selectedProperty().setValue(false);

        parameters.getChildren().clear();

        selected = null;

        if (label != null) {
            code.setEditable(true);
            newPar.setDisable(false);
            label.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

            EditableProcedure p = label.getHolder();
            code.setText(p.getCode());

            p.getParameters().forEach(this::displayParameter);

        } else {
            code.setEditable(false);
            code.setText("");
            newPar.setDisable(true);

        }
        selected = label;
    }

    public void revert(Event event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to revert everything?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("");
        alert.setTitle("Revert everything?");
        Optional<ButtonType> t = alert.showAndWait();
        if (t.get() == ButtonType.YES) {
            reload();
        }
    }

    public void apply(Event event) {
        proceduresOrg.forEach(procedure -> procedureHandler.removeProcedure(procedure.getName()));

        proceduresNew.forEach(procedure -> procedureHandler.registerProcedure(procedure));

        reload();
    }

    public void addParameter(Event event) {
        int i = 0;
        String name = "param";
        List<String> par = selected.getHolder().getParameters();
        while (par.contains(name)) {
            i += 1;
            name = String.format("param%s", i);
        }
        displayParameter(name);
        par.add(name);
    }

    public void reload() {
        proceduresOrg = new ArrayList<>(procedureHandler.getProcedures().values());
        proceduresNew = new ArrayList<>();

        select(null);
        procedures.getChildren().clear();

        proceduresOrg.forEach(procedure -> {
            EditableProcedure p = new EditableProcedure(procedure);
            proceduresNew.add(p);
            displayProcedure(p, procedure);
        });
    }

    public void init() {
        code.textProperty().addListener((observable, oldValue, newValue) -> {
            if (selected != null) {
                selected.getHolder().setCode(newValue);
            }
        });
    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public Size getSize() {
        return new Size(node.getWidth(), node.getHeight());
    }

    private void draw() {
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
        newP.setPrefHeight(24);
        newP.setOnAction(this::newProcedure);
        StackPane.setAlignment(newP, Pos.CENTER_LEFT);

        Button revert = new Button("Revert");
        revert.setTranslateX(-96);
        revert.setPrefWidth(64);
        revert.setPrefHeight(24);
        revert.setOnAction(this::revert);
        StackPane.setAlignment(revert, Pos.CENTER_RIGHT);

        Button apply = new Button("Apply");
        apply.setTranslateX(-16);
        apply.setPrefWidth(64);
        apply.setPrefHeight(24);
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

        procedures = new VBox();
        ScrollPane proS = new ScrollPane(procedures);
        proS.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        proS.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        procedures.prefWidthProperty().bind(proS.widthProperty().subtract(2));
        proS.maxHeightProperty().bind(pro.heightProperty().subtract(proL.heightProperty()).subtract(2));
        proS.setStyle("-fx-focus-color: transparent; -fx-background-insets: 0;");
        StackPane.setAlignment(proS, Pos.BOTTOM_CENTER);

        procedures.heightProperty().addListener((observable, oldValue, newValue) -> proS.setVvalue(proS.getVmax()));

        pro.getChildren().addAll(proL, proS);

        //PARAMETERS
        Label parL = new Label(" Parameters");
        parL.setPrefHeight(24);
        parL.prefWidthProperty().bind(par.widthProperty());
        parL.setBorder(new Border(new BorderStroke(null, null, Color.GRAY, null,
                null, null, BorderStrokeStyle.SOLID, null, CornerRadii.EMPTY, BorderWidths.DEFAULT, Insets.EMPTY)));
        StackPane.setAlignment(parL, Pos.TOP_LEFT);

        par.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        newPar = new Button("Add");
        newPar.setPrefWidth(64);
        newPar.setMinHeight(0);
        newPar.setPrefHeight(16);
        newPar.setPadding(Insets.EMPTY);
        newPar.setTranslateY(4);
        newPar.setTranslateX(-16);
        newPar.setOnAction(this::addParameter);
        StackPane.setAlignment(newPar, Pos.TOP_RIGHT);

        parameters = new VBox();
        ScrollPane parS = new ScrollPane(parameters);
        parS.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        parS.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        parameters.prefWidthProperty().bind(parS.widthProperty().subtract(2));
        parS.maxHeightProperty().bind(par.heightProperty().subtract(parL.heightProperty()).subtract(2));
        parS.setStyle("-fx-focus-color: transparent; -fx-background-insets: 0;");
        StackPane.setAlignment(parS, Pos.BOTTOM_CENTER);

        par.getChildren().addAll(parL, parS, newPar);

        parameters.heightProperty().addListener((observable, oldValue, newValue) -> parS.setVvalue(parS.getVmax()));

        //CODE
        code.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        code.setStyle("-fx-background-insets: 0; -fx-background-color: transparent; -fx-background-radius: 0, 0, 0, 0;");

        node.getChildren().addAll(pro, par, code, buttons);
    }
}
