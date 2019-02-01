package nl.edulogo.acslogo.handlers;

import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import nl.edulogo.core.Position;
import nl.edulogo.display.fx.FXCanvas;

public class MouseHandler {
    private FXCanvas canvas;
    private boolean left = false;
    private boolean right = false;
    private Position mouse = new Position(0, 0);
    private Waitable<Position> leftMouseClick = new Waitable<>();
    private Waitable<Position> rightMouseClick = new Waitable<>();
    private Waitable<Position> mouseMove = new Waitable<>();

    public MouseHandler(FXCanvas canvas) {
        this.canvas = canvas;
        Node n = canvas.getNode();
        n.addEventHandler(MouseEvent.ANY, this::mouseEvent);
    }

    private void mouseEvent(MouseEvent event) {
        EventType<? extends MouseEvent> type = event.getEventType();
        if (type == MouseEvent.MOUSE_PRESSED) {
            switch (event.getButton()) {
                case PRIMARY:
                    left = true;
                    leftMouseClick.setDone(new Position(event.getX(), event.getY()));
                    break;
                case SECONDARY:
                    rightMouseClick.setDone(new Position(event.getX(), event.getY()));
                    right = true;
                    break;
                default:
                    break;
            }
        } else if (type == MouseEvent.MOUSE_RELEASED) {
            switch (event.getButton()) {
                case PRIMARY:
                    left = false;
                    break;
                case SECONDARY:
                    right = false;
                    break;
                default:
                    break;
            }
        } else if (type == MouseEvent.MOUSE_MOVED) {
            mouseMove.setDone(new Position(event.getX(), event.getY()));
            mouse = new Position(event.getX(), event.getY());
        }
    }

    public boolean isLeftMouseDown() {
        return left;
    }

    public boolean isRightMouseDown() {
        return right;
    }

    public Waitable<Position> getLeftMouseClick() {
        return leftMouseClick;
    }

    public Waitable<Position> getRightMouseClick() {
        return rightMouseClick;
    }

    public Waitable<Position> getMouseMove() {
        return mouseMove;
    }

    public Position getMouse() {
        return mouse;
    }
}
