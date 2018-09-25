package nl.edulogo.javalogo;

import nl.edulogo.core.Font;
import nl.edulogo.core.Polygon;
import nl.edulogo.javalogo.variabele.InvoerVariabele;
import nl.edulogo.javalogo.variabele.SchuifInvoerVariabele;
import nl.edulogo.logo.Logo;

/**
 * Created by Under_Koen on 25/09/2018.
 */
interface JavaLogo extends Logo {
    //Logo extend
    @Override
    default void forward(double amount) {
        vooruit(amount);
    }

    @Override
    default void right(double rotation) {
        rechts(rotation);
    }

    @Override
    default void left(double rotation) {
        links(rotation);
    }

    //Animatie
    void maakAnimatieMogelijk();

    void pauze(int millisec);

    boolean animatieLopend();

    void beginAnimatie();

    void onderbreekAnimatie();

    void stop();

    default void animatie() {
    }

    //Achtergrond
    void achtergrondkleur(String kl);

    void achtergrondkleur(int r, int g, int b);

    //Pen
    void penUit();

    void penAan();

    void penAan(String kl);

    void penAan(int r, int g, int b);

    //Bewegen
    int geefX();

    int geefY();

    void links(double dHoek);

    void rechts(double dHoek);

    void vooruit(double dy);

    void stap(double dx, double dy);

    void stapx(double dx);

    void stapy(double dy);

    //Muis
    void maakMuisActieMogelijk();

    int geefSleepdx();

    int geefSleepdy();

    int geefDrukx();

    int geefDruky();

    default void muisDrukActie() {
    }

    default void muisLosActie() {
    }

    default void muisSleepActie() {
    }

    //Tekst
    void schrijf(String s);

    void schrijf(String s, Font f);

    //Trace
    void maakTraceMogelijk();

    //Invoer Variabele
    void maakZichtbaar(InvoerVariabele iv);

    default void invoerVarActie(InvoerVariabele iv) {
    }

    default void schuifInvoerVarActie(SchuifInvoerVariabele iv) {
    }

    //Vullen
    Polygon geefVlak();

    void vulAan();

    void vulAan(String kl);

    void vulAan(int r, int g, int b);

    void vulUit();

    //Ander
    void tekenErbij();

    void tekenOpnieuw();

    void schaal(double s);

    default void initialiseer() {
    }

    default void tekenprogramma() {
    }
}
