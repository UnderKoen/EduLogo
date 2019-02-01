package nl.edulogo.acslogo.script.commandos;

import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;

public interface RunnableEmpty {
    Value run() throws ExecutorException, ParsingException;
}
