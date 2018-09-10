package nl.edulogo.display;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nl.edulogo.core.*;

import java.io.File;

/**
 * Created by Under_Koen on 10/09/2018.
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

        FxDisplay display = new FxDisplay(new Size(500, 500));
        pane.getChildren().add(display.getCanvas());

        primaryStage.show();

        display.fillScreen(new Color(20, 20, 20));
        display.drawLine(new Position(10, 10), new Position(100, 200));
        display.drawLine(new Position(15, 10), new Position(120, 200), Color.RED);
        display.drawDot(new Position(420, 420));
        display.drawDot(new Position(450, 450), Color.WHITE);
        display.fillPolygon(new Polygon(new Position(300, 50), new Position(400, 50), new Position(450, 75),
                new Position(350, 300), new Position(300, 100)), Color.GREEN);
        display.write("HEY", new Position(50, 300));
        display.write("KOEN", new Position(50, 350), new Font(50, Color.RED));
        display.write("DAAN", new Position(50, 400), new Font("Comic Sans MS", 50, Color.BLUE));
        display.drawImage(new Image(new File("/Users/koen/Desktop/Random/img/KOEN.JPG")), new Position(250, 250));
        display.drawImage(new Image(new File("/Users/koen/Desktop/Random/img/d0an.png")),
                new Position(390, 430), new Size(50, 50));
        display.drawLine(new Position(380, 390), new Position(405, 455), Color.RED);
        display.drawLine(new Position(440, 390), new Position(405, 455), Color.RED);
    }
}
