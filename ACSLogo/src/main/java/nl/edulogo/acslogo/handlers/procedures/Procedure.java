package nl.edulogo.acslogo.handlers.procedures;

import nl.edulogo.acslogo.ACSLogo;
import nl.edulogo.acslogo.handlers.VariableHandler;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.ExecutorException;
import nl.edulogo.acslogo.script.parser.ParsingException;

import java.util.List;

public class Procedure extends Commando {
    private ACSLogo logo;
    private String code;
    private List<String> parameters;

    public Procedure(String name, List<String> parameters, String code, ACSLogo logo) {
        super(name, parameters.size());
        this.logo = logo;
        this.code = code;
        this.parameters = parameters;
    }

    @Override
    public Value call(Value... arguments) throws ExecutorException {
        VariableHandler variableHandler = logo.getExecutor().getVariableHandler();
        for (int i = 0; i < parameters.size(); i++) {
            String para = parameters.get(i);
            variableHandler.setVariable(para, arguments[i]);
        }

        try {
            Script s = logo.getParser().parse(code);
            return logo.getExecutor().execute(s);
        } catch (ParsingException e) {
            throw new ExecutorException(e.getMessage());
        }
    }
}
