package nl.edulogo.javalogo;

import nl.edulogo.core.*;
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

    private Color achtergrond;

    @Override
    public void maakAnimatieMogelijk() {
        animationHandler.maakMogelijk();
    }

    @Override
    public void pauze(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ignored) {
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

    public boolean isAnimatieMogelijk() {
        return animationHandler.isMogelijk();
    }

    @Override
    public void stop() {
        if (this.animatieLopend()) {
            this.onderbreekAnimatie();
        }
    }

    @Override
    public void achtergrondkleur(int r, int g, int b) {
        achtergrond = new Color(r, g, b);
        fillScreen(achtergrond);
    }

    @Override
    public void penUit() {
        turtle.setPenDown(false);
        traceHandler.addTrace(new Trace(Trace.TraceSoort.PENUIT));
    }

    @Override
    public void penAan() {
        turtle.setPenDown(true);
        traceHandler.addTrace(new Trace(Trace.TraceSoort.PENAAN));
    }

    @Override
    public void penAan(String kl) {
        turtle.setColor(ColorUtil.fromString(kl));
        turtle.setPenDown(true);
        traceHandler.addTrace(new Trace(Trace.TraceSoort.PENAAN, kl));
    }

    @Override
    public void penAan(int r, int g, int b) {
        turtle.setColor(new Color(r, g, b));
        turtle.setPenDown(true);
        traceHandler.addTrace(new Trace(Trace.TraceSoort.PENAAN, r, g, b));
    }

    private Polygon vlak;

    @Override
    public void achtergrondkleur(String kl) {
        if (!kl.equals("default")) achtergrond = ColorUtil.fromString(kl);
        fillScreen(achtergrond);
    }

    private boolean vulAan = false;
    private boolean canDraw = false;

    public void start() {
        turtle = new Turtle(new Position(250, 250), 0);
        canvas = new FXCanvas(new Size(500, 500));
        traceHandler = new TraceHandler();
        animationHandler = new AnimationHandler(this);
        achtergrondkleur("wit");
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
        traceHandler.addTrace(new Trace(Trace.TraceSoort.VOORUIT, dy));
    }

    @Override
    public void stap(double dx, double dy) {
        if (!canDraw) {
            return;
        }
        step(dx, dy);
        traceHandler.addTrace(new Trace(Trace.TraceSoort.STAP, (int) Math.rint(dx), (int) Math.rint(dy)));
    }

    private void newLocation() {
        if (vulAan) vlak.addPosition(turtle.getPosition());
    }

    @Override
    public void maakMuisActieMogelijk() {
        if (mouseHandler != null) return;
        mouseHandler = new MouseHandler(this);
        FXCanvas fxCanvas = (FXCanvas) getCanvas();
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
        write(s);
        traceHandler.addTrace(new Trace(Trace.TraceSoort.SCHRIJF));
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
        traceHandler.addTrace(new Trace(Trace.TraceSoort.VULAAN));
    }

    @Override
    public void vulAan(String kl) {
        vulAan(ColorUtil.fromString(kl));
        traceHandler.addTrace(new Trace(Trace.TraceSoort.VULAAN, kl));
    }

    @Override
    public void vulAan(int r, int g, int b) {
        vulAan(new Color(r, g, b));
        traceHandler.addTrace(new Trace(Trace.TraceSoort.VULAAN, r, g, b));
    }

    @Override
    public void rechts(double dHoek) {
        traceHandler.addTrace(new Trace(Trace.TraceSoort.RECHTS, dHoek));
        super.rechts(dHoek);
    }

    @Override
    public void links(double dHoek) {
        traceHandler.addTrace(new Trace(Trace.TraceSoort.LINKS, dHoek));
        super.links(dHoek);
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
        fillPath();
        //canvas.fillPolygon(geefVlak(), turtle.getFillColor());
        vulAan = false;
        traceHandler.addTrace(new Trace(Trace.TraceSoort.VULUIT));
    }

    @Override
    public void tekenErbij() {

    }

    @Override
    public void tekenOpnieuw() {
        if (!canDraw) {
            return;
        }
        achtergrondkleur("default");
        turtle = new Turtle(new Position(250, 250), 0);
        tekenprogramma();
    }

    @Override
    public void schaal(double s) {

    }
}
