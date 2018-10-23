package nl.edulogo.editor.fx;

import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.stage.Stage;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXDisplay;

/**
 * Created by Under_Koen on 11/09/2018.
 */
public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXEditor editor = new FXEditor();

        editor.getMenuBar().getMenus().add(new Menu("de"));

        FXDisplay<FXEditor> display = new FXDisplay<>(new Size(350, 500), editor);
        display.show();
    }
}