package nl.edulogo.acslogo;

import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.parser.pieces.ListPiece.ListObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 08/11/2018.
 */
public class Commandos {
    private static ACSLogo logo;

    private Commandos() {
    }

    public static Commando[] getCommandos(ACSLogo logo) {
        Commandos.logo = logo;
        List<Commando> commandos = new ArrayList<>();

        commandos.add(new Commando("forward", Commandos::forward, 1, "fd"));
        commandos.add(new Commando("back", Commandos::back, 1));
        commandos.add(new Commando("right", Commandos::right, 1, "rt"));
        commandos.add(new Commando("left", Commandos::left, 1, "lt"));

        commandos.add(new Commando("repeat", Commandos::repeat, 2));

        commandos.add(new Commando("abs", Commandos::abs, 1));
        commandos.add(new Commando("and", Commandos::and, 2));
        commandos.add(new Commando("ascii", Commandos::ascii, 1));
        commandos.add(new Commando("char", Commandos::charM, 1));

        return commandos.toArray(new Commando[0]);
    }

    //Default method
    @Deprecated
    public static Value defaultM(Value... arguments) {
        return null;
    }

    public static Value abs(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.abs(d));
    }

    public static Value and(Value... arguments) {
        Boolean l = (Boolean) arguments[0].getValue();
        Boolean r = (Boolean) arguments[1].getValue();
        return new Value(l && r);
    }

    public static Value ascii(Value... arguments) {
        char c = ((String) arguments[0].getValue()).charAt(0);
        return new Value(Character.hashCode(c));
    }

    public static Value charM(Value... arguments) {
        int i = ((Double) arguments[0].getValue()).intValue();
        return new Value((char) i + "");
    }

    public static Value repeat(Value... arguments) {
        int t = ((Double) arguments[0].getValue()).intValue();
        ListObject l = (ListObject) arguments[1].getValue();
        for (int i = 0; i < t; i++) {
            logo.run(String.join(" ", l.getList()));
        }
        return null;
    }

    public static Value forward(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.forward(amount);
        return null;
    }

    public static Value back(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.forward(-amount);
        return null;
    }

    public static Value right(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.right(amount);
        return null;
    }

    public static Value left(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.left(amount);
        return null;
    }
}
