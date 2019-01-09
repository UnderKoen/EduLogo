package nl.edulogo.acslogo;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Starter extends Application {
    public static boolean done = false;
    public static File file;

    public static void start() {
        launch();
    }

    public static void startFile(File file) {
        try {
            new ACSLogo(new Document(file));
        } catch (IOException ignored) {
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        done = true;
        if (file == null) {
            new ACSLogo(new Document());
        } else {
            startFile(file);
        }
    }
}