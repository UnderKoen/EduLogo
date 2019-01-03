package nl.edulogo.acslogo.handlers.procedures;

import nl.edulogo.acslogo.ACSLogo;
import nl.edulogo.acslogo.handlers.ConsoleHandler;
import nl.edulogo.acslogo.handlers.Variable.LocalVariableHandler;
import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.Executor;
import nl.edulogo.acslogo.script.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class Procedure extends Commando {
    public static LocalVariableHandler current = null;

    protected ACSLogo logo;
    protected String code;
    protected List<String> parameters;

    protected Procedure(Procedure procedure) {
        this(procedure.name, new ArrayList<>(procedure.parameters), procedure.code, procedure.logo);
    }

    public Procedure(String name, List<String> parameters, String code, ACSLogo logo) {
        super(name, parameters.size());
        this.logo = logo;
        this.code = code;
        this.parameters = parameters;
    }

    @Override
    public Value call(Value... arguments) throws ExecutorException {
        LocalVariableHandler variableHandler = new LocalVariableHandler(logo.getExecutor().getVariableHandler());
        for (int i = 0; i < parameters.size(); i++) {
            String para = parameters.get(i);
            variableHandler.makeLocal(para);
            variableHandler.setVariable(para, arguments[i]);
        }

        ConsoleHandler consoleHandler = logo.getExecutor().getConsoleHandler();
        Executor executor = new Executor(consoleHandler, logo.getExecutor().getCommandoHandler(), variableHandler);
        Parser parser = new Parser(consoleHandler, executor);

        LocalVariableHandler old = current;
        current = variableHandler;
        try {
            Script s = parser.parse(code);
            Value r = executor.execute(s);
            current = old;
            return r;
        } catch (ParsingException e) {
            current = null;
            throw new ExecutorException(e.getMessage());
        }
    }

    public String getCode() {
        return code;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
