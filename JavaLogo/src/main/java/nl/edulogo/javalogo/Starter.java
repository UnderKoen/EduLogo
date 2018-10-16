package nl.edulogo.javalogo;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.edulogo.display.fx.FXCanvas;
import nl.edulogo.display.fx.FXDisplay;

import java.lang.reflect.Constructor;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public class Starter extends Application {
    private static TekenApplet applet;

    static void start() {
        StackTraceElement[] cause = Thread.currentThread().getStackTrace();

        Integer starter = null;
        for (int i = 0; i < cause.length; i++) {
            if (Starter.class.getName().equals(cause[i].getClassName()) && "start".equals(cause[i].getMethodName())) {
                starter = i;
                break;
            }
        }

        if (starter == null) return;
        if (cause.length < starter + 3) return;

        StackTraceElement e = cause[starter + 2];

        if (e == null) return;

        try {
            Class cls = Class.forName(e.getClassName(), false, Thread.currentThread().getContextClassLoader());
            if (TekenApplet.class.isAssignableFrom(cls)) {
                Class<? extends TekenApplet> appletClass = cls;
                Constructor constructor = appletClass.getConstructor();
                applet = (TekenApplet) constructor.newInstance();
                launch();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        applet.start();
        FXDisplay<FXCanvas> display = new FXDisplay<>((FXCanvas) applet.getCanvas());
        display.show();
        applet.tekenprogramma();
    }
}
