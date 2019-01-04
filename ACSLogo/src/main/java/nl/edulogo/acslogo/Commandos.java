package nl.edulogo.acslogo;

import javafx.application.Platform;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import nl.edulogo.acslogo.handlers.ConsoleHandler;
import nl.edulogo.acslogo.handlers.Waitable;
import nl.edulogo.acslogo.handlers.procedures.Procedure;
import nl.edulogo.acslogo.handlers.variable.LocalVariableHandler;
import nl.edulogo.acslogo.handlers.variable.PropertyHandler;
import nl.edulogo.acslogo.handlers.variable.VariableHandler;
import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.acslogo.script.commandos.ListObject;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.utils.ValueUtil;
import nl.edulogo.acslogo.utils.WaitableUtil;
import nl.edulogo.core.*;
import nl.edulogo.display.fx.FXCanvas;
import nl.edulogo.logo.Path;
import nl.edulogo.logo.Turtle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Under_Koen on 08/11/2018.
 */
public class Commandos {
    private static Random random = new Random();
    private static ACSLogo logo;

    private Commandos() {
    }

    public static Commando[] getCommandos(ACSLogo logo) {
        Commandos.logo = logo;
        List<Commando> commandos = new ArrayList<>();

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
         * -OpenAppend
         * -OpenRead
         * -OpenTextAppend
         * -OpenTextRead
         * -OpenTextWrite
         * -OpenWrite
         * -Pwd
         */

        /*TODO export:
         * -ExportEPS
         * -ExportPDF
         * -ExportTiff
         */

        /*NOT GONE IMPLEMENT
         * -Instruments
         * -Play
         * -Say
         * -SetClipPath
         * -Infinite length functions (for those that can have a list as a input will have that instead like: difference, sum, ...)
         */

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

        commandos.add(new Commando("background", Commandos::background, "bg"));

        commandos.add(new Commando("butFirst", Commandos::butFirst, 1));
        commandos.add(new Commando("butLast", Commandos::butLast, 1));

        commandos.add(new Commando("buttonP", Commandos::buttonP, "button?"));
        //Zelfde als buttonP maar dan over je rechter muis, heeft geen iplementatie van acslogo
        commandos.add(new Commando("buttonS", Commandos::buttonS));

        commandos.add(new Commando("canvasSize", Commandos::canvasSize));

        commandos.add(new Commando("catch", Commandos::catchM, 2));

        commandos.add(new Commando("char", Commandos::charM, 1));

        commandos.add(new Commando("clean", Commandos::clean));
        commandos.add(new Commando("clearScreen", Commandos::clearScreen, "cs"));

        commandos.add(new Commando("colorAtPoint", Commandos::colorAtPoint, 1, "colourAtPoint"));

        commandos.add(new Commando("cos", Commandos::cos, 1, "cosine"));
        commandos.add(new Commando("cosh", Commandos::cosh, 1));

        commandos.add(new Commando("count", Commandos::count, 1));

        commandos.add(new Commando("currentPath", Commandos::currentPath));

        commandos.add(new Commando("date", Commandos::date));

        commandos.add(new Commando("define", Commandos::define, 2));
        commandos.add(new Commando("defineP", Commandos::defineP, 1, "define?"));

        commandos.add(new Commando("difference", Commandos::difference, 1));

        commandos.add(new Commando("dot", Commandos::dot, 1));

        commandos.add(new Commando("emptyP", Commandos::emptyP, 1, "empty?"));
        commandos.add(new Commando("equalP", Commandos::equalP, 2, "equal?"));

        commandos.add(new Commando("exp", Commandos::exp, 1));

        commandos.add(new Commando("fill", Commandos::fill));
        commandos.add(new Commando("fillIn", Commandos::fillIn));
        commandos.add(new Commando("fillCurrentPath", Commandos::fillCurrentPath));
        commandos.add(new Commando("fillPath", Commandos::fillPath, 1));

        commandos.add(new Commando("first", Commandos::first, 1));
        commandos.add(new Commando("firstPut", Commandos::firstPut, 2));

        commandos.add(new Commando("fontFace", Commandos::fontFace, "font"));
        commandos.add(new Commando("fontFaces", Commandos::fontFaces, "fonts"));
        commandos.add(new Commando("fontFamilies", Commandos::fontFamilies));
        commandos.add(new Commando("fontFamily", Commandos::fontFamily));
        commandos.add(new Commando("fontTraits", Commandos::fontTraits));

        commandos.add(new Commando("getMouseChange", Commandos::getMouseChange));
        commandos.add(new Commando("getLeftMouseClick", Commandos::getLeftMouseClick, "getMouseClick"));
        commandos.add(new Commando("getRightMouseClick", Commandos::getRightMouseClick));
        commandos.add(new Commando("getMouseMoved", Commandos::getMouseMoved));

        commandos.add(new Commando("getProp", Commandos::getProp, 2, "gProp"));

        commandos.add(new Commando("graphicsType", Commandos::graphicsType, 1, "grType"));

        commandos.add(new Commando("heading", Commandos::heading));

        commandos.add(new Commando("hideTurtle", Commandos::hideTurtle, "ht"));

        commandos.add(new Commando("home", Commandos::home));

        commandos.add(new Commando("if", Commandos::ifM, 3));

        commandos.add(new Commando("integer", Commandos::integer, 1));

        commandos.add(new Commando("item", Commandos::item, 2));
        commandos.add(new Commando("last", Commandos::last, 1));
        commandos.add(new Commando("lastPut", Commandos::lastPut, 2));
        commandos.add(new Commando("list", Commandos::list, 2));
        commandos.add(new Commando("listP", Commandos::listP, 1, "list?"));

        commandos.add(new Commando("local", Commandos::local, 1));

        commandos.add(new Commando("log", Commandos::log, 1));
        commandos.add(new Commando("log10", Commandos::log10, 1));
        commandos.add(new Commando("lowerCase", Commandos::lowerCase, 1));

        commandos.add(new Commando("make", Commandos::make, 2));

        commandos.add(new Commando("memberP", Commandos::memberP, 2, "member?"));

        commandos.add(new Commando("mouse", Commandos::mouse));

        commandos.add(new Commando("nameP", Commandos::nameP, 1, "name?"));
        commandos.add(new Commando("not", Commandos::not, 1));
        commandos.add(new Commando("numberP", Commandos::numberP, 1, "number?"));
        commandos.add(new Commando("or", Commandos::or, 2));
        commandos.add(new Commando("output", Commandos::output, 1, "op"));
        commandos.add(new Commando("pathBounds", Commandos::pathBounds, 1));
        commandos.add(new Commando("pathLength", Commandos::pathLength, 1));
        commandos.add(new Commando("pen", Commandos::pen));
        commandos.add(new Commando("penColor", Commandos::penColor, "penColour", "pc"));
        commandos.add(new Commando("penDown", Commandos::penDown, "pd"));
        commandos.add(new Commando("penUp", Commandos::penUp, "pu"));
        commandos.add(new Commando("penWidth", Commandos::penWidth));
        commandos.add(new Commando("pi", Commandos::pi));
        commandos.add(new Commando("position", Commandos::position, "pos"));
        commandos.add(new Commando("power", Commandos::power, 2));
        commandos.add(new Commando("print", Commandos::print, 1));
        commandos.add(new Commando("product", Commandos::product, 1));

        commandos.add(new Commando("propList", Commandos::propList, 1, "pList"));
        commandos.add(new Commando("putProp", Commandos::putProp, 3, "pProp"));

        commandos.add(new Commando("quotient", Commandos::quotient, 1));
        commandos.add(new Commando("random", Commandos::random, 1));

        commandos.add(new Commando("readChar", Commandos::readChar));
        commandos.add(new Commando("readChars", Commandos::readChars, 1));
        commandos.add(new Commando("readList", Commandos::readList));
        commandos.add(new Commando("readWord", Commandos::readWord));

        commandos.add(new Commando("remainder", Commandos::remainder, 2));
        commandos.add(new Commando("remProp", Commandos::remProp, 2));
        commandos.add(new Commando("reverseList", Commandos::reverseList, 1, "reverse", "reversePath"));
        commandos.add(new Commando("rgb", Commandos::rgb, 1));

        commandos.add(new Commando("round", Commandos::round, 1));
        commandos.add(new Commando("run", Commandos::run, 1));

        commandos.add(new Commando("sentence", Commandos::sentence, 2));

        commandos.add(new Commando("setBackground", Commandos::setBackground, 1, "setBG"));

        commandos.add(new Commando("setCanvasSize", Commandos::setCanvasSize, 1));

        commandos.add(new Commando("setFontFace", Commandos::setFontFace, 1));
        commandos.add(new Commando("setFontFamily", Commandos::setFontFamily, 1));
        commandos.add(new Commando("setFontTraits", Commandos::setFontTraits, 1));

        commandos.add(new Commando("setFullScreen", Commandos::setFullScreen, 1));

        commandos.add(new Commando("setHeading", Commandos::setHeading, 1));

        commandos.add(new Commando("setLineCap", Commandos::setLineCap, 1));
        commandos.add(new Commando("setLineDash", Commandos::setLineDash, 1));

        return commandos.toArray(new Commando[0]);
    }

