package nl.edulogo.javalogo;

import nl.edulogo.javalogo.variabele.InvoerVariabele;
import nl.edulogo.javalogo.variabele.SchuifInvoerVariabele;

/**
 * Created by Under_Koen, D0an on 20/09/2018.
 */
public class Test extends TekenApplet {

    @Override
    public void initialiseer() {
        setSize(500, 500);
        maakTraceMogelijk();
        maakAnimatieMogelijk();
        achtergrondkleur("geel");
        maakZichtbaar(new SchuifInvoerVariabele("Grootte", 0, 1000, 100));
        maakZichtbaar(new InvoerVariabele("Rood", 0, 255, 255));
        maakZichtbaar(new InvoerVariabele("Groen", 0, 255, 255));
        maakZichtbaar(new InvoerVariabele("Blauw", 0, 255, 255));
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