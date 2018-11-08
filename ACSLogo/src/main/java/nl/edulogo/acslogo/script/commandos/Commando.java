package nl.edulogo.acslogo.script.commandos;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Commando {
    private String name;
    private int arguments;
    private Runnable runnable;

    public Commando(String name, int arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public Commando(String name, Runnable runnable, int arguments) {
        this(name, arguments);
        this.runnable = runnable;
    }

    public String getName() {
        return name;
    }

    public int getArguments() {
        return arguments;
    }

    public Value call(Value... arguments) {
        Value response = null;
        if (runnable != null) response = runnable.run(arguments);
        if (response == null) response = new Value(null);
        return response;
    }
}
