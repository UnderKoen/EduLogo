package nl.edulogo.acslogo.display.fx;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import nl.edulogo.acslogo.display.TurtleGraphics;
import nl.edulogo.core.Image;
import nl.edulogo.core.Position;
import nl.edulogo.display.fx.FXCanvas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FXTurtleGraphics implements TurtleGraphics<FXCanvas> {
    private StackPane node;
    private ImageView image;

    public FXTurtleGraphics(int size) {
        node = new StackPane();

        image = new ImageView();
        image.setFitWidth(size);
        image.setFitHeight(size);
        node.getChildren().add(image);
    }

    @Override
    public void setRotation(double degrees) {
        image.setRotate(degrees);
    }

    @Override
    public void setPosition(Position position) {
        image.setTranslateX(position.getX());
        image.setTranslateY(position.getY());
    }

    @Override
    public void setAlpha(double alpha) {
        image.setOpacity(alpha);
    }

    @Override
    public void setImage(Image image) {
        try {
            this.image.setImage(new javafx.scene.image.Image(new FileInputStream(image.getFile())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    public void addToCanvas(FXCanvas canvas) {
        StackPane parent = (StackPane) canvas.getCanvas().getParent();
        parent.getChildren().add(node);
        node.addEventHandler(MouseEvent.ANY, canvas.getNode()::fireEvent);
    }
}
