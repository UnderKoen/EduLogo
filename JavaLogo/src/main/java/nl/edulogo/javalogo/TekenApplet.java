package nl.edulogo.javalogo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nl.edulogo.core.Display;
import nl.edulogo.core.Position;
import nl.edulogo.core.Size;
import nl.edulogo.core.utils.MathUtil;
import nl.edulogo.display.FxDisplay;

public class TekenApplet {

    private Display display;
    private Position currentPos;
    private double rotation = 0;

    public static void main(String[] args) {
        new TekenApplet().start();
    }

    public void start() {
        initialiseer();
        currentPos = new Position(250, 250);
        display = new FxDisplay(new Size(500, 500));
        Temp.display = (FxDisplay) display;
        new Thread(() -> {
            try {
                //Thread.sleep(1000);
                tekenapplet();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        Temp.start2();
    }

    public void tekenapplet() {

    }

    public void initialiseer() {

    }

    public void vooruit(double dy) {
        Position newPos = MathUtil.getRelativePosition(rotation, -dy);
        newPos.addPosition(currentPos);
        display.drawLine(currentPos, newPos);
        currentPos = newPos;
    }

    public void rechts(double dHoek) {
        rotation -= dHoek;
        rotation = rotation % 360;
        if (rotation < 0) rotation = 360 + rotation;
    }

    public void links(double dHoek) {
        rechts(-dHoek);
    }



    public static class Temp extends Application {
        public static FxDisplay display;

        public static void start2() {
            launch();
        }

        @Override
        public void start(Stage primaryStage) {
            StackPane pane = new StackPane();
            Scene scene = new Scene(pane, 500, 500);

            primaryStage.setScene(scene);

            pane.getChildren().add(Temp.display.getCanvas());

            primaryStage.show();
        }
    }
}
