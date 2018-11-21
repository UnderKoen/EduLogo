package nl.edulogo.acslogo.script.commandos;

import nl.edulogo.acslogo.script.executor.ExecutorException;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public interface Runnable {
    Value run(Value... arguments) throws ExecutorException;
}
