package nl.edulogo.editor;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

public class DragResizer {

    /**
     * The margin around the control that a user can click in to start resizing
     * the region.
     */
    private static final int RESIZE_MARGIN = 5;

    private final Region region;
    private boolean dragging;

    private DragResizer(Region region) {
        this.region = region;
    }

    public static void makeResizable(Region region) {
        final DragResizer resizer = new DragResizer(region);

        region.getParent().addEventFilter(MouseEvent.MOUSE_PRESSED, resizer::mousePressed);
        region.getParent().addEventFilter(MouseEvent.MOUSE_DRAGGED, resizer::mouseDragged);
        region.getParent().addEventFilter(MouseEvent.MOUSE_MOVED, resizer::mouseOver);
        region.getParent().addEventFilter(MouseEvent.MOUSE_RELEASED, resizer::mouseReleased);
    }

    private void mouseReleased(MouseEvent event) {
        if (!dragging) return;
        dragging = false;
    }

    private void mouseOver(MouseEvent event) {
        region.getParent().setCursor((dragging || isInDraggableZone(event)) ? Cursor.S_RESIZE : Cursor.DEFAULT);
    }

    private boolean isInDraggableZone(MouseEvent event) {
        double dif = region.getLayoutY() - event.getY();
        return dif >= -RESIZE_MARGIN && dif <= RESIZE_MARGIN;
    }

    private void mouseDragged(MouseEvent event) {
        if (!dragging) return;

        double dif = region.getLayoutY() - event.getY();
        region.setMaxHeight(region.getHeight() + dif);
    }

    private void mousePressed(MouseEvent event) {
        if (!isInDraggableZone(event)) return;
        dragging = true;
    }
}