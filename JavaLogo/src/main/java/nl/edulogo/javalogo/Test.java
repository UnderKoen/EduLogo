package nl.edulogo.javalogo;

import nl.edulogo.core.Polygon;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Test extends TekenApplet {
    double zijde, beginX, beginY;
    Polygon vlak;
    boolean raak;
    double rot = 0;

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
        rechts(rot);
        for (int i2 = 0; i2 < 10; i2++) {
            for (int i = 0; i < 90; i++) {
                rechts(1 + i2 * 2 * 0.05);
                vooruit(1 * i2 * 2 * 0.2);
            }
            rechts(3);
        }
        schrijf("hallo");
    }

    public void animatie() {
        while (animatieLopend()) {
            rot += 0.1;
            tekenOpnieuw();
            if (rot % 500 == 0) System.out.println(rot);
            //woops I did it again 0.o
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }
}