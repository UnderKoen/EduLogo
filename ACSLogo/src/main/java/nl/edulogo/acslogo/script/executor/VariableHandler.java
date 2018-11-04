package nl.edulogo.acslogo.script.executor;

import nl.edulogo.acslogo.script.parser.PieceType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class VariableHandler {
    private Map<String, Object> vars;

    public VariableHandler() {
        this.vars = new HashMap<>();
    }

    public Object getVariable(String name) {
        return vars.get(name);
    }

    public void setVariable(String name, Object object) throws ExecutorException {
        PieceType.getType(object);
        vars.put(name, object);
    }

    public boolean contains(String name) {
        return vars.containsKey(name);
    }
}
