package nl.edulogo.javalogo;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Test extends TekenApplet {
    double rot = 0;

    public void initialiseer() {
        maakAnimatieMogelijk();
    }

    public void tekenprogramma() {
        rechts(rot);
        vooruit(100);
    }

    public void animatie() {
        while (animatieLopend()) {
            rot += 0.1;
            tekenOpnieuw();
            if (rot % 500 == 0) System.out.println(rot);
            //woops I did it again 0.o
            pauze(10);
        }
    }
}