package nl.edulogo.javalogo;

import nl.edulogo.javalogo.variabele.InvoerVariabele;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Test extends TekenApplet {

    @Override
    public void initialiseer() {
        setSize(500, 500);
        maakTraceMogelijk();
        maakZichtbaar(new InvoerVariabele("t", 0, 10, -10));
    }

    @Override
    public void tekenprogramma() {
        links(90);
        vooruit(100);
        rechts(90);
        vooruit(100);
        links(90);
        vooruit(100);
        penUit();
        rechts(180);
        vooruit(300);
        penAan("rood");
        links(90);
        vooruit(100);
    }
}