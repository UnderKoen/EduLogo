package nl.edulogo.display.fx;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nl.edulogo.core.Size;
import nl.edulogo.display.Display;

/**
 * Created by Under_Koen on 10/09/2018.
 */
public class FXDisplay<T extends FXView> implements Display<T> {
    private T view;
    private Size size;
    private Size minSize;
    private Size maxSize;
    private boolean resizeable = true;

    private Scene scene;
    private Pane pane;
    private Stage stage;

    public FXDisplay(T view) {
        stage = new Stage();
        pane = new StackPane();
        scene = new Scene(pane);
        stage.setScene(scene);

        setView(view);
    }

    public FXDisplay(Size size, T view) {
        this(view);
        setSize(size);
    }

    public FXDisplay(T view, boolean resizeable) {
        this(view);
        setResizeable(resizeable);
    }

    public FXDisplay(Size size, T view, boolean resizeable) {
        this(size, view);
        setResizeable(resizeable);
    }

    public FXDisplay(Size size, Size minSize, Size maxSize, T view) {
        this(size, view);
        setMinSize(minSize);
        setMaxSize(maxSize);
    }

    public FXDisplay(Size size, Size minSize, Size maxSize, T view, boolean resizeable) {
        this(size, minSize, maxSize, view);
        setResizeable(resizeable);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public T getView() {
        return view;
    }

    @Override
    public void setView(T view) {
        this.view = view;
        if (!pane.getChildren().isEmpty()) pane.getChildren().remove(0);
        pane.getChildren().add(view.getNode());
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public boolean isResizeable() {
        return resizeable;
    }

    @Override
    public void setResizeable(boolean resizeable) {
        this.resizeable = resizeable;
        stage.setResizable(resizeable);
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void setSize(Size size) {
        this.size = size;
        stage.setWidth(size.getWidth());
        stage.setHeight(size.getHeight() + 22);
    }

    @Override
    public Size getMinSize() {
        return minSize;
    }

    @Override
    public void setMinSize(Size size) {
        this.minSize = size;
        stage.setMinWidth(minSize.getWidth());
        stage.setMinHeight(minSize.getHeight() + 22);
    }

    @Override
    public Size getMaxSize() {
        return maxSize;
    }

    @Override
    public void setMaxSize(Size size) {
        this.maxSize = size;
        stage.setMaxWidth(maxSize.getWidth());
        stage.setMaxHeight(maxSize.getHeight() + 22);
    }
}
