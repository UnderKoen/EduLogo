package nl.edulogo.editor;

import javafx.scene.input.KeyCode;

/**
 * Created by Under_Koen on 12/09/2018.
 */
public interface Console {
    void print(String text);

    void println(String text);

    void error(String text);

    void enableInput();

    void disableInput();

    String getInputText();

    void clearInput();

    void setOnKey(KeyListener listener);

    interface KeyListener {
        void run(KeyCode key);
    }
}
