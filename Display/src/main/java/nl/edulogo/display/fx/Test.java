package nl.edulogo.display.fx;

import javafx.application.Application;
import javafx.stage.Stage;
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
        canvas.drawLine(new Position(200, 200), new Position(300, 300));
        canvas.drawLine(new Position(300, 200), new Position(200, 300));
        canvas.arc(new Position(250, 250), 100, 100, 90, 360);
        display.show();
    }
}
