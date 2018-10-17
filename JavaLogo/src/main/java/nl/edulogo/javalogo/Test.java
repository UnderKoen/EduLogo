package nl.edulogo.javalogo;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Test extends TekenApplet {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void tekenprogramma() {
        vulAan("rood");
        for (int i = 0; i < 360; i++) {
            links(1);
            vooruit(1);
        }
        vulUit();
        vulAan(0, 0, 255);
        for (int i = 0; i < 360; i++) {
            rechts(1);
            vooruit(1);
        }
        vulUit();
    }

    @Override
    public void initialiseer() {

    }
}