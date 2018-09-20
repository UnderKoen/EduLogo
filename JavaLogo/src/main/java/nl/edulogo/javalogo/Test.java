package nl.edulogo.javalogo;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Test extends TekenApplet {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void tekenapplet() {
        System.out.println("de");
        vooruit(100);
    }

    @Override
    void initialiseer() {

    }
}