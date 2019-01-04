package nl.edulogo.javalogo;

import nl.edulogo.core.Color;
import nl.edulogo.javalogo.utils.ColorUtil;

import java.util.ArrayList;
import java.util.List;

public class TraceHandler {
    private List<Trace> traces;
    private int index;
    private boolean mogelijk;
    private boolean bezig;
    private TekenApplet applet;

    public TraceHandler(TekenApplet applet) {
        this.applet = applet;
        mogelijk = false;
        traces = new ArrayList<>();
        bezig = false;
        index = 0;
    }

    public boolean isBezig() {
        return bezig;
    }

    public void setBezig(boolean bezig) {
        this.bezig = bezig;
    }

    public boolean isMogelijk() {
        return mogelijk;
    }

    public void maakMogelijk() {
        mogelijk = true;
    }

    public void addTrace(Trace trace) {
        traces.add(trace);
        System.out.println(trace.toString());
    }

    public void handleAllTracesTest() {
        for (Trace t : traces) {
            handleTrace(t);
        }
    }

    private void handleTrace(Trace trace) {
        switch (trace.getSoort()) {
            case RECHTS: {
                applet.right((double) trace.getValues()[0]);
                break;
            }
            case LINKS: {
                applet.left((double) trace.getValues()[0]);
                break;
            }
            case VOORUIT: {
                applet.forward((double) trace.getValues()[0]);
                break;
            }
            case PENAAN: {
                applet.getTurtle().setPenDown(true);
                break;
            }
            case PENUIT: {
                applet.getTurtle().setPenDown(false);
                break;
            }
            case SCHRIJF: {
                applet.write((String) trace.getValues()[0]);
                break;
            }
            case STAP: {
                applet.step((double) trace.getValues()[0], (double) trace.getValues()[1]);
                break;
            }
            case VULAAN: {
                applet.resetPath();
                if (trace.getValues().length == 0) {
                    applet.getTurtle().setFillColor(Color.BLACK);
                } else if (trace.getValues().length == 1) {
                    applet.getTurtle().setFillColor(ColorUtil.fromString((String) trace.getValues()[0]));
                } else if (trace.getValues().length == 3) {
                    applet.getTurtle().setFillColor(new Color((int) trace.getValues()[0], (int) trace.getValues()[1], (int) trace.getValues()[2]));
                }
                break;
            }
            case VULUIT: {
                applet.fillPath();
                break;
            }
        }
    }
}
