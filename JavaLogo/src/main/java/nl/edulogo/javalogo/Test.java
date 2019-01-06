package nl.edulogo.javalogo;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Test extends TekenApplet {

    double rot = 0;

    public void initialiseer() {
        achtergrondkleur("wit");
        //maakAnimatieMogelijk();
        maakTraceMogelijk();
    }

    @Override
    public void tekenprogramma() {
        rechts(rot);
        //vulAan("zwart");
        for (int i = 0; i < 9; i++) {
            vooruit(100);
            rechts(360 / 9);
        }
        //vulUit();
        schrijf("dood aan koen");
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