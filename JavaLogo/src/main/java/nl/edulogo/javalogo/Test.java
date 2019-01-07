package nl.edulogo.javalogo;

import nl.edulogo.javalogo.variabele.InvoerVariabele;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Test extends TekenApplet {

    public InvoerVariabele zijde;
    public InvoerVariabele hoeken;
    double i = 0;

    @Override
    public void initialiseer() {
        setSize(500, 500);
        zijde = new InvoerVariabele("zijde", 1, 10000, 50);
        hoeken = new InvoerVariabele("hoeken", 1, 10000, 4);
        ///*
        maakZichtbaar(hoeken);
        maakZichtbaar(zijde);
        //*/
        //maakAnimatieMogelijk();
    }

    @Override
    public void animatie() {
        //while(true) {
        //	tekenOpnieuw();
        //	pauze(1);
        //}
    }

    @Override
    public void tekenprogramma() {
//		if (i == 360) i =0;
        //rechts(i);
        //i+=2;
        //figur(zijde.geefWaarde(), hoeken.geefWaarde(), null);
        andere();
    }

    public void andere() {
        stap((double) zijde.geefWaarde() / -8, (double) zijde.geefWaarde() / hoeken.geefWaarde() / 2);
        for (int i = 0; i < 1; i++) {
            figur((double) zijde.geefWaarde() / hoeken.geefWaarde(), hoeken.geefWaarde(), null);
            rechts(360.0 / hoeken.geefWaarde());
        }
    }

    public void opdracht2() {
        links(90);
        for (int i = 0; i < 3; i++) {
            rechts(120);
            vooruit(70);
            rechts(120);
            figur(70, 3, "blauw");
            links(180);
            figur(210, 3, "geel");
            rechts(60);
            vooruit(70);
        }
        figur(140, 3, "groen");
    }

    public void figur(double zijde, int hoeken, String kleur) {
        if (kleur != null) vulAan(kleur);
        for (int i = 0; i < hoeken; i++) {
            rechts(360.0 / hoeken);
            vooruit(zijde);
        }
        vulUit();
    }

    @Override
    public void invoerVarActie(InvoerVariabele iv) {
        this.tekenOpnieuw();
    }

}