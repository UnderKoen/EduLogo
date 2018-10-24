package nl.edulogo.acslogo.script.parser.field;

import nl.edulogo.acslogo.script.commandos.Commando;

/**
 * Created by Under_Koen on 24/10/2018.
 */
public class CommandoField extends Field<Commando> {
    private Commando commando;
    private Field[] arguments;

    public CommandoField(String field, Commando commando) {
        super(field, FieldType.COMMANDO);
        this.commando = commando;
        arguments = new Field[commando.getArguments().length];
    }

    public Commando getCommando() {
        return commando;
    }

    public Field[] getArguments() {
        return arguments;
    }

    @Override
    public Commando parse() {
        return null;
    }
}
