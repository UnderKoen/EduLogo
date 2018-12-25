package nl.edulogo.acslogo.script.commandos;

import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;

import java.util.Arrays;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Commando {
    protected String name;
    private int arguments;
    private Runnable runnable;
    private String[] aliases;

    public Commando(String name, int arguments) {
        this.name = name.toLowerCase();
        this.arguments = arguments;
    }

    public Commando(String name, Runnable runnable, int arguments) {
        this(name, arguments);
        this.runnable = runnable;
    }

    public Commando(String name, Runnable runnable, int arguments, String... aliases) {
        this(name, runnable, arguments);
        this.aliases = Arrays.stream(aliases).map(String::toLowerCase).toArray(String[]::new);
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }

    public int getArguments() {
        return arguments;
    }

    public Value call(Value... arguments) throws ExecutorException, ParsingException {
        Value response = new Value(null);
        if (runnable != null) response = runnable.run(arguments);
        if (response == null) response = new Value(null);
        return response;
    }
}
