package nl.edulogo.acslogo.handlers.procedures;

import nl.edulogo.acslogo.ACSLogo;
import nl.edulogo.acslogo.handlers.CommandoHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcedureHandler {
    private ACSLogo logo;
    private CommandoHandler commandoHandler;
    private Map<String, Procedure> procedures;

    public ProcedureHandler(ACSLogo logo, CommandoHandler commandoHandler) {
        this.logo = logo;
        this.commandoHandler = commandoHandler;
        procedures = new HashMap<>();
    }

    public void registerProcedure(String name, List<String> parameters, String code) {
        if (procedures.containsKey(name)) {
            Procedure p = procedures.remove(name);
            commandoHandler.removeCommando(p);
        }

        Procedure procedure = new Procedure(name, parameters, code, logo);
        commandoHandler.registerCommando(procedure);
        procedures.put(name, procedure);
    }

    public void removeProcedure(String name) {
        if (procedures.containsKey(name)) {
            Procedure p = procedures.remove(name);
            commandoHandler.removeCommando(p);
        }
    }
}
