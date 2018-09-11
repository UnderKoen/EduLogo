package nl.edulogo.javalogo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nl.edulogo.core.Display;
import nl.edulogo.core.Position;
import nl.edulogo.core.Size;
import nl.edulogo.display.FxDisplay;

public class TekenApplet {
    private Display display;

    public static void main(String[] args) {
        new TekenApplet().start();
    }

    public void start() {
        display = new FxDisplay(new Size(500, 500));
        Temp.display = (FxDisplay) display;
        new Thread(() -> {
            try {
                //Thread.sleep(1000);
                debug();//TODO FIX THIS SHIT
                System.out.println("wowoowowo");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        Temp.start2();
    }

    private void debug() {
        test();
    }

    //FUNCTIES
    public void test() {
        Temp.display.drawLine(new Position(10, 10), new Position(200, 200));
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
