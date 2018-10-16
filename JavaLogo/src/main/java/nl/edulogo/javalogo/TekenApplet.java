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

    public static void launch() {
        Starter.start();
    }

    public void start() {
        initialiseer();
        turtle = new Turtle(new Position(250, 250), 0);
        canvas = new FXCanvas(new Size(500, 500));
    }

    @Override
    public Turtle getTurtle() {
        return turtle.clone();
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

    @Override
    public int geefX() {
        return 0;
    }

    @Override
    public int geefY() {
        return 0;
    }

    @Override
    public void stap(double dx, double dy) {
        Position newPos = MathUtil.getRelativePosition(turtle.getRotation(), -dy);
        newPos.addPosition(MathUtil.getRelativePosition(turtle.getRotation() + 90, dx));
        newPos.addPosition(turtle.getPosition());
        canvas.drawLine(turtle.getPosition(), newPos);
        turtle.setPosition(newPos);
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
    public void maakMuisActieMogelijk() {

    }

    @Override
    public int geefSleepdx() {
        return 0;
    }

    @Override
    public int geefSleepdy() {
        return 0;
    }

    @Override
    public int geefDrukx() {
        return 0;
    }

    @Override
    public int geefDruky() {
        return 0;
    }

    @Override
    public void schrijf(String s) {
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
    public Polygon geefVlak() {
        return null;
    }

    @Override
    public void vulAan() {

    }

    @Override
    public void vulAan(String kl) {

    }

    @Override
    public void vulAan(int r, int g, int b) {

    }

    @Override
    public void vulUit() {

    }

    @Override
    public void tekenErbij() {

    }

    @Override
    public void tekenOpnieuw() {

    }

    @Override
    public void schaal(double s) {

    }
}
