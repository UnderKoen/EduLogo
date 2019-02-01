package nl.edulogo.acslogo;

import nl.edulogo.acslogo.script.commandos.Value;

public class Output extends RuntimeException {
    Value output;

    public Output(Value output) {
        this.output = output;
    }

    public Value getOutput() {
        return output;
    }
}
