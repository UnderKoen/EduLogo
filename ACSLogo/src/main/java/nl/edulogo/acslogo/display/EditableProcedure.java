package nl.edulogo.acslogo.display;

import nl.edulogo.acslogo.ACSLogo;
import nl.edulogo.acslogo.handlers.procedures.Procedure;

import java.util.List;

public class EditableProcedure extends Procedure {
    public EditableProcedure(Procedure procedure) {
        super(procedure);
    }

    public EditableProcedure(String name, List<String> parameters, String code, ACSLogo logo) {
        super(name, parameters, code, logo);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public int getArguments() {
        return parameters.size();
    }
}
