package nl.edulogo.acslogo.handlers.variable;

import nl.edulogo.acslogo.script.commandos.Value;

import java.util.HashMap;
import java.util.Map;

public class PropertyHandler {
    private Map<String, Map<String, Value>> properties;

    public PropertyHandler() {
        properties = new HashMap<>();
    }

    public Value getProperty(String name, String property) {
        return properties.get(name).get(property);
    }

    public Map<String, Value> getProperties(String name) {
        return new HashMap<>(properties.get(name));
    }

    public void setProperty(String name, String property, Value value) {
        Map<String, Value> prop;
        if (properties.containsKey(name)) {
            prop = properties.get(name);
        } else {
            prop = new HashMap<>();
            properties.put(name, prop);
        }

        prop.put(property, value);
    }

    public void removeProperty(String name, String property) {
        if (!properties.containsKey(name)) return;
        Map<String, Value> prop = properties.get(name);
        if (!properties.containsKey(property)) return;
        properties.remove(property);
    }
}
