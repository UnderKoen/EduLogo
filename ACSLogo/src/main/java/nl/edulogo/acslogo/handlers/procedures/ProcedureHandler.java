package nl.edulogo.acslogo.handlers.procedures;

import nl.edulogo.acslogo.ACSLogo;
import nl.edulogo.acslogo.handlers.CommandoHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProcedureHandler {
    private ACSLogo logo;
    private CommandoHandler commandoHandler;
    private Map<String, Procedure> procedures;

    public ProcedureHandler(ACSLogo logo, CommandoHandler commandoHandler) {
        this.logo = logo;
        this.commandoHandler = commandoHandler;
        procedures = new LinkedHashMap<>();
    }

    public Map<String, Procedure> getProcedures() {
        return procedures;
    }

    public void registerProcedure(Procedure procedure) {
        String name = procedure.getName();

        removeProcedure(name);

        commandoHandler.registerCommando(procedure);
        procedures.put(name, procedure);
    }

    public void registerProcedure(String name, List<String> parameters, String code) {
        Procedure procedure = createProcedure(name, parameters, code);
        registerProcedure(procedure);
    }

    public Procedure createProcedure(String name, List<String> parameters, String code) {
        return new Procedure(name, parameters, code, logo);
    }

    public void removeProcedure(String name) {
        if (procedures.containsKey(name)) {
            Procedure p = procedures.remove(name);
            commandoHandler.removeCommando(p);
        }
    }

    public Procedure getProcedure(String name) {
        return procedures.get(name);
    }

    public ACSLogo getLogo() {
        return logo;
    }
}
