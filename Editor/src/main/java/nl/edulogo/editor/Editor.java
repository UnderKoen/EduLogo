package nl.edulogo.editor;

/**
 * Created by Under_Koen on 11/09/2018.
 */
public interface Editor {
    void setText(String text);

    String getText();

    String getSelectedText();

    String getCurrentLine();

    Console getConsole();
}
