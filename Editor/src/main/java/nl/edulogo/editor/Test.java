package nl.edulogo.editor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Under_Koen on 11/09/2018.
 */
public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FxEditor editor = new FxEditor();
        Scene scene = new Scene(editor, 350, 500);

        primaryStage.setScene(scene);

        primaryStage.show();

        Platform.runLater(() -> {
            editor.getConsole().print("1234567890");
            editor.getConsole().print("|1234567890");
            editor.getConsole().print("[1234567890");
            editor.getConsole().print("{1234567890");
            editor.getConsole().print("(1234567890");
            for (int i = 0; i < 100; i++) {
                editor.getConsole().println("Koen is rood: " + i);
            }
        });
    }
}