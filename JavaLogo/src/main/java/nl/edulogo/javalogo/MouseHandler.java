package nl.edulogo.javalogo;

import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

/**
 * Created by Under_Koen on 17/10/2018.
 */
public class MouseHandler implements EventDispatcher {
    private TekenApplet tekenApplet;

    private int mouseX;
    private int mouseY;
    private int mousePressX;
    private int mousePressY;
    private int draggedX;
    private int draggedY;

    public MouseHandler(TekenApplet tekenApplet) {
        this.tekenApplet = tekenApplet;
    }

    @Override
    public Event dispatchEvent(Event event, EventDispatchChain tail) {
        if (event instanceof MouseEvent) {
            MouseEvent mouseEvent = (MouseEvent) event;
            EventType type = mouseEvent.getEventType();
            if (type == MouseEvent.MOUSE_PRESSED) {
                mousePressed(mouseEvent);
            } else if (type == MouseEvent.MOUSE_RELEASED) {
                mouseReleased();
            } else if (type == MouseEvent.MOUSE_MOVED) {
                mouseMoved(mouseEvent);
            } else if (type == MouseEvent.MOUSE_DRAGGED) {
                mouseDragged(mouseEvent);
            }
        }
        return event;
    }

    public void mouseMoved(MouseEvent event) {
        mouseX = (int) event.getX();
        mouseY = (int) event.getY();
        tekenApplet.muisBeweegActie();
    }

    public void mousePressed(MouseEvent event) {
        mousePressX = (int) event.getX();
        mousePressY = (int) event.getY();
        tekenApplet.muisDrukActie();
    }

    public void mouseReleased() {
        tekenApplet.muisLosActie();
    }

    public void mouseDragged(MouseEvent event) {
        draggedX = (int) event.getX() - mouseX;
        draggedY = mouseY - (int) event.getY();

        mouseX = (int) event.getX();
        mouseY = (int) event.getY();

        tekenApplet.muisSleepActie();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getMousePressX() {
        return mousePressX;
    }

    public int getMousePressY() {
        return mousePressY;
    }

    public int getDraggedX() {
        return draggedX;
    }

    public int getDraggedY() {
        return draggedY;
    }
}