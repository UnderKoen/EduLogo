package nl.edulogo.javalogo;

import nl.edulogo.core.Color;
import nl.edulogo.core.Position;
import nl.edulogo.javalogo.utils.ColorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D0an
 */

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

    private boolean loopBusy = false;

    public void handleAllTracesTest() {
        for (Trace t : traces) {
            handleTrace(t);
        }
    }

    public void addTrace(Trace trace) {
        traces.add(trace);
    }

    public String getCurrentTraceString() {
        if (index >= traces.size()) return "";
        return traces.get(index).toString();
    }

    public void done() {
        resetScreen();
        loopBusy = false;
        traces.clear();
        applet.tekenOpnieuw();
        index = 0;
    }

    public void begin() {
        resetScreen();
        index = 0;
    }

    public void next() {
        if (index >= traces.size()) return;
        resetScreen();
        for (int i = 0; i <= index; i++) {
            handleTrace(traces.get(i));
        }
        index++;
        drawArrow();
    }

    public void back() {
        if (index <= 0) return;
        index--;
        resetScreen();
        for (int i = 0; i < index; i++) {
            handleTrace(traces.get(i));
        }
        drawArrow();
    }

    public void loop() {
        if (loopBusy) return;
        loopBusy = true;
        resetScreen();
        index = 0;
        Thread loopThread = new Thread(() -> {
            while (loopBusy) {
                next();
                //System.out.println("nu stoppen: index=" + index + ", traces.size()=" + traces.size());
                if (index >= traces.size()) {
                    loopBusy = false;
                }
                applet.pauze(400);
            }
        });
        loopThread.start();
    }

    private void resetScreen() {
        applet.getTurtle().setColor(Color.BLACK);
        applet.getTurtle().setPosition(new Position(applet.getCanvas().getSize().getWidth() / 2, applet.getCanvas().getSize().getHeight() / 2));
        applet.getTurtle().setRotation(0);
        applet.resetPath();
        applet.fillScreen(null);
    }

    private void drawArrow() {
        applet.getTurtle().setPenDown(true);
        applet.getTurtle().setColor(Color.BLACK);
        applet.resetPath();
        applet.step(-8, 0);
        applet.step(8, 5);
        applet.step(8, -5);
        applet.step(-8, 0);
        applet.getTurtle().setFillColor(Color.YELLOW);
        applet.fillPath();
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
                if (trace.getValues().length == 0) {
                    applet.getTurtle().setColor(Color.BLACK);
                } else if (trace.getValues().length == 1) {
                    applet.getTurtle().setColor(ColorUtil.fromString((String) trace.getValues()[0]));
                } else if (trace.getValues().length == 3) {
                    applet.getTurtle().setColor(new Color((int) trace.getValues()[0], (int) trace.getValues()[1], (int) trace.getValues()[2]));
                }
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
