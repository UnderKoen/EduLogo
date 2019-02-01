package nl.edulogo.acslogo.handlers.variable;

import nl.edulogo.acslogo.script.commandos.Value;

public class LocalVariableHandler extends VariableHandler {
    private VariableHandler parent;

    public LocalVariableHandler(VariableHandler parent) {
        this.parent = parent;
    }

    public void makeLocal(String name) {
        super.setVariable(name, null);
    }

    @Override
    public Value getVariable(String name) {
        if (super.contains(name)) {
            return super.getVariable(name);
        } else {
            return parent.getVariable(name);
        }
    }

    @Override
    public void setVariable(String name, Value value) {
        if (super.contains(name)) {
            super.setVariable(name, value);
        } else {
            parent.setVariable(name, value);
        }
    }

    @Override
    public boolean contains(String name) {
        if (super.contains(name)) {
            return true;
        } else {
            return parent.contains(name);
        }
    }
}
