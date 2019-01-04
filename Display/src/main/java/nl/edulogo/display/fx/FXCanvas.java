package nl.edulogo.display.fx;

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import nl.edulogo.core.*;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class FXCanvas implements Canvas, FXView {
    private javafx.scene.canvas.Canvas canvas;
    private GraphicsContext graphics;
    private Size size;

    public FXCanvas(Size size) {
        this.size = size;
        canvas = new javafx.scene.canvas.Canvas(size.getWidth(), size.getHeight());
        graphics = canvas.getGraphicsContext2D();
    }

    protected javafx.scene.canvas.Canvas getCanvas() {
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
        graphics.strokeLine(from.getX() + 0.5, from.getY() + 0.5, to.getX() + 0.5, to.getY() + 0.5);
    }

    @Override
    public void drawDot(Position position) {
        drawDot(position, Color.BLACK);
    }

    @Override
    public void drawDot(Position position, Color color) {
        setColor(color);
        graphics.strokeLine(position.getX() + 0.5, position.getY() + 0.5, position.getX() + 0.5, position.getY() + 0.5);
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
    public void arc(Position center, double radiusX, double radiusY, double startAngle, double length) {
        arc(center, radiusX, radiusY, startAngle, length, Color.BLACK);
    }

    @Override
    public void arc(Position center, double radiusX, double radiusY, double startAngle, double length, Color color) {
        setColor(color);
        graphics.strokeArc(center.getX() - radiusX, center.getY() - radiusY, radiusX * 2, radiusY * 2, startAngle, length, ArcType.OPEN);
    }

    @Override
    public void rotate(double degrees) {
        graphics.rotate(degrees);
    }

    @Override
    public void setPenWidth(double width) {
        graphics.setLineWidth(width);
    }

    @Override
    public void translate(Position position) {
        graphics.translate(position.getX(), position.getY());
    }

    @Override
    public void clear() {
        graphics.clearRect(0, 0, size.getWidth(), size.getHeight());
    }

    @Override
    public Node getNode() {
        return canvas;
    }
}