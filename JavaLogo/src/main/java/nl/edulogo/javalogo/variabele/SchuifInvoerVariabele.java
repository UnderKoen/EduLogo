package nl.edulogo.javalogo.variabele;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import nl.edulogo.javalogo.TekenApplet;

/**
 * Created by Under_Koen on 25/09/2018.
 */
public class SchuifInvoerVariabele extends InvoerVariabele {

    public SchuifInvoerVariabele(String nm, int mn, int mx, int w) {
        super(nm, mn, mx, w);
    }

    public static class SchuifInvoerVariabeleNode extends StackPane {

        public SchuifInvoerVariabeleNode(TekenApplet applet, SchuifInvoerVariabele var) {

            if (!var.isEnabled()) setDisabled(true);

            Label nameLabel = new Label();
            nameLabel.setText(var.getName());
            nameLabel.setTranslateY(-31);

            setMaxSize(100, 26);

            Label minLabel = new Label();
            minLabel.setText(String.valueOf(var.getMinimum()));
            minLabel.setTranslateY(-13);

            Label maxLabel = new Label();
            maxLabel.setText(String.valueOf(var.getMaximum()));
            maxLabel.setTranslateY(-13);

            Label valLabel = new Label();
            valLabel.setText(String.valueOf(var.geefWaarde()));
            valLabel.setTranslateY(15);

            Slider numberSlider = new Slider();
            numberSlider.setMax(var.getMaximum());
            numberSlider.setMaxSize(150, 15);
            numberSlider.setMinSize(150, 15);
            numberSlider.setMin(var.getMinimum());
            numberSlider.setValue(var.geefWaarde());
            numberSlider.setOnMouseDragged(event -> {
                var.zetWaarde((int) Math.round(Double.valueOf(numberSlider.getValue())));
                valLabel.setText(String.valueOf(var.geefWaarde()));
                applet.schuifInvoerVarActie(var);
            });

            StackPane.setAlignment(nameLabel, Pos.CENTER_LEFT);
            StackPane.setAlignment(numberSlider, Pos.CENTER);
            StackPane.setAlignment(minLabel, Pos.CENTER_LEFT);
            StackPane.setAlignment(valLabel, Pos.CENTER_LEFT);
            StackPane.setAlignment(maxLabel, Pos.CENTER_RIGHT);

            getChildren().addAll(nameLabel, numberSlider, maxLabel, minLabel, valLabel);

        }

    }
}
