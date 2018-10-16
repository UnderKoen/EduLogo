package nl.edulogo.acslogo.script.commandos;

import nl.edulogo.acslogo.script.arguments.Argument;
import nl.edulogo.acslogo.script.arguments.ArgumentType;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class Commando {
    private String name;
    private ArgumentType[] arguments;
    private Runnable runnable;

    public Commando(String name, ArgumentType... arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public Commando(String name, Runnable runnable, ArgumentType... arguments) {
        this(name, arguments);
        this.runnable = runnable;
    }

    public String getName() {
        return name;
    }

    public ArgumentType[] getArguments() {
        return arguments;
    }

    public void call(Argument... arguments) {
        if (runnable != null) runnable.run(arguments);
    }
}
