package nl.edulogo.display;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import nl.edulogo.core.*;

/**
 * Created by Under_Koen on 10/09/2018.
 */
public class FxDisplay implements Display {
    private Canvas canvas;
    private GraphicsContext graphics;
    private Size size;

    public FxDisplay(Size size) {
        this.size = size;
        canvas = new Canvas(size.getWidth(), size.getHeight());
        graphics = canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void setColor(Color color) {
        javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.color(color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0);
        graphics.setStroke(fxColor);
        graphics.setFill(fxColor);
    }

    @Override
    public void drawLine(Position from, Position to) {
        drawLine(from, to, Color.BLACK);
    }

    @Override
    public void drawLine(Position from, Position to, Color color) {
        setColor(color);
        graphics.strokeLine(from.getX(), from.getY(), to.getX(), to.getY());
    }

    @Override
    public void drawDot(Position position) {
        drawDot(position, Color.BLACK);
    }

    @Override
    public void drawDot(Position position, Color color) {
        setColor(color);
        graphics.strokeLine(position.getX(), position.getY(), position.getX(), position.getY());
    }

    private void drawImage(javafx.scene.image.Image image, Position position, Size size) {
        graphics.drawImage(image, position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    @Override
    public void drawImage(Image image, Position position) {
        javafx.scene.image.Image fxImage = new javafx.scene.image.Image(image.getFile().toURI().toString());
        drawImage(fxImage, position, new Size(fxImage.getWidth(), fxImage.getHeight()));
    }

    @Override
    public void drawImage(Image image, Position position, Size size) {
        javafx.scene.image.Image fxImage = new javafx.scene.image.Image(image.getFile().toURI().toString());
        drawImage(fxImage, position, size);
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
        canvas.setHeight(size.getHeight());
        canvas.setWidth(size.getWidth());
    }

    private void setFont(Font font) {
        setColor(font.getColor());
        graphics.setFont(new javafx.scene.text.Font(font.getName(), font.getSize()));
    }

    @Override
    public void write(String text, Position position) {
        write(text, position, Font.SYSTEM_REGULAR);
    }

    @Override
    public void write(String text, Position position, Font font) {
        setFont(font);
        graphics.fillText(text, position.getX(), position.getY());
    }

    @Override
    public void fillScreen(Color color) {
        setColor(color);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
    }

    @Override
    public void fillPolygon(Polygon polygon, Color color) {
        Position[] positions = polygon.getPositions();
        double[] x = new double[positions.length];
        double[] y = new double[positions.length];
        for (int i = 0; i < positions.length; i++) {
            Position position = positions[i];
            x[i] = position.getX();
            y[i] = position.getY();
        }

        setColor(color);
        graphics.fillPolygon(x, y, positions.length);
    }

    @Override
    public void clear() {
        graphics.clearRect(0, 0, size.getWidth(), size.getHeight());
    }
}
