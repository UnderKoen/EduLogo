package nl.edulogo.javalogo;

import nl.edulogo.core.Font;
import nl.edulogo.core.Polygon;
import nl.edulogo.javalogo.variabele.InvoerVariabele;
import nl.edulogo.javalogo.variabele.SchuifInvoerVariabele;
import nl.edulogo.logo.Logo;

/**
 * Created by Under_Koen on 25/09/2018.
 */
abstract class JavaLogo extends Logo {
    //Animatie
    public abstract void maakAnimatieMogelijk();

    public abstract void pauze(int millisec);

    public abstract boolean animatieLopend();

    public abstract void beginAnimatie();

    public abstract void onderbreekAnimatie();

    public abstract void stop();

    public void animatie() {
    }

    //Achtergrond
    public abstract void achtergrondkleur(String kl);

    public abstract void achtergrondkleur(int r, int g, int b);

    //Pen
    public abstract void penUit();

    public abstract void penAan();

    public abstract void penAan(String kl);

    public abstract void penAan(int r, int g, int b);

    //Bewegen
    public void links(double dHoek) {
        left(dHoek);
    }

    public void rechts(double dHoek) {
        right(dHoek);
    }

    public void vooruit(double dy) {
        forward(dy);
    }

    public abstract void stap(double dx, double dy);

    public abstract void stapx(double dx);

    public abstract void stapy(double dy);

    //Muis
    public abstract void maakMuisActieMogelijk();

    public abstract int geefX();

    public abstract int geefY();

    public abstract int geefSleepdx();

    public abstract int geefSleepdy();

    public abstract int geefDrukx();

    public abstract int geefDruky();

    public void muisDrukActie() {
    }

    public void muisLosActie() {
    }

    public void muisSleepActie() {
    }

    public void muisBeweegActie() {
    }

    //Tekst
    public abstract void schrijf(String s);

    public abstract void schrijf(String s, Font f);

    //Trace
    public abstract void maakTraceMogelijk();

    //Invoer Variabele
    public abstract void maakZichtbaar(InvoerVariabele iv);

    void invoerVarActie(InvoerVariabele iv) {
    }

    void schuifInvoerVarActie(SchuifInvoerVariabele iv) {
    }

    //Vullen
    public abstract Polygon geefVlak();

    public abstract void vulAan();

    public abstract void vulAan(String kl);

    public abstract void vulAan(int r, int g, int b);

    public abstract void vulUit();

    //Ander
    public abstract void tekenErbij();

    public abstract void tekenOpnieuw();

    public abstract void schaal(double s);

    void initialiseer() {
    }

    void tekenprogramma() {
    }
}
