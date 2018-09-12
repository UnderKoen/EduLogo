package nl.edulogo.editor;

/**
 * Created by Under_Koen on 11/09/2018.
 */
public interface Editor {
    String getText();

    String getSelectedText();

    String getCurrentLine();

    Console getConsole();
}
