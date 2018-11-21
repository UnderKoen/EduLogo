package nl.edulogo.acslogo;

import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.ExecutorException;
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
        commandos.add(new Commando("arc", Commandos::arc, 2));

        commandos.add(new Commando("arcCos", Commandos::arcCos, 1, "arcCosine"));
        commandos.add(new Commando("arCosh", Commandos::arCosh, 1));
        commandos.add(new Commando("arcSin", Commandos::arcSin, 1, "arcSine"));
        commandos.add(new Commando("arSinh", Commandos::arSinh, 1));
        commandos.add(new Commando("arcTan", Commandos::arcTan, 1, "arcTangent"));
        commandos.add(new Commando("arTanh", Commandos::arTanh, 1));
        commandos.add(new Commando("aTan2", Commandos::aTan2, 2));

        //TODO background / bg

        commandos.add(new Commando("butFirst", Commandos::butFirst, 1));
        commandos.add(new Commando("butLast", Commandos::butLast, 1));

        //TODO button?/ buttonP

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

    public static Value arc(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        double r = (Double) arguments[1].getValue();
        logo.arc(r, d);
        return null;
    }

    public static Value arcCos(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.acos(d)));
    }

    public static Value arCosh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.log(d + Math.sqrt(d * d - 1.0))));
    }

    public static Value arcSin(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.asin(d)));
    }

    public static Value arSinh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.log(d + Math.sqrt(d * d + 1.0))));
    }

    public static Value arcTan(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.atan(d)));
    }

    public static Value arTanh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(0.5 * Math.log((d + 1.0) / (d - 1.0)));
    }

    public static Value aTan2(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        double d2 = (Double) arguments[1].getValue();
        return new Value(Math.toDegrees(Math.atan2(d2, d)));
    }

    public static Value ascii(Value... arguments) {
        char c = ((String) arguments[0].getValue()).charAt(0);
        return new Value(Character.hashCode(c));
    }

    public static Value butFirst(Value... arguments) throws ExecutorException {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<String> l = ((ListObject) v.getValue()).getList();
            if (l.isEmpty()) throw new ExecutorException("An empty list is a invalid input.");
            l.remove(0);
            return new Value(new ListObject(l));
        } else {
            String s = v.getValue().toString();
            if (s.isEmpty()) throw new ExecutorException("An empty string is a invalid input.");
            return new Value(s.substring(1));
        }
    }

    public static Value butLast(Value... arguments) throws ExecutorException {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<String> l = ((ListObject) v.getValue()).getList();
            if (l.isEmpty()) throw new ExecutorException("An empty list is a invalid input.");
            l.remove(l.size() - 1);
            return new Value(new ListObject(l));
        } else {
            String s = v.getValue().toString();
            if (s.isEmpty()) throw new ExecutorException("An empty string is a invalid input.");
            return new Value(s.substring(0, s.toCharArray().length - 1));
        }
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
        logo.forward(amount / 2.0);
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
