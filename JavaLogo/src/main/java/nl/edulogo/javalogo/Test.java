package nl.edulogo.javalogo;

import nl.edulogo.core.Polygon;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Test extends TekenApplet {
    public static void main(String[] args) {
        launch();
    }

    double zijde, beginX, beginY;
    Polygon vlak;
    boolean raak;

    public void initialiseer() {
        maakMuisActieMogelijk();
        zijde = 100;
        beginX = 0;
        beginY = 0;
    }

    public void tekenprogramma() {
        penUit();
        stap(beginX, beginY);
        penAan();
        vierkant(zijde);
        vlak = geefVlak();
    }

    void vierkant(double z) {
        vulAan("rood");
        vooruit(100);
        rechts(50);
        vooruit(100);
        rechts(120);
        vooruit(110);
        rechts(90);
        vooruit(50);
        rechts(-50);
        vooruit(200);
        vulUit();
    }

    public void muisDrukActie() {
        int x = geefDrukx();
        int y = geefDruky();
        raak = vlak.contains(x, y);
    }

    public void muisSleepActie() {
        if (raak) {
            beginX = beginX + geefSleepdx();
            beginY = beginY + geefSleepdy();
            tekenOpnieuw();
        }
    }
}