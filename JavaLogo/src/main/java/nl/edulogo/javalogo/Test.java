package nl.edulogo.javalogo;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Test extends TekenApplet {

    double rot = 0;

    public void initialiseer() {
        achtergrondkleur("rood");
        //maakAnimatieMogelijk();
        maakTraceMogelijk();
    }

    public void tekenprogramma() {
        vulAan("wit");
        for (int i = 0; i < 360; i++) {
            rechts(1);
            vooruit(1);
        }
        rechts(180);
        vulUit();
        test();
    }

    public void animatie() {
        while (animatieLopend()) {
            rot += 0.1;
            tekenOpnieuw();
            //woops I did it again 0.o
            pauze(10);
        }
    }
}