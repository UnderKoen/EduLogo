package nl.edulogo.javalogo;

import nl.edulogo.javalogo.variabele.InvoerVariabele;
import nl.edulogo.javalogo.variabele.SchuifInvoerVariabele;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Test extends TekenApplet {

    double rot = 0;
    InvoerVariabele rood = new InvoerVariabele("rood", 0, 255, 255);
    SchuifInvoerVariabele groen = new SchuifInvoerVariabele("groen", 0, 255, 255);
    InvoerVariabele blauw = new InvoerVariabele("blauw", 0, 255, 255);

    public void initialiseer() {
        setSize(800, 800);
        achtergrondkleur("wit");
        maakAnimatieMogelijk();
        maakTraceMogelijk();
        maakZichtbaar(rood);
        maakZichtbaar(groen);
        maakZichtbaar(blauw);
    }

    @Override
    public void tekenprogramma() {
        rechts(rot);
        vulAan(rood.geefWaarde(), groen.geefWaarde(), blauw.geefWaarde());
        for (int i = 0; i < 4; i++) {
            vooruit(100);
            rechts(90);
        }
        vulUit();
    }

    @Override
    public void invoerVarActie(InvoerVariabele iv) {
        tekenOpnieuw();
    }


    @Override
    public void schuifInvoerVarActie(SchuifInvoerVariabele iv) {
        tekenOpnieuw();
    }
}