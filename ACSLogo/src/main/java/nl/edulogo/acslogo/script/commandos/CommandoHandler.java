package nl.edulogo.acslogo.script.commandos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class CommandoHandler {
    private List<Commando> commandos = new ArrayList<>();

    public void registerCommando(Commando commando) {
        String name = commando.getName();
        boolean contains = commandos.stream()
                .map(Commando::getName)
                .anyMatch(name::equalsIgnoreCase);
        if (contains) {
            throw new IllegalArgumentException(String.format("%s, is already registerd", name));
        }
        commandos.add(commando);
    }

    public void registerCommandos(Commando... commandos) {
        for (Commando commando : commandos) {
            registerCommando(commando);
        }
    }

    public Commando getCommando(String name) {
        return commandos.stream()
                .filter(cmd -> cmd.getName().equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }
}
