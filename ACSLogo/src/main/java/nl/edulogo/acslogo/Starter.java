package nl.edulogo.acslogo;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Starter extends Application {
    private static Runnable onLaunch;

    public static void start(Runnable onLaunch) {
        Starter.onLaunch = onLaunch;
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        onLaunch.run();
        //Just starting a javaFX program
    }
}
