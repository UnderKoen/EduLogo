package nl.edulogo.acslogo;

import javafx.application.Platform;

import java.awt.*;
import java.io.File;

/**
 * Created by Under_Koen on 2019-01-08.
 */
public class Test {
    public static void main(String[] args) {
        Desktop desktop = Desktop.getDesktop();
        desktop.setOpenFileHandler(e -> {
            File file = e.getFiles().get(0);
            if (!Starter.done) {
                Starter.file = file;
            } else {
                Platform.runLater(() -> Starter.startFile(file));
            }
        });
        Starter.start();
    }
}
