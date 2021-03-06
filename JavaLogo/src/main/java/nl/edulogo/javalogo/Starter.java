package nl.edulogo.javalogo;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.edulogo.display.fx.FXCanvas;
import nl.edulogo.display.fx.FXDisplay;

import java.lang.reflect.Constructor;

/**
 * Created by Under_Koen & D0an on 20/09/2018.
 */
public class Starter extends Application {
    private static TekenApplet applet;

    static void start() {
        String clsN = System.getProperty("sun.java.command");
        clsN = clsN.split(" ")[0];
        if (clsN.contains("/")) clsN = clsN.split("/")[1];

        try {
            Class cls = Class.forName(clsN, false, Thread.currentThread().getContextClassLoader());
            if (TekenApplet.class.isAssignableFrom(cls)) {
                Class<? extends TekenApplet> appletClass = cls;
                Constructor constructor = appletClass.getConstructor();
//                System.out.println(Arrays.toString(constructor.getParameterTypes()));
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
    }
}
