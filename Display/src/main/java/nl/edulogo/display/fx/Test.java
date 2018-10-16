package nl.edulogo.display.fx;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.edulogo.core.Color;
import nl.edulogo.core.Position;
import nl.edulogo.core.Size;

/**
 * Created by Under_Koen on 10/09/2018.
 */
public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Size size = new Size(500, 500);
        FXCanvas canvas = new FXCanvas(size);
        FXDisplay<FXCanvas> display = new FXDisplay<>(size, size, new Size(1000, 1000), canvas);
        canvas.fillScreen(Color.RED);
        canvas.drawLine(new Position(0, 0), new Position(500, 500));
        display.show();
    }
}
