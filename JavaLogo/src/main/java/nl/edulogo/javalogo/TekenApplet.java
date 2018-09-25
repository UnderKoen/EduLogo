package nl.edulogo.javalogo;

import nl.edulogo.core.*;
import nl.edulogo.core.utils.MathUtil;
import nl.edulogo.display.fx.FXCanvas;
import nl.edulogo.javalogo.utils.ColorUtil;
import nl.edulogo.javalogo.variabele.InvoerVariabele;
import nl.edulogo.logo.Turtle;

public abstract class TekenApplet implements JavaLogo {
    protected Canvas canvas;
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
    public abstract void tekenprogramma();

    @Override
    public abstract void initialiseer();

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

    }

    @Override
    public void penAan() {

    }

    @Override
    public void penAan(String kl) {

    }

    @Override
    public void penAan(int r, int g, int b) {

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
    public void rechts(double dHoek) {
        double rotation = turtle.getRotation();
        rotation -= dHoek;
        rotation = rotation % 360;
        if (rotation < 0) rotation = 360 + rotation;
        turtle.setRotation(rotation);
    }

    @Override
    public void links(double dHoek) {
        rechts(-dHoek);
    }

    @Override
    public void vooruit(double dy) {
        Position newPos = MathUtil.getRelativePosition(turtle.getRotation(), -dy);
        newPos.addPosition(turtle.getPosition());
        canvas.drawLine(turtle.getPosition(), newPos);
        turtle.setPosition(newPos);
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

    }

    @Override
    public void stapy(double dy) {

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

    }

    @Override
    public void schrijf(String s, Font f) {

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
