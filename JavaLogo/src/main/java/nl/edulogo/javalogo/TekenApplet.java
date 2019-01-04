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
    private TraceHandler traceHandler;
    private AnimationHandler animationHandler;
    private MouseHandler mouseHandler;

    public TraceHandler getTraceHandler() {
        return traceHandler;
    }

    public static void main(String[] args) {
        Starter.start();
    }

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
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public boolean animatieLopend() {
        return animationHandler.isAnimation();
    }

    @Override
    public void beginAnimatie() {
        animationHandler.startAnimation();
    }

    @Override
    public void onderbreekAnimatie() {
        animationHandler.stopAnimatie();
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
        traceHandler.addTrace("penUit()");
    }

    @Override
    public void penAan() {
        turtle.setPenDown(true);
        traceHandler.addTrace("penAan()");
    }

    @Override
    public void penAan(String kl) {
        turtle.setColor(ColorUtil.fromString(kl));
        turtle.setPenDown(true);
        traceHandler.addTrace("penAan(" + kl + ")");
    }

    @Override
    public void penAan(int r, int g, int b) {
        turtle.setColor(new Color(r, g, b));
        turtle.setPenDown(true);
        traceHandler.addTrace("penAan(" + r + g + b + ")");
    }

    private Polygon vlak;
    private boolean vulAan = false;
    private boolean canDraw = false;

    public void start() {
        turtle = new Turtle(new Position(250, 250), 0);
        canvas = new FXCanvas(new Size(500, 500));
        traceHandler = new TraceHandler();
        animationHandler = new AnimationHandler(this);
        initialiseer();

        //TODO CAN DRAW
        canDraw = true;
        tekenprogramma();
    }

    @Override
    public void stapx(double dx) {
        stap(dx, 0);
        traceHandler.addTrace("stapx(" + (int) Math.rint(dx) + ")");
    }

    @Override
    public void stapy(double dy) {
        stap(0, dy);
        traceHandler.addTrace("stapy(" + (int) Math.rint(dy) + ")");
    }

    @Override
    public void vooruit(double dy) {
        if (!canDraw) {
            return;
        }
        super.vooruit(dy);
        newLocation();
        traceHandler.addTrace("vooruit(" + dy + ")");
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
        traceHandler.addTrace("stap(" + (int) Math.rint(dx) + "," + (int) Math.rint(dy) + ")");
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
        traceHandler.addTrace("schrijf()");
    }

    @Override
    public void schrijf(String s, Font f) {
        turtle.setFont(f);
        schrijf(s);
        traceHandler.addTrace("schrijf()");
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
        traceHandler.addTrace("vulAan()");
    }

    @Override
    public void vulAan(String kl) {
        vulAan(ColorUtil.fromString(kl));
        traceHandler.addTrace("vulAan(" + kl + ")");
    }

    @Override
    public void vulAan(int r, int g, int b) {
        vulAan(new Color(r, g, b));
        traceHandler.addTrace("vulAan(" + r + g + b + ")");
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
