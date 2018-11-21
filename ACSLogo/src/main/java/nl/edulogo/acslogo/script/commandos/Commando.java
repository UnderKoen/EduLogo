package nl.edulogo.acslogo.script.commandos;

import nl.edulogo.acslogo.script.executor.ExecutorException;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Commando {
    private String name;
    private int arguments;
    private Runnable runnable;
    private String[] aliases;

    public Commando(String name, int arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public Commando(String name, Runnable runnable, int arguments) {
        this(name, arguments);
        this.runnable = runnable;
    }

    public Commando(String name, Runnable runnable, int arguments, String... aliases) {
        this.name = name;
        this.arguments = arguments;
        this.runnable = runnable;
        this.aliases = aliases;
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

    public Value call(Value... arguments) throws ExecutorException {
        Value response = null;
        if (runnable != null) response = runnable.run(arguments);
        if (response == null) response = new Value(null);
        return response;
    }
}
