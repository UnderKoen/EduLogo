package nl.edulogo.acslogo.display.fx;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import nl.edulogo.acslogo.Commons;

import java.util.function.UnaryOperator;

public class EditableLabel<T> extends StackPane {
    private static UnaryOperator<TextFormatter.Change> format = change -> {
        String newText = change.getControlNewText();
        if (Commons.REGEX_INPUT.matcher(newText).matches()) {
            return change;
        } else return null;
    };

    private T holder;
    private Property<String> text;
    private Property<Boolean> selected;
    private Label label;
    private TextField textField;
    private ContextMenu contextMenu;

    public void setContextMenu(ContextMenu contextMenu) {
        this.contextMenu = contextMenu;
    }

    public EditableLabel(String text, T holder) {
        this.text = new SimpleStringProperty(text);
        this.selected = new SimpleBooleanProperty(false);
        this.holder = holder;
        draw();
        setOnMouseClicked(this::mouseClicked);
    }

    public T getHolder() {
        return holder;
    }

    public void setText(String text) {
        this.text.setValue(text);
    }

    public Property<String> textProperty() {
        return text;
    }

    public Property<Boolean> selectedProperty() {
        return selected;
    }

    private void mouseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            textField = new TextField(text.getValue());
            textField.setTextFormatter(new TextFormatter<>(format));
            getChildren().add(textField);

            textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) done();
            });

            textField.setOnKeyReleased(event1 -> {
                if (event1.getCode() == KeyCode.ENTER) done();
            });

            textField.requestFocus();
            textField.selectAll();
        } else if (event.getButton() == MouseButton.SECONDARY) {
            if (contextMenu != null) contextMenu.show(this, event.getScreenX(), event.getScreenY());
            return;
        }
        selected.setValue(true);
        if (contextMenu != null) contextMenu.hide();
    }

    public void setHolder(T holder) {
        this.holder = holder;
    }

    private void done() {
        setText(textField.getText());
        getChildren().remove(textField);
    }

    private void draw() {
        label = new Label();
        label.textProperty().bind(text);
        label.setPadding(new Insets(4));
        label.maxWidthProperty().bind(widthProperty());

        this.getChildren().add(label);
    }
}
