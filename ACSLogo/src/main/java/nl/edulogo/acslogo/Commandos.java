package nl.edulogo.acslogo;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import nl.edulogo.acslogo.handlers.mouse.Waitable;
import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.parser.pieces.ListPiece.ListObject;
import nl.edulogo.acslogo.utils.ValueUtil;
import nl.edulogo.acslogo.utils.WaitableUtil;
import nl.edulogo.core.Font;
import nl.edulogo.core.Position;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXCanvas;
import nl.edulogo.logo.Path;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        commandos.add(new Commando("ascii", Commandos::ascii, 1));

        commandos.add(new Commando("background", Commandos::background, 0, "bg"));

        commandos.add(new Commando("butFirst", Commandos::butFirst, 1));
        commandos.add(new Commando("butLast", Commandos::butLast, 1));

        commandos.add(new Commando("buttonP", Commandos::buttonP, 0, "button?"));
        //Zelfde als buttonP maar dan over je rechter muis, heeft geen iplementatie van acslogo
        commandos.add(new Commando("buttonS", Commandos::buttonS, 0));

        commandos.add(new Commando("canvasSize", Commandos::canvasSize, 0));

        commandos.add(new Commando("catch", Commandos::catchM, 2));

        /*TODO FILE STUFF:
         * -CD
         * -CloseReadFile
         * -CloseWriteFile
         * -Dir
         * -DrawImage
         * -EofP
         * -FPrint
         * -FReadChar
         * -FReadChars
         * -FReadList
         * -FReadWord
         * -FShow
         * -FType
         */

        commandos.add(new Commando("char", Commandos::charM, 1));

        commandos.add(new Commando("clean", Commandos::clean, 0));
        commandos.add(new Commando("clearScreen", Commandos::clearScreen, 0, "cs"));

        commandos.add(new Commando("colorAtPoint", Commandos::colorAtPoint, 1, "colourAtPoint"));

        commandos.add(new Commando("cos", Commandos::cos, 1, "cosine"));
        commandos.add(new Commando("cosh", Commandos::cosh, 1));

        commandos.add(new Commando("count", Commandos::count, 1));

        commandos.add(new Commando("currentPath", Commandos::currentPath, 0));

        commandos.add(new Commando("date", Commandos::date, 0));

        commandos.add(new Commando("define", Commandos::define, 2));
        commandos.add(new Commando("defineP", Commandos::defineP, 1, "define?"));

        commandos.add(new Commando("difference", Commandos::difference, 2));

        commandos.add(new Commando("dot", Commandos::dot, 1));

        commandos.add(new Commando("emptyP", Commandos::emptyP, 1, "empty?"));
        commandos.add(new Commando("equalP", Commandos::equalP, 2, "equal?"));

        commandos.add(new Commando("exp", Commandos::exp, 1));

        /*TODO export
         * ExportEPS
         * ExportPDF
         * ExportTiff
         */

        commandos.add(new Commando("fill", Commandos::fill, 0));
        commandos.add(new Commando("fillIn", Commandos::fillIn, 0));
        commandos.add(new Commando("fillCurrentPath", Commandos::fillCurrentPath, 0));
        commandos.add(new Commando("fillPath", Commandos::fillPath, 1));

        commandos.add(new Commando("first", Commandos::first, 1));
        commandos.add(new Commando("firstPut", Commandos::firstPut, 2));

        commandos.add(new Commando("fontFace", Commandos::fontFace, 0, "font"));
        commandos.add(new Commando("fontFaces", Commandos::fontFaces, 0, "fonts"));
        commandos.add(new Commando("fontFamilies", Commandos::fontFamilies, 0));
        commandos.add(new Commando("fontFamily", Commandos::fontFamily, 0));
        commandos.add(new Commando("fontTraits", Commandos::fontTraits, 0));

        commandos.add(new Commando("getMouseChange", Commandos::getMouseChange, 0));
        commandos.add(new Commando("getLeftMouseClick", Commandos::getLeftMouseClick, 0, "getMouseClick"));
        commandos.add(new Commando("getRightMouseClick", Commandos::getRightMouseClick, 0));
        commandos.add(new Commando("getMouseMoved", Commandos::getMouseMoved, 0));


        commandos.add(new Commando("home", Commandos::home, 0));

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

    public static Value back(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.forward(-amount);
        return null;
    }

    public static Value background(Value... arguments) {
        return new Value(logo.colorHandler.getBackground());
    }

    public static Value butFirst(Value... arguments) throws ExecutorException {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> l = ((ListObject) v.getValue()).getList();
            if (l.isEmpty()) throw new ExecutorException("An empty list is a invalid input.");
            l.remove(0);
            return new Value(new ListObject(l));
        } else {
            String s = v.toString();
            if (s.isEmpty()) throw new ExecutorException("An empty string is a invalid input.");
            return new Value(s.substring(1));
        }
    }

    public static Value butLast(Value... arguments) throws ExecutorException {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> l = ((ListObject) v.getValue()).getList();
            if (l.isEmpty()) throw new ExecutorException("An empty list is a invalid input.");
            l.remove(l.size() - 1);
            return new Value(new ListObject(l));
        } else {
            String s = v.toString();
            if (s.isEmpty()) throw new ExecutorException("An empty string is a invalid input.");
            return new Value(s.substring(0, s.toCharArray().length - 1));
        }
    }

    public static Value buttonP(Value... arguments) {
        return new Value(logo.mouseHandler.isLeftMouseDown());
    }

    public static Value buttonS(Value... arguments) {
        return new Value(logo.mouseHandler.isRightMouseDown());
    }

    public static Value canvasSize(Value... arguments) {
        Size s = logo.getCanvas().getSize();
        return new Value(new ListObject(s.getWidth(), s.getHeight()));
    }

    public static Value catchM(Value... arguments) throws ParsingException, ExecutorException {
        String error = (String) arguments[0].getValue();
        ListObject l = (ListObject) arguments[1].getValue();

        try {
            logo.runRaw(l.getInner());
        } catch (Catch ex) {
            if (!ex.getError().equals(error)) throw ex;
        }

        return null;
    }

    public static Value charM(Value... arguments) {
        int i = ((Double) arguments[0].getValue()).intValue();
        return new Value((char) i + "");
    }

    public static Value clean(Value... arguments) {
        logo.getCanvas().fillScreen(logo.colorHandler.getBackgroundColor());
        logo.getTurtle().getPath().clear();
        return null;
    }

    public static Value clearScreen(Value... arguments) {
        clean();
        home();
        logo.getTurtle().setPenDown(true);
        return null;
    }

    public static Value colorAtPoint(Value... arguments) throws ExecutorException {
        //TODO fix bigger than canvas and fix that [0 0] is middle of screen
        List<Value> l = ((ListObject) arguments[0].getValue()).getList();
        if (l.size() != 2) throw new ExecutorException("Color at point needs a list with two numbers");
        int x = (int) l.get(0).getValue();
        int y = (int) l.get(1).getValue();

        FXCanvas fxCanvas = (FXCanvas) logo.getCanvas();
        WritableImage img = fxCanvas.getNode().snapshot(new SnapshotParameters(), null);
        Color color = img.getPixelReader().getColor(x, y);

        List<Object> r = new ArrayList<>();
        r.add((int) (color.getRed() * 255));
        r.add((int) (color.getGreen() * 255));
        r.add((int) (color.getBlue() * 255));

        return new Value(new ListObject(r));
    }

    public static Value cos(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.cos(Math.toRadians(d)));
    }

    public static Value cosh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.cosh(Math.toRadians(d)));
    }

    public static Value count(Value... arguments) {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> l = ((ListObject) v.getValue()).getList();
            return new Value(l.size());
        } else {
            String s = v.toString();
            return new Value(s.toCharArray().length);
        }
    }

    public static Value currentPath(Value... arguments) {
        List<Value> l = new ArrayList<>();
        Position[] points = logo.getTurtle().getPath().getPoints();
        for (Position pos : points) {
            l.add(ValueUtil.positionToValue(pos));
        }
        return new Value(new ListObject(l));
    }

    public static Value date(Value... arguments) {
        String s = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return new Value(s);
    }

    public static Value define(Value... arguments) throws ExecutorException, ParsingException {
        String name = (String) arguments[0].getValue();
        ListObject l = (ListObject) arguments[1].getValue();
        Script script = logo.getParser().parse(l.getInner());

        if (script.getPieces().size() != 2)
            throw new ExecutorException("Define should have two list inside the list.");
        ListObject para = (ListObject) script.getPieces().get(0).getValue().getValue();
        ListObject code = (ListObject) script.getPieces().get(1).getValue().getValue();

        logo.procedureHandler.registerProcedure(name, para.getStringList(), code.getInner());

        return null;
    }

    public static Value defineP(Value... arguments) {
        String name = (String) arguments[0].getValue();
        return new Value(logo.procedureHandler.getProcedures().keySet().contains(name));
    }

    public static Value difference(Value... arguments) {
        double d1 = (double) arguments[0].getValue();
        double d2 = (double) arguments[1].getValue();
        return new Value(d1 - d2);
    }

    public static Value dot(Value... arguments) {
        List<Value> list = ((ListObject) arguments[0].getValue()).getList();
        double x = (double) list.get(0).getValue();
        double y = (double) list.get(1).getValue();
        logo.getCanvas().drawDot(new Position(x, y));
        return null;
    }

    public static Value emptyP(Value... arguments) {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> l = ((ListObject) v.getValue()).getList();
            return new Value(l.isEmpty());
        } else {
            String s = v.toString();
            return new Value(s.isEmpty());
        }
    }

    public static Value equalP(Value... arguments) {
        String s1 = arguments[0].toString();
        String s2 = arguments[1].toString();
        return new Value(s1.equals(s2));
    }

    public static Value exp(Value... arguments) {
        Double d = (double) arguments[0].getValue();
        return new Value(Math.exp(d));
    }

    public static Value fill(Value... arguments) {
        //TODO Paint bucket mode oposite until same color
        return null;
    }

    public static Value fillIn(Value... arguments) {
        //TODO Paint bucket mode
        return null;
    }

    public static Value fillCurrentPath(Value... arguments) {
        logo.fillPath();
        return null;
    }

    public static Value fillPath(Value... arguments) throws ExecutorException {
        List<Value> list = ((ListObject) arguments[0].getValue()).getList();
        Path path = new Path();

        for (Value value : list) {
            if (value.getType() != Value.ValueType.LIST) throw new ExecutorException("Path is not correct");
            List<Value> location = ((ListObject) value.getValue()).getList();
            double x = (double) location.get(0).getValue();
            double y = (double) location.get(1).getValue();
            path.addPoint(new Position(x, y));
        }
        logo.fillPath(path);
        return null;
    }

    public static Value first(Value... arguments) throws ExecutorException {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> l = ((ListObject) v.getValue()).getList();
            if (l.isEmpty()) throw new ExecutorException("An empty list is a invalid input.");
            return l.get(0);
        } else {
            String s = v.toString();
            if (s.isEmpty()) throw new ExecutorException("An empty string is a invalid input.");
            return new Value(String.valueOf(s.charAt(0)));
        }
    }

    public static Value firstPut(Value... arguments) {
        Value add = arguments[0];
        Value to = arguments[1];
        if (to.getType() == Value.ValueType.LIST) {
            List<Value> list = ((ListObject) to.getValue()).getList();
            list.add(0, add);
            return new Value(new ListObject(list));
        } else {
            return new Value(add.toString() + to.toString());
        }
    }

    public static Value fontFace(Value... arguments) {
        Font font = logo.getTurtle().getFont();
        return ValueUtil.fontToValue(font);
    }

    public static Value fontFaces(Value... arguments) {
        List<Value> fonts = javafx.scene.text.Font.getFontNames().stream()
                .map(ValueUtil::fontToValue)
                .collect(Collectors.toList());
        return new Value(new ListObject(fonts));
    }

    public static Value fontFamilies(Value... arguments) {
        List<Value> fonts = javafx.scene.text.Font.getFamilies().stream()
                .map(ValueUtil::fontToValue)
                .collect(Collectors.toList());
        return new Value(new ListObject(fonts));
    }

    public static Value fontFamily(Value... arguments) {
        Font font = logo.getTurtle().getFont();
        return ValueUtil.fontToValue(new javafx.scene.text.Font(font.getName(), font.getSize()).getFamily());
    }

    public static Value fontTraits(Value... arguments) {
        Font font = logo.getTurtle().getFont();
        return ValueUtil.fontToValue(new javafx.scene.text.Font(font.getName(), font.getSize()).getStyle());
    }

    public static Value forward(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.forward(amount / 2.0);
        return null;
    }

    public static Value getMouseChange(Value... arguments) {
        Waitable<Position> left = logo.mouseHandler.getLeftMouseClick();
        Waitable<Position> right = logo.mouseHandler.getRightMouseClick();
        Waitable<Position> moved = logo.mouseHandler.getMouseMove();

        Waitable updated = WaitableUtil.waitFor(left, right, moved);
        Position pos = (Position) updated.reset();

        return new Value(new ListObject(ValueUtil.positionToValue(pos), new Value(updated == left), new Value(updated == right)));
    }

    public static Value getLeftMouseClick(Value... arguments) {
        Waitable<Position> left = logo.mouseHandler.getLeftMouseClick();
        Position pos = WaitableUtil.waitFor(left);
        return ValueUtil.positionToValue(pos)
    }

    public static Value getRightMouseClick(Value... arguments) {
        Waitable<Position> right = logo.mouseHandler.getRightMouseClick();
        Position pos = WaitableUtil.waitFor(right);
        return ValueUtil.positionToValue(pos)
    }

    public static Value getMouseMoved(Value... arguments) {
        Waitable<Position> moved = logo.mouseHandler.getMouseMove();
        Position pos = WaitableUtil.waitFor(moved);
        return ValueUtil.positionToValue(pos)
    }

    public static Value home(Value... arguments) {
        Size s = logo.getCanvas().getSize();
        logo.getTurtle().setPosition(new Position(s.getWidth() / 2.0, s.getHeight() / 2.0));
        logo.getTurtle().setRotation(0);
        return null;
    }

    public static Value repeat(Value... arguments) throws ParsingException, ExecutorException {
        int t = ((Double) arguments[0].getValue()).intValue();
        ListObject l = (ListObject) arguments[1].getValue();
        for (int i = 0; i < t; i++) {
            logo.runRaw(l.getInner());
        }
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
