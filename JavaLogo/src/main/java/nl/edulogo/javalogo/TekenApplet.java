package nl.edulogo.javalogo;

import nl.edulogo.core.Canvas;
import nl.edulogo.core.Position;
import nl.edulogo.core.Size;
import nl.edulogo.core.utils.MathUtil;
import nl.edulogo.display.fx.FXCanvas;

public abstract class TekenApplet {
    protected Canvas canvas;
    private Position currentPos;
    private double rotation = 0;

    public static void launch() {
        Starter.start();
    }

    public void start() {
        initialiseer();
        currentPos = new Position(250, 250);
        canvas = new FXCanvas(new Size(500, 500));
    }

    abstract void tekenapplet();

    abstract void initialiseer();

    public void vooruit(double dy) {
        Position newPos = MathUtil.getRelativePosition(rotation, -dy);
        newPos.addPosition(currentPos);
        canvas.drawLine(currentPos, newPos);
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
}
