package nl.edulogo.acslogo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Starter extends Application {
    public static boolean done = false;
    public static File file;

    private static List<ACSLogo> logoList = new ArrayList<>();

    public static void start() {
        launch();
    }

    public static void startFile(File file) {
        try {
            logoList.add(new ACSLogo(new Document(file)));
        } catch (IOException ignored) {
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Starter.done = true;
        if (file == null) {
            logoList.add(new ACSLogo(new Document()));
        } else {
            startFile(file);
        }
    }
}