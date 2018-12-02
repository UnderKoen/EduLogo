package nl.edulogo.acslogo.handlers;

import nl.edulogo.acslogo.script.commandos.Commando;

import java.util.ArrayList;
import java.util.Arrays;
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

    public void removeCommando(Commando commando) {
        commandos.remove(commando);
    }

    public Commando getCommando(String name) {
        return commandos.stream()
                .filter(cmd -> cmd.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseGet(() -> {
                    for (Commando commando : commandos) {
                        if (commando.getAliases() == null) continue;
                        if (Arrays.asList(commando.getAliases()).contains(name)) return commando;
                    }
                    return null;
                });
    }
}
