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
        achtergrondkleur("rood");
        vooruit(100);
        rechts(90);
        vooruit(100);
    }

    @Override
    public void initialiseer() {

    }
}