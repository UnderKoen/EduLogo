package nl.edulogo.acslogo.script.commandos;

import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public interface Runnable {
    Value run(Value... arguments) throws ExecutorException, ParsingException;
}
