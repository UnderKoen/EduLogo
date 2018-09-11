package nl.edulogo.editor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
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
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 500, 500);

        primaryStage.setScene(scene);

        FxEditor editor = new FxEditor();
        pane.getChildren().add(editor);

        primaryStage.show();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
                System.out.println(editor.getCurrentLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}