    private static Value abs(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.abs(d));
    }

    private static Value and(Value... arguments) {
        Boolean l = (Boolean) arguments[0].getValue();
        Boolean r = (Boolean) arguments[1].getValue();
        return new Value(l && r);
    }

    private static Value arc(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        double r = (Double) arguments[1].getValue();
        logo.arc(r, d);
        return null;
    }

    private static Value arcCos(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.acos(d)));
    }

    private static Value arCosh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.log(d + Math.sqrt(d * d - 1.0))));
    }

    private static Value arcSin(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.asin(d)));
    }

    private static Value arSinh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.log(d + Math.sqrt(d * d + 1.0))));
    }

    private static Value arcTan(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.atan(d)));
    }

    private static Value arTanh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(0.5 * Math.log((d + 1.0) / (d - 1.0)));
    }

    private static Value aTan2(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        double d2 = (Double) arguments[1].getValue();
        return new Value(Math.toDegrees(Math.atan2(d2, d)));
    }

    private static Value ascii(Value... arguments) {
        char c = ((String) arguments[0].getValue()).charAt(0);
        return new Value(Character.hashCode(c));
    }

    private static Value back(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.forward(-amount);
        return null;
    }

    private static Value background() {
        return new Value(logo.colorHandler.getBackground());
    }

    private static Value butFirst(Value... arguments) throws ExecutorException {
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

    private static Value butLast(Value... arguments) throws ExecutorException {
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

    private static Value buttonP() {
        return new Value(logo.mouseHandler.isLeftMouseDown());
    }

    private static Value buttonS() {
        return new Value(logo.mouseHandler.isRightMouseDown());
    }

    private static Value canvasSize() {
        Size s = logo.getCanvas().getSize();
        return new Value(new ListObject(s.getWidth(), s.getHeight()));
    }

    private static Value catchM(Value... arguments) throws ParsingException, ExecutorException {
        String error = (String) arguments[0].getValue();
        ListObject l = (ListObject) arguments[1].getValue();

        try {
            logo.runRaw(l.getInner());
        } catch (Catch ex) {
            if (!ex.getError().equals(error)) throw ex;
        }

        return null;
    }

    private static Value charM(Value... arguments) {
        int i = ((Double) arguments[0].getValue()).intValue();
        return new Value((char) i + "");
    }

    private static Value clean() {
        logo.getCanvas().fillScreen(logo.colorHandler.getBackgroundColor());
        logo.getTurtle().getPath().clear();
        return null;
    }

    private static Value clearScreen() {
        clean();
        home();
        logo.getTurtle().setPenDown(true);
        return null;
    }

    private static Value colorAtPoint(Value... arguments) throws ExecutorException {
        //TODO fix bigger than canvas and wrong thread
        List<Value> l = ((ListObject) arguments[0].getValue()).getList();
        if (l.size() != 2) throw new ExecutorException("Color at point needs a list with two numbers");
        Position relative = logo.getStart();
        int x = (int) (l.get(0).getAsNumber() - relative.getX());
        int y = (int) (l.get(1).getAsNumber() - relative.getY());

        FXCanvas fxCanvas = (FXCanvas) logo.getCanvas();
        WritableImage img = fxCanvas.getNode().snapshot(new SnapshotParameters(), null);
        javafx.scene.paint.Color color = img.getPixelReader().getColor(x, y);

        List<Object> r = new ArrayList<>();
        r.add((int) (color.getRed() * 255));
        r.add((int) (color.getGreen() * 255));
        r.add((int) (color.getBlue() * 255));

        return new Value(new ListObject(r));
    }

    private static Value cos(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.cos(Math.toRadians(d)));
    }

    private static Value cosh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.cosh(Math.toRadians(d)));
    }

    private static Value count(Value... arguments) {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> l = ((ListObject) v.getValue()).getList();
            return new Value(l.size());
        } else {
            String s = v.toString();
            return new Value(s.toCharArray().length);
        }
    }

    private static Value currentPath() {
        List<Value> l = new ArrayList<>();
        Position[] points = logo.getTurtle().getPath().getPoints();
        for (Position pos : points) {
            l.add(ValueUtil.positionToValue(pos, logo.getStart()));
        }
        return new Value(new ListObject(l));
    }

    private static Value date() {
        String s = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return new Value(s);
    }

    private static Value define(Value... arguments) throws ExecutorException, ParsingException {
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

    private static Value defineP(Value... arguments) {
        String name = (String) arguments[0].getValue();
        return new Value(logo.procedureHandler.getProcedures().keySet().contains(name));
    }

    private static Value difference(Value... arguments) throws ExecutorException {
        List<Value> list = arguments[0].getAsList().getList();
        Double r = null;
        for (Value value : list) {
            if (r == null) {
                r = value.getAsNumber();
            } else {
                r = r - value.getAsNumber();
            }
        }
        return new Value(r);
    }

    private static Value dot(Value... arguments) {
        List<Value> list = ((ListObject) arguments[0].getValue()).getList();
        double x = (double) list.get(0).getValue();
        double y = (double) list.get(1).getValue();
        logo.getCanvas().drawDot(new Position(x, y));
        return null;
    }

    private static Value emptyP(Value... arguments) {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> l = ((ListObject) v.getValue()).getList();
            return new Value(l.isEmpty());
        } else {
            String s = v.toString();
            return new Value(s.isEmpty());
        }
    }

    private static Value equalP(Value... arguments) {
        String s1 = arguments[0].toString();
        String s2 = arguments[1].toString();
        return new Value(s1.equals(s2));
    }

    private static Value exp(Value... arguments) {
        Double d = (double) arguments[0].getValue();
        return new Value(Math.exp(d));
    }

    private static Value fill() {
        //TODO Paint bucket mode oposite until same color
        return null;
    }

    private static Value fillIn() {
        //TODO Paint bucket mode
        return null;
    }

    private static Value fillCurrentPath() {
        logo.fillPath();
        return null;
    }

    private static Value fillPath(Value... arguments) throws ExecutorException {
        List<Value> list = ((ListObject) arguments[0].getValue()).getList();
        Path path = new Path();

        Position start = logo.getStart();
        for (Value value : list) {
            if (value.getType() != Value.ValueType.LIST) throw new ExecutorException("Path is not correct");
            List<Value> location = ((ListObject) value.getValue()).getList();
            double x = location.get(0).getAsNumber() + start.getX();
            double y = -location.get(1).getAsNumber() + start.getY();
            path.addPoint(new Position(x, y));
        }
        logo.fillPath(path);
        return null;
    }

    private static Value first(Value... arguments) throws ExecutorException {
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

    private static Value firstPut(Value... arguments) {
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

    private static Value fontFace() {
        Font font = logo.getTurtle().getFont();
        return ValueUtil.fontToValue(font);
    }

    private static Value fontFaces() {
        List<Value> fonts = javafx.scene.text.Font.getFontNames().stream()
                .map(ValueUtil::fontToValue)
                .collect(Collectors.toList());
        return new Value(new ListObject(fonts));
    }

    private static Value fontFamilies() {
        List<Value> fonts = javafx.scene.text.Font.getFamilies().stream()
                .map(ValueUtil::fontToValue)
                .collect(Collectors.toList());
        return new Value(new ListObject(fonts));
    }

    private static Value fontFamily() {
        Font font = logo.getTurtle().getFont();
        return ValueUtil.fontToValue(new javafx.scene.text.Font(font.getName(), font.getSize()).getFamily());
    }

    private static Value fontTraits() {
        Font font = logo.getTurtle().getFont();
        return ValueUtil.fontToValue(new javafx.scene.text.Font(font.getName(), font.getSize()).getStyle());
    }

    private static Value forward(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.forward(amount);
        return null;
    }

    private static Value getMouseChange() {
        Waitable<Position> left = logo.mouseHandler.getLeftMouseClick();
        Waitable<Position> right = logo.mouseHandler.getRightMouseClick();
        Waitable<Position> moved = logo.mouseHandler.getMouseMove();

        Waitable updated = WaitableUtil.waitFor(left, right, moved);
        Position pos = (Position) updated.reset();

        return new Value(new ListObject(ValueUtil.positionToValue(pos, logo.getStart()), new Value(updated == left), new Value(updated == right)));
    }

    private static Value getLeftMouseClick() {
        Waitable<Position> left = logo.mouseHandler.getLeftMouseClick();
        Position pos = WaitableUtil.waitFor(left);
        return ValueUtil.positionToValue(pos, logo.getStart());
    }

    private static Value getRightMouseClick() {
        Waitable<Position> right = logo.mouseHandler.getRightMouseClick();
        Position pos = WaitableUtil.waitFor(right);
        return ValueUtil.positionToValue(pos, logo.getStart());
    }

    private static Value getMouseMoved() {
        Waitable<Position> moved = logo.mouseHandler.getMouseMove();
        Position pos = WaitableUtil.waitFor(moved);
        return ValueUtil.positionToValue(pos, logo.getStart());
    }

    private static Value getProp(Value... arguments) throws ExecutorException {
        String name = arguments[0].getAsString();
        String property = arguments[1].getAsString();
        Value r = logo.propertyHandler.getProperty(name, property);
        if (r == null) throw new ExecutorException("This property doesn't exist.");
        return r;
    }

    private static Value graphicsType(Value... arguments) throws ExecutorException {
        Value v = arguments[0];
        String text;
        if (v.getType() == Value.ValueType.LIST) {
            ListObject list = v.getAsList();
            text = list.getInner();
        } else {
            text = v.getAsString();
        }
        logo.writeRotated(text);
        return null;
    }

    private static Value heading() {
        return new Value((360.0 - logo.getTurtle().getRotation()) % 360);
    }

    private static Value hideTurtle() {
        logo.getTurtleGraphics().hide();
        return null;
    }

    private static Value home(Value... arguments) {
        Size s = logo.getCanvas().getSize();
        Position newp = new Position(s.getWidth() / 2.0, s.getHeight() / 2.0);
        logo.setStart(newp);
        logo.getTurtle().setPosition(newp);
        logo.getTurtle().setRotation(0);
        return null;
    }

    private static Value ifM(Value... arguments) throws ParsingException, ExecutorException {
        boolean b = arguments[0].getAsBoolean();
        ListObject trueL = arguments[1].getAsList();
        ListObject falseL = arguments[2].getAsList();

        return logo.runRaw((b) ? trueL.getInner() : falseL.getInner());
    }

    private static Value integer(Value... arguments) throws ExecutorException {
        return new Value((double) (int) arguments[0].getAsNumber());
    }

    private static Value item(Value... arguments) throws ExecutorException {
        int item = (int) arguments[0].getAsNumber() - 1;
        Value v = arguments[1];
        if (v.getType() == Value.ValueType.LIST) {
            ListObject list = v.getAsList();
            return list.getList().get(item);
        } else {
            String s = v.getAsString();
            return new Value(String.valueOf(s.charAt(item)));
        }
    }

    private static Value last(Value... arguments) throws ExecutorException {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> l = ((ListObject) v.getValue()).getList();
            if (l.isEmpty()) throw new ExecutorException("An empty list is a invalid input.");
            return l.get(l.size() - 1);
        } else {
            String s = v.toString();
            if (s.isEmpty()) throw new ExecutorException("An empty string is a invalid input.");
            return new Value(String.valueOf(s.charAt(s.length() - 1)));
        }
    }

    private static Value lastPut(Value... arguments) {
        Value add = arguments[0];
        Value to = arguments[1];
        if (to.getType() == Value.ValueType.LIST) {
            List<Value> list = ((ListObject) to.getValue()).getList();
            list.add(add);
            return new Value(new ListObject(list));
        } else {
            return new Value(to.toString() + add.toString());
        }
    }

    private static Value left(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.left(amount);
        return null;
    }

    private static Value list(Value... arguments) {
        Value first = arguments[0];
        Value second = arguments[1];
        return new Value(new ListObject(first, second));
    }

    private static Value listP(Value... arguments) {
        return new Value(arguments[0].getType() == Value.ValueType.LIST);
    }

    private static Value local(Value... arguments) throws ExecutorException {
        LocalVariableHandler variableHandler = Procedure.current;
        if (variableHandler == null) throw new ExecutorException("This method can only be used in procedures.");
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            for (Value value : v.getAsList().getList()) {
                variableHandler.makeLocal(value.getAsString());
            }
        } else {
            variableHandler.makeLocal(v.getAsString());
        }
        return null;
    }

    private static Value log(Value... arguments) throws ExecutorException {
        return new Value(Math.log(arguments[0].getAsNumber()));
    }

    private static Value log10(Value... arguments) throws ExecutorException {
        return new Value(Math.log10(arguments[0].getAsNumber()));
    }

    private static Value lowerCase(Value... arguments) {
        return ValueUtil.stringToValue(arguments[0].toString().toLowerCase());
    }

    private static Value make(Value... arguments) throws ExecutorException {
        VariableHandler variableHandler = Procedure.current;
        if (variableHandler == null) variableHandler = logo.getExecutor().getVariableHandler();
        String name = arguments[0].getAsString();
        Value value = arguments[1];
        variableHandler.setVariable(name, value);
        return null;
    }

    private static Value memberP(Value... arguments) throws ExecutorException {
        Value v = arguments[1];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> list = v.getAsList().getList();
            return new Value(list.contains(arguments[0]));
        } else {
            String s = v.getAsString();
            return new Value(s.contains(arguments[0].getAsString()));
        }
    }

    private static Value mouse() {
        return ValueUtil.positionToValue(logo.mouseHandler.getMouse(), logo.getStart());
    }

    private static Value nameP(Value... arguments) throws ExecutorException {
        VariableHandler variableHandler = Procedure.current;
        if (variableHandler == null) variableHandler = logo.getExecutor().getVariableHandler();

        String name = arguments[0].getAsString();
        return new Value(variableHandler.contains(name));
    }

    private static Value not(Value... arguments) throws ExecutorException {
        return new Value(!arguments[0].getAsBoolean());
    }

    private static Value numberP(Value... arguments) {
        return new Value(ValueUtil.stringToValue(arguments[0].toString()).getType() == Value.ValueType.NUMBER);
    }

    private static Value or(Value... arguments) throws ExecutorException {
        return new Value(arguments[0].getAsBoolean() || arguments[1].getAsBoolean());
    }

    private static Value output(Value... arguments) {
        throw new Output(arguments[0]);
    }

    private static Value pathBounds(Value... arguments) throws ExecutorException {
        double minX = 0;
        double minY = 0;
        double maxX = 0;
        double maxY = 0;

        for (Value value : arguments[0].getAsList().getList()) {
            List<Value> pos = value.getAsList().getList();
            double x = pos.get(0).getAsNumber();
            double y = pos.get(1).getAsNumber();
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }

        double widht = Math.abs(maxX - minX);
        double height = Math.abs(maxY - minY);

        return new Value(new ListObject(minX, minY, Math.abs(maxX - minX), Math.abs(maxY - minY)));
    }

    private static Value pathLength(Value... arguments) throws ExecutorException {
        return new Value(arguments[0].getAsList().getList().size());
    }

    private static Value pen() {
        Turtle turtle = logo.getTurtle();
        int color = logo.colorHandler.getPen();
        return new Value(new ListObject(turtle.isPenDown(), color));
    }

    private static Value penColor() {
        int color = logo.colorHandler.getPen();
        return new Value(color);
    }

    private static Value penUp() {
        logo.getTurtle().setPenDown(false);
        return null;
    }

    private static Value penDown() {
        logo.getTurtle().setPenDown(true);
        return null;
    }

    private static Value penWidth() {
        return new Value(logo.getTurtle().getPenWidth());
    }

    private static Value pi() {
        return new Value(Math.PI);
    }

    private static Value position() {
        return ValueUtil.positionToValue(logo.getTurtle().getPosition(), logo.getStart());
    }

    private static Value power(Value... arguments) throws ExecutorException {
        return new Value(Math.pow(arguments[0].getAsNumber(), arguments[1].getAsNumber()));
    }

    private static Value print(Value... arguments) throws ExecutorException {
        Value v = arguments[0];
        ConsoleHandler console = logo.getExecutor().getConsoleHandler();
        if (v.getType() == Value.ValueType.LIST) {
            ListObject list = arguments[0].getAsList();
            console.output(list.getInner());
        } else {
            console.output(arguments[0]);
        }
        return null;
    }

    private static Value product(Value... arguments) throws ExecutorException {
        List<Value> list = arguments[0].getAsList().getList();
        Double r = null;
        for (Value value : list) {
            if (r == null) {
                r = value.getAsNumber();
            } else {
                r = r * value.getAsNumber();
            }
        }
        return new Value(r);
    }

    private static Value propList(Value... arguments) throws ExecutorException {
        String name = arguments[0].getAsString();
        PropertyHandler propertyHandler = logo.propertyHandler;

        Map<String, Value> prop = propertyHandler.getProperties(name);
        if (prop == null) throw new ExecutorException("This property doesn't exist.");

        List<Value> list = new ArrayList<>();
        for (Map.Entry<String, Value> entry : prop.entrySet()) {
            list.add(new Value(entry.getKey()));
            list.add(entry.getValue());
        }

        return new Value(new ListObject(list));
    }

    private static Value putProp(Value... arguments) throws ExecutorException {
        String name = arguments[0].getAsString();
        String property = arguments[1].getAsString();
        Value value = arguments[2];
        logo.propertyHandler.setProperty(name, property, value);
        return null;
    }

    private static Value quotient(Value... arguments) throws ExecutorException {
        List<Value> list = arguments[0].getAsList().getList();
        Double r = null;
        for (Value value : list) {
            if (r == null) {
                r = value.getAsNumber();
            } else {
                r = r / value.getAsNumber();
            }
        }
        return new Value(r);
    }

    private static Value random(Value... arguments) throws ExecutorException {
        int max = (int) arguments[0].getAsNumber();
        double r = random.nextInt(Math.max(max, 1));
        return new Value(r);
    }

    private static Value readChar() {
        ConsoleHandler console = logo.getExecutor().getConsoleHandler();
        console.waitFor((text, key) -> text.length() >= 1);
        console.enableInput();
        String s = String.valueOf(WaitableUtil.waitFor(console.getInput()).charAt(0));
        console.disableInput();
        return ValueUtil.stringToValue(s);
    }

    private static Value readChars(Value... arguments) throws ExecutorException {
        int count = (int) arguments[0].getAsNumber();
        if (count < 1) throw new ExecutorException("Input for readChars should be bigger or equals to 1.");
        ConsoleHandler console = logo.getExecutor().getConsoleHandler();
        console.waitFor((text, key) -> text.length() >= count);
        console.enableInput();
        String s = WaitableUtil.waitFor(console.getInput()).substring(0, count);
        console.disableInput();
        return ValueUtil.stringToValue(s);
    }

    private static Value readList() {
        ConsoleHandler console = logo.getExecutor().getConsoleHandler();
        console.waitFor((text, key) -> key == KeyCode.ENTER);
        console.enableInput();
        String s = String.format("[%s]", WaitableUtil.waitFor(console.getInput()));
        console.disableInput();
        return ValueUtil.stringToListValue(s);
    }

    private static Value readWord() {
        ConsoleHandler console = logo.getExecutor().getConsoleHandler();
        console.waitFor((text, key) -> key == KeyCode.ENTER);
        console.enableInput();
        String s = WaitableUtil.waitFor(console.getInput());
        console.disableInput();
        return ValueUtil.stringToValue(s);
    }

    private static Value remainder(Value... arguments) throws ExecutorException {
        double first = arguments[0].getAsNumber();
        double second = arguments[1].getAsNumber();
        return new Value(first % second);
    }

    private static Value remProp(Value... arguments) throws ExecutorException {
        String name = arguments[0].getAsString();
        String property = arguments[1].getAsString();
        logo.propertyHandler.removeProperty(name, property);
        return null;
    }

    private static Value repeat(Value... arguments) throws ParsingException, ExecutorException {
        int t = ((Double) arguments[0].getValue()).intValue();
        ListObject l = (ListObject) arguments[1].getValue();
        for (int i = 0; i < t; i++) {
            logo.runRaw(l.getInner());
        }
        return null;
    }

    private static Value reverseList(Value... arguments) throws ExecutorException {
        List<Value> list = arguments[0].getAsList().getList();
        Collections.reverse(list);
        return new Value(new ListObject(list));
    }

    private static Value rgb(Value... arguments) throws ExecutorException {
        int c = (int) arguments[0].getAsNumber();
        Color color = logo.colorHandler.getColor(c);
        return new Value(new ListObject(color.getRed(), color.getGreen(), color.getBlue()));
    }

    private static Value right(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.right(amount);
        return null;
    }

    private static Value round(Value... arguments) throws ExecutorException {
        double d = arguments[0].getAsNumber();
        return new Value(Math.round(d));
    }

    private static Value run(Value... arguments) throws ExecutorException, ParsingException {
        ListObject list = arguments[0].getAsList();
        return logo.runRaw(list.getInner());
    }

    private static Value sentence(Value... arguments) throws ExecutorException {
        Value first = arguments[0];
        Value second = arguments[1];
        List<Value> list = new ArrayList<>();

        Value[] check = {first, second};
        for (Value value : check) {
            if (value.getType() == Value.ValueType.LIST) {
                list.addAll(value.getAsList().getList());
            } else {
                list.add(value);
            }
        }

        return new Value(new ListObject(list));
    }

    private static Value setBackground(Value... arguments) throws ExecutorException {
        int color = (int) arguments[0].getAsNumber();
        logo.colorHandler.setBackground(color);
        return null;
    }

    private static Value setCanvasSize(Value... arguments) throws ExecutorException {
        List<Value> list = arguments[0].getAsList().getList();
        int width = (int) list.get(0).getAsNumber();
        int height = (int) list.get(1).getAsNumber();
        logo.getCanvas().setSize(new Size(width, height));
        clearScreen();
        return null;
    }

    private static Value setFontFace(Value... arguments) throws ExecutorException {
        String fontFace = arguments[0].getAsList().getInner();
        checkFont(fontFace);

        Font old = logo.getTurtle().getFont();
        logo.getTurtle().setFont(new Font(fontFace, old.getSize(), old.getColor()));
        return null;
    }

    private static Value setFontFamily(Value... arguments) throws ExecutorException {
        String fontFamily = arguments[0].getAsList().getInner();
        checkFont(fontFamily);

        Font old = logo.getTurtle().getFont();
        String style = new javafx.scene.text.Font(old.getName(), old.getSize()).getStyle();

        String name = String.format("%s %s", fontFamily, style);
        String s = new javafx.scene.text.Font(name, old.getSize()).getName();
        if (!s.equalsIgnoreCase(name)) name = fontFamily;

        logo.getTurtle().setFont(new Font(name, old.getSize(), old.getColor()));
        return null;
    }

    private static Value setFontTraits(Value... arguments) throws ExecutorException {
        Font old = logo.getTurtle().getFont();
        String s = new javafx.scene.text.Font(old.getName(), old.getSize()).getFamily();

        String style = arguments[0].getAsList().getInner();
        String name = String.format(String.format("%s %s", s, style).trim());
        checkFont(name);

        logo.getTurtle().setFont(new Font(name, old.getSize(), old.getColor()));
        return null;
    }

    private static Value setFullScreen(Value... arguments) throws ExecutorException {
        boolean b = arguments[0].getAsBoolean();
        Platform.runLater(() -> ((Stage) ((FXCanvas) logo.getCanvas()).getCanvas().getScene().getWindow()).setFullScreen(b));
        return null;
    }

    private static Value setHeading(Value... arguments) throws ExecutorException {
        double d = arguments[0].getAsNumber();
        logo.getTurtle().setRotation(360.0 - d);
        return null;
    }

    private static Value setLineCap(Value... arguments) throws ExecutorException {
        String s = arguments[0].getAsString();
        LineCap lineCap;
        switch (s.toLowerCase()) {
            case "butt":
                lineCap = LineCap.BUTT;
                break;
            case "round":
                lineCap = LineCap.ROUND;
                break;
            case "square":
                lineCap = LineCap.SQUARE;
                break;
            default:
                throw new ExecutorException(String.format("LineCap should be one of these: butt, round or square."));
        }
        logo.getCanvas().setLineCap(lineCap);
        return null;
    }

    private static Value setLineDash(Value... arguments) throws ExecutorException {
        List<Value> list = arguments[0].getAsList().getList();
        if (list.isEmpty()) {
            logo.getCanvas().setLineDash(0);
            return null;
        }

        double offset = list.remove(0).getAsNumber();
        double[] dashes = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            double d = list.get(i).getAsNumber();
            dashes[i] = d;
        }

        logo.getCanvas().setLineDash(offset, dashes);
        return null;
    }

    private static void checkFont(String font) throws ExecutorException {
        Font old = logo.getTurtle().getFont();
        String s = new javafx.scene.text.Font(font, 12).getName();
        if (!s.equalsIgnoreCase(font.trim()))
            throw new ExecutorException(String.format("Font %s doesn't exist.", font));
    }
}