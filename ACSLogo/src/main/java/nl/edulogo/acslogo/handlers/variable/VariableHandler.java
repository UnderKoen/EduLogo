package nl.edulogo.acslogo.handlers.variable;

import nl.edulogo.acslogo.script.commandos.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Under_Koen on 04/11/2018.
 */
public class VariableHandler {
    private Map<String, Value> vars;

    public VariableHandler() {
        this.vars = new HashMap<>();
    }

    public Value getVariable(String name) {
        return vars.get(name);
    }

    public void setVariable(String name, Value value) {
        vars.put(name, value);
    }

    public boolean contains(String name) {
        return vars.containsKey(name);
    }
}
