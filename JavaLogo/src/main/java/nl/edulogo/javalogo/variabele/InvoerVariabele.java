package nl.edulogo.javalogo.variabele;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import nl.edulogo.javalogo.TekenApplet;

/**
 * Created by Under_Koen on 25/09/2018.
 */
public class InvoerVariabele {
    private String naam;
    private boolean enabled;
    private int min;
    private int max;
    private int val;

    public InvoerVariabele(String nm, int mn, int mx, int w) {
        naam = nm;
        min = mn;
        max = mx;
        val = w;
        enabled = true;
    }

    public void zetInvoerUit() {
        enabled = false;
    }

    public void zetInvoerAan() {
        enabled = true;
    }

    public void zetWaarde(int newval) {
        if (newval >= max) {
            val = max;
        } else if (newval <= min) {
            val = min;
        } else {
            val = newval;
        }
    }

    public String getName() {
        return naam;
    }

    public int getMinimum() {
        return min;
    }

    public int getMaximum() {
        return max;
    }

    public int geefWaarde() {
        return val;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public static class InvoerVariabeleNode extends StackPane {

        public InvoerVariabeleNode(TekenApplet applet, InvoerVariabele var) {

            if (!var.isEnabled()) setDisabled(true);

            Label nameLabel = new Label();
            nameLabel.setText(var.getName());
            nameLabel.setTranslateY(-24);

            setMaxSize(150, 26);
            TextField numberInput = new TextField();
            numberInput.setText(String.valueOf(var.geefWaarde()));
            numberInput.setMaxSize(150, 26);
            numberInput.setMinSize(150, 26);
            numberInput.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    try {
                        var.zetWaarde((int) Math.round(Double.valueOf(numberInput.getText())));
                    } catch (Exception e) {
                    }
                    numberInput.setText(String.valueOf(var.geefWaarde()));
                    applet.invoerVarActie(var);
                }
            });

            Button upValue = new Button("+");
            upValue.setPadding(Insets.EMPTY);
            upValue.setMaxSize(50, 13);
            upValue.setMinSize(50, 13);
            upValue.setOnAction(event -> {
                int newval = var.geefWaarde() + 1;
                var.zetWaarde(newval);
                numberInput.setText(String.valueOf(var.geefWaarde()));
                applet.invoerVarActie(var);
            });

            Button downValue = new Button("-");
            downValue.setPadding(Insets.EMPTY);
            downValue.setMaxSize(50, 13);
            downValue.setMinSize(50, 13);
            downValue.setOnAction(event -> {
                int newval = var.geefWaarde() - 1;
                var.zetWaarde(newval);
                numberInput.setText(String.valueOf(var.geefWaarde()));
                applet.invoerVarActie(var);
            });

            StackPane.setAlignment(upValue, Pos.TOP_RIGHT);
            StackPane.setAlignment(nameLabel, Pos.CENTER_LEFT);
            StackPane.setAlignment(downValue, Pos.BOTTOM_RIGHT);
            StackPane.setAlignment(numberInput, Pos.CENTER_LEFT);

            getChildren().addAll(nameLabel, numberInput, upValue, downValue);

        }

    }

}
