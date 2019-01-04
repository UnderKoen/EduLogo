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
        System.out.println("doet dit iets 2");
        while (animatieLopend()) {
            rot += 0.1;
            tekenOpnieuw();
            System.out.println("doet dit iets");
            //woops I did it again 0.o
            pauze(10);
        }
    }
}