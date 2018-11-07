package nl.edulogo.javalogo;

import nl.edulogo.core.*;
import nl.edulogo.core.utils.MathUtil;
import nl.edulogo.display.fx.FXCanvas;
import nl.edulogo.javalogo.utils.ColorUtil;
import nl.edulogo.javalogo.variabele.InvoerVariabele;
import nl.edulogo.logo.Turtle;

public abstract class TekenApplet extends JavaLogo {
    private Canvas canvas;
    private Turtle turtle;

    public static void main(String[] args) {
        Starter.start();
    }

    private MouseHandler mouseHandler;

    @Override
    public Turtle getTurtle() {
        return turtle;
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void maakAnimatieMogelijk() {

    }

    @Override
    public void pauze(int millisec) {

    }

    @Override
    public boolean animatieLopend() {
        return false;
    }

    @Override
    public void beginAnimatie() {

    }

    @Override
    public void onderbreekAnimatie() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void achtergrondkleur(String kl) {
        canvas.fillScreen(ColorUtil.fromString(kl));
    }

    @Override
    public void achtergrondkleur(int r, int g, int b) {
        canvas.fillScreen(new Color(r, g, b));
    }

    @Override
    public void penUit() {
        turtle.setPenDown(false);
    }

    @Override
    public void penAan() {
        turtle.setPenDown(true);
    }

    @Override
    public void penAan(String kl) {
        turtle.setColor(ColorUtil.fromString(kl));
        turtle.setPenDown(true);
    }

    @Override
    public void penAan(int r, int g, int b) {
        turtle.setColor(new Color(r, g, b));
        turtle.setPenDown(true);
    }

    private Polygon vlak;
    private boolean vulAan = false;
    private boolean canDraw = false;

    public void start() {
        turtle = new Turtle(new Position(250, 250), 0);
        canvas = new FXCanvas(new Size(500, 500));
        initialiseer();

        //TODO CAN DRAW
        canDraw = true;
        tekenprogramma();
    }

    @Override
    public void stapx(double dx) {
        stap(dx, 0);
    }

    @Override
    public void stapy(double dy) {
        stap(0, dy);
    }

    @Override
    public void vooruit(double dy) {
        if (!canDraw) {
            return;
        }
        super.vooruit(dy);
        newLocation();
    }

    @Override
    public void stap(double dx, double dy) {
        if (!canDraw) {
            return;
        }
        Position newPos = MathUtil.getRelativePosition(turtle.getRotation(), -dy);
        newPos.addPosition(MathUtil.getRelativePosition(turtle.getRotation() + 90, dx));
        newPos.addPosition(turtle.getPosition());
        if (turtle.isPenDown()) canvas.drawLine(turtle.getPosition(), newPos);
        newLocation();
        turtle.setPosition(newPos);
    }

    private void newLocation() {
        if (vulAan) vlak.addPosition(turtle.getPosition());
    }

    @Override
    public void maakMuisActieMogelijk() {
        if (mouseHandler != null) return;
        mouseHandler = new MouseHandler(this);
        FXCanvas fxCanvas = (FXCanvas) canvas;
        fxCanvas.getNode().setEventDispatcher(mouseHandler);
    }

    @Override
    public int geefX() {
        return mouseHandler.getMouseX();
    }

    @Override
    public int geefY() {
        return mouseHandler.getMouseY();
    }

    @Override
    public int geefSleepdx() {
        return mouseHandler.getDraggedX();
    }

    @Override
    public int geefSleepdy() {
        return mouseHandler.getDraggedY();
    }

    @Override
    public void schrijf(String s) {
        if (!canDraw) {
            return;
        }
        canvas.write(s, turtle.getPosition(), turtle.getFont());
    }

    @Override
    public void schrijf(String s, Font f) {
        turtle.setFont(f);
        schrijf(s);
    }

    @Override
    public void maakTraceMogelijk() {

    }

    @Override
    public void maakZichtbaar(InvoerVariabele iv) {

    }

    @Override
    public int geefDrukx() {
        return mouseHandler.getMousePressX();
    }

    @Override
    public int geefDruky() {
        return mouseHandler.getMousePressY();
    }

    @Override
    public Polygon geefVlak() {
        return vlak;
    }

    @Override
    public void vulAan() {
        vulAan(Color.BLACK);
    }

    @Override
    public void vulAan(String kl) {
        vulAan(ColorUtil.fromString(kl));
    }

    @Override
    public void vulAan(int r, int g, int b) {
        vulAan(new Color(r, g, b));
    }

    public void vulAan(Color color) {
        if (!canDraw) {
            return;
        }
        turtle.setFillColor(color);
        vulAan = true;
        vlak = new Polygon();
    }

    @Override
    public void vulUit() {
        if (!vulAan) return;
        canvas.fillPolygon(geefVlak(), turtle.getFillColor());
        vulAan = false;
    }

    @Override
    public void tekenErbij() {

    }

    @Override
    public void tekenOpnieuw() {
        if (!canDraw) {
            return;
        }
        achtergrondkleur("wit");
        turtle = new Turtle(new Position(250, 250), 0);
        tekenprogramma();
    }

    @Override
    public void schaal(double s) {

    }
}
