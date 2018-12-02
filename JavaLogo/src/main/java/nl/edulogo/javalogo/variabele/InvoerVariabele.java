package nl.edulogo.javalogo.variabele;

/**
 * Created by Under_Koen on 25/09/2018.
 */
public class InvoerVariabele {
    private String naam;
    private boolean enabled;
    private int min;
    private int max;
    private int val;

    public InvoerVariabele(final String nm, final int mn, final int mx, final int w) {
        naam = nm;
        min = mn;
        max = mx;
        val = w;
        enabled = true;
    }

    public void zetInvoerUit() {
        enabled = false;
    }

    public void zetInvoerAan() {
        enabled = true;
    }

    public void zetWaarde(int newval) {
        if (newval > max) {
            val = max;
        } else if (newval < min) {
            val = min;
        } else {
            val = newval;
        }
        //this.wtekst.setValue(super.waarde);
    }

}
