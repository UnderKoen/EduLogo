package nl.edulogo.acslogo.handlers;

import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import nl.edulogo.display.fx.FXCanvas;

public class MouseHandler {
    private FXCanvas canvas;
    private boolean left = false;
    private boolean right = false;

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
                    break;
                case SECONDARY:
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
        }
    }

    public boolean isLeftMouseDown() {
        return left;
    }

    public boolean isRightMouseDown() {
        return right;
    }
}
