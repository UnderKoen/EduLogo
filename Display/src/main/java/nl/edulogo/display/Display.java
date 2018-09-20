package nl.edulogo.display;

import nl.edulogo.core.Size;

/**
 * Created by Under_Koen on 20/09/2018.
 */
public interface Display<T extends View> {
    T getView();

    void setView(T view);

    void show();

    boolean isResizeable();

    void setResizeable(boolean resizeable);

    Size getSize();

    void setSize(Size size);

    Size getMinSize();

    void setMinSize(Size size);

    Size getMaxSize();

    void setMaxSize(Size size);
}
