package nl.edulogo.acslogo.display.fx;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import nl.edulogo.acslogo.ACSLogo;
import nl.edulogo.acslogo.display.TurtleGraphics;
import nl.edulogo.core.Position;
import nl.edulogo.display.fx.FXCanvas;

public class FXTurtleGraphics implements TurtleGraphics<FXCanvas> {
    private StackPane node;
    private ImageView image;
    private SimpleObjectProperty<Double> rotate;
    private SimpleObjectProperty<Double> x;
    private SimpleObjectProperty<Double> y;

    public FXTurtleGraphics(int size) {
        node = new StackPane();

        image = new ImageView();
        image.setFitWidth(size);
        image.setFitHeight(size);
        node.getChildren().add(image);

        rotate = new SimpleObjectProperty<>(0.0);
        image.rotateProperty().bind(rotate);

        x = new SimpleObjectProperty<>(image.getTranslateX());
        y = new SimpleObjectProperty<>(image.getTranslateY());

        image.translateXProperty().bind(x);
        image.translateYProperty().bind(y);
    }

    @Override
    public void setRotation(double degrees) {
        Platform.runLater(() -> {
            rotate.set(degrees);
        });
    }

    @Override
    public void setPosition(Position position) {
        Platform.runLater(() -> {
            x.set(position.getX());
            y.set(position.getY());
        });
    }

    @Override
    public void setAlpha(double alpha) {
        image.setOpacity(alpha);
    }

    @Override
    public void setImage(String file) {
        this.image.setImage(new javafx.scene.image.Image(ACSLogo.class.getClassLoader().getResourceAsStream(file)));
    }

    @Override
    public void show() {
        image.setVisible(true);
    }

    @Override
    public void hide() {
        image.setVisible(false);
    }

    @Override
    public boolean isVisible() {
        return image.isVisible();
    }

    @Override
    public void addToCanvas(FXCanvas canvas) {
        StackPane parent = (StackPane) canvas.getCanvas().getParent();
        parent.getChildren().add(node);
        node.addEventHandler(MouseEvent.ANY, canvas.getNode()::fireEvent);
    }
}
