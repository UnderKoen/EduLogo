package nl.edulogo.editor;

import java.util.function.Consumer;

/**
 * Created by Under_Koen on 11/09/2018.
 */
public interface Editor {
    String getText();

    String getSelectedText();

    String getCurrentLine();

    void print(String text);

    void println(String text);

    void registerOnRun(Consumer<String> onRun);
}
