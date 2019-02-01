package nl.edulogo.acslogo;

import javafx.application.Platform;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
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
import nl.edulogo.core.Color;
import nl.edulogo.core.Font;
import nl.edulogo.core.Image;
import nl.edulogo.core.utils.MathUtil;
import nl.edulogo.display.fx.FXCanvas;
import nl.edulogo.logo.Path;
import nl.edulogo.logo.Turtle;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Under_Koen on 08/11/2018.
 */
public class Commandos {
    private static Random random = new Random();
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("kk:mm:ss:SSS");
    private ACSLogo logo;

    public Commandos(ACSLogo logo) {
        this.logo = logo;
    }

    public Commando[] getCommandos() {
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
         * -Fill
         * -FillIn
         * -Play
         * -Say
         * -SetClipPath
         * -SetShadow
         * -SetVoice
         * -SetWrap
         * -Snap
         * -Voice
         * -Voices
         * -WaitForSpeech
         * -Wrap
         * -Infinite length functions (for those that can have a list as a input will have that instead like: difference, sum, ...)
         */

        commandos.add(new Commando("forward", this::forward, 1, "fd"));
        commandos.add(new Commando("back", this::back, 1));
        commandos.add(new Commando("right", this::right, 1, "rt"));
        commandos.add(new Commando("left", this::left, 1, "lt"));

        commandos.add(new Commando("repeat", this::repeat, 2));

        commandos.add(new Commando("abs", this::abs, 1));
        commandos.add(new Commando("and", this::and, 2));
        commandos.add(new Commando("arc", this::arc, 2));

        commandos.add(new Commando("arcCos", this::arcCos, 1, "arcCosine"));
        commandos.add(new Commando("arCosh", this::arCosh, 1));
        commandos.add(new Commando("arcSin", this::arcSin, 1, "arcSine"));
        commandos.add(new Commando("arSinh", this::arSinh, 1));
        commandos.add(new Commando("arcTan", this::arcTan, 1, "arcTangent"));
        commandos.add(new Commando("arTanh", this::arTanh, 1));
        commandos.add(new Commando("aTan2", this::aTan2, 2));

        commandos.add(new Commando("ascii", this::ascii, 1));

        commandos.add(new Commando("background", this::background, "bg"));

        commandos.add(new Commando("butFirst", this::butFirst, 1));
        commandos.add(new Commando("butLast", this::butLast, 1));

        commandos.add(new Commando("buttonP", this::buttonP, "button?"));
        //Zelfde als buttonP maar dan over je rechter muis, heeft geen iplementatie van acslogo
        commandos.add(new Commando("buttonS", this::buttonS));

        commandos.add(new Commando("canvasSize", this::canvasSize));

        commandos.add(new Commando("catch", this::catchM, 2));

        commandos.add(new Commando("char", this::charM, 1));

        commandos.add(new Commando("clean", this::clean));
        commandos.add(new Commando("clearScreen", this::clearScreen, "cs"));

        commandos.add(new Commando("colorAtPoint", this::colorAtPoint, 1, "colourAtPoint"));

        commandos.add(new Commando("cos", this::cos, 1, "cosine"));
        commandos.add(new Commando("cosh", this::cosh, 1));

        commandos.add(new Commando("count", this::count, 1));

        commandos.add(new Commando("currentPath", this::currentPath));

        commandos.add(new Commando("date", this::date));

        commandos.add(new Commando("define", this::define, 2));
        commandos.add(new Commando("defineP", this::defineP, 1, "define?"));

        commandos.add(new Commando("difference", this::difference, 1));

        commandos.add(new Commando("dot", this::dot, 1));

        commandos.add(new Commando("emptyP", this::emptyP, 1, "empty?"));
        commandos.add(new Commando("equalP", this::equalP, 2, "equal?"));

        commandos.add(new Commando("exp", this::exp, 1));

        commandos.add(new Commando("fillCurrentPath", this::fillCurrentPath));
        commandos.add(new Commando("fillPath", this::fillPath, 1));

        commandos.add(new Commando("first", this::first, 1));
        commandos.add(new Commando("firstPut", this::firstPut, 2));

        commandos.add(new Commando("fontFace", this::fontFace, "font"));
        commandos.add(new Commando("fontFaces", this::fontFaces, "fonts"));
        commandos.add(new Commando("fontFamilies", this::fontFamilies));
        commandos.add(new Commando("fontFamily", this::fontFamily));
        commandos.add(new Commando("fontTraits", this::fontTraits));

        commandos.add(new Commando("getMouseChange", this::getMouseChange));
        commandos.add(new Commando("getMouseClick", this::getMouseClick));
        commandos.add(new Commando("getLeftMouseClick", this::getLeftMouseClick));
        commandos.add(new Commando("getRightMouseClick", this::getRightMouseClick));
        commandos.add(new Commando("getMouseMoved", this::getMouseMoved));

        commandos.add(new Commando("getProp", this::getProp, 2, "gProp"));

        commandos.add(new Commando("graphicsType", this::graphicsType, 1, "grType"));

        commandos.add(new Commando("heading", this::heading));

        commandos.add(new Commando("hideTurtle", this::hideTurtle, "ht"));

        commandos.add(new Commando("home", this::home));

        commandos.add(new Commando("if", this::ifM, 3));

        commandos.add(new Commando("image", this::image, 1));

        commandos.add(new Commando("integer", this::integer, 1));

        commandos.add(new Commando("item", this::item, 2));
        commandos.add(new Commando("last", this::last, 1));
        commandos.add(new Commando("lastPut", this::lastPut, 2));
        commandos.add(new Commando("list", this::list, 2));
        commandos.add(new Commando("listP", this::listP, 1, "list?"));

        commandos.add(new Commando("local", this::local, 1));

        commandos.add(new Commando("log", this::log, 1));
        commandos.add(new Commando("log10", this::log10, 1));
        commandos.add(new Commando("lowerCase", this::lowerCase, 1));

        commandos.add(new Commando("make", this::make, 2));

        commandos.add(new Commando("memberP", this::memberP, 2, "member?"));

        commandos.add(new Commando("mouse", this::mouse));

        commandos.add(new Commando("nameP", this::nameP, 1, "name?"));
        commandos.add(new Commando("not", this::not, 1));
        commandos.add(new Commando("numberP", this::numberP, 1, "number?"));
        commandos.add(new Commando("or", this::or, 2));
        commandos.add(new Commando("output", this::output, 1, "op"));
        commandos.add(new Commando("pathBounds", this::pathBounds, 1));
        commandos.add(new Commando("pathLength", this::pathLength, 1));
        commandos.add(new Commando("pen", this::pen));
        commandos.add(new Commando("penColor", this::penColor, "penColour", "pc"));
        commandos.add(new Commando("penDown", this::penDown, "pd"));
        commandos.add(new Commando("penUp", this::penUp, "pu"));
        commandos.add(new Commando("penWidth", this::penWidth));
        commandos.add(new Commando("pi", this::pi));
        commandos.add(new Commando("position", this::position, "pos"));
        commandos.add(new Commando("power", this::power, 2));
        commandos.add(new Commando("print", this::print, 1));
        commandos.add(new Commando("product", this::product, 1));

        commandos.add(new Commando("propList", this::propList, 1, "pList"));
        commandos.add(new Commando("putProp", this::putProp, 3, "pProp"));

        commandos.add(new Commando("quotient", this::quotient, 1));
        commandos.add(new Commando("random", this::random, 1));

        commandos.add(new Commando("readChar", this::readChar));
        commandos.add(new Commando("readChars", this::readChars, 1));
        commandos.add(new Commando("readList", this::readList));
        commandos.add(new Commando("readWord", this::readWord));

        commandos.add(new Commando("remainder", this::remainder, 2));
        commandos.add(new Commando("remProp", this::remProp, 2));
        commandos.add(new Commando("reverseList", this::reverseList, 1, "reverse", "reversePath"));
        commandos.add(new Commando("rgb", this::rgb, 1));

        commandos.add(new Commando("round", this::round, 1));
        commandos.add(new Commando("run", this::run, 1));

        commandos.add(new Commando("sentence", this::sentence, 2));

        commandos.add(new Commando("setBackground", this::setBackground, 1, "setBG"));

        commandos.add(new Commando("setCanvasSize", this::setCanvasSize, 1));

        commandos.add(new Commando("setFontFace", this::setFontFace, 1));
        commandos.add(new Commando("setFontFamily", this::setFontFamily, 1));
        commandos.add(new Commando("setFontTraits", this::setFontTraits, 1));

        commandos.add(new Commando("setFullScreen", this::setFullScreen, 1));

        commandos.add(new Commando("setHeading", this::setHeading, 1));

        commandos.add(new Commando("setLineCap", this::setLineCap, 1));
        commandos.add(new Commando("setLineDash", this::setLineDash, 1));

        commandos.add(new Commando("setPen", this::setPen, 1));
        commandos.add(new Commando("setPenColor", this::setPenColor, 1, "setPenColour", "setPC"));
        commandos.add(new Commando("setPenWidth", this::setPenWidth, 1));
        commandos.add(new Commando("setPosition", this::setPosition, 1, "setPos"));
        commandos.add(new Commando("setRGB", this::setRGB, 2));
        commandos.add(new Commando("setTypeSize", this::setTypeSize, 1));
        commandos.add(new Commando("setX", this::setX, 1));
        commandos.add(new Commando("setY", this::setY, 1));
        commandos.add(new Commando("show", this::show, 1));
        commandos.add(new Commando("shownP", this::shownP, "shown?"));
        commandos.add(new Commando("showTurtle", this::showTurtle, "st"));
        commandos.add(new Commando("sin", this::sin, 1, "sine"));
        commandos.add(new Commando("sinh", this::sinh, 1));
        commandos.add(new Commando("sqrt", this::sqrt, 1));
        commandos.add(new Commando("stop", this::stop));
        commandos.add(new Commando("strokeCurrentPath", this::strokeCurrentPath));
        commandos.add(new Commando("strokePath", this::strokePath, 1));
        commandos.add(new Commando("sum", this::sum, 1));

        commandos.add(new Commando("tan", this::tan, 1, "tangent"));
        commandos.add(new Commando("tanh", this::tanh, 1));
        commandos.add(new Commando("text", this::text, 1));
        commandos.add(new Commando("textBox", this::textBox, 1));
        commandos.add(new Commando("throw", this::throwM, 1));
        commandos.add(new Commando("thing", this::thing, 1));
        commandos.add(new Commando("time", this::time));
        commandos.add(new Commando("towards", this::towards, 1));
        commandos.add(new Commando("type", this::type, 1));

        commandos.add(new Commando("upperCase", this::upperCase, 1));

        commandos.add(new Commando("wait", this::wait, 1));
        commandos.add(new Commando("word", this::word, 2));
        commandos.add(new Commando("wordP", this::wordP, 1, "word?"));

        commandos.add(new Commando("xPos", this::xPos));
        commandos.add(new Commando("yPos", this::yPos));

        commandos.add(new Commando("upUpDownDownLeftRightLeftRightBA", this::upUpDownDownLeftRightLeftRightBA));
        commandos.add(new Commando("d0an", this::d0an, "daan", "ddvl"));
        commandos.add(new Commando("under_koen", this::underKoen, "underKoen", "koen"));
        commandos.add(new Commando("disgustedD0an", this::disgustedD0an));

        return commandos.toArray(new Commando[0]);
    }

    private Value abs(Value... arguments) throws ExecutorException {
        double d = arguments[0].getAsNumber();
        return new Value(Math.abs(d));
    }

    private Value and(Value... arguments) throws ExecutorException {
        Boolean l = arguments[0].getAsBoolean();
        Boolean r = arguments[1].getAsBoolean();
        return new Value(l && r);
    }

    private Value arc(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        double r = (Double) arguments[1].getValue();
        logo.arc(r, d);
        return null;
    }

    private Value arcCos(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.acos(d)));
    }

    private Value arCosh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.log(d + Math.sqrt(d * d - 1.0))));
    }

    private Value arcSin(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.asin(d)));
    }

    private Value arSinh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.log(d + Math.sqrt(d * d + 1.0))));
    }

    private Value arcTan(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.toDegrees(Math.atan(d)));
    }

    private Value arTanh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(0.5 * Math.log((d + 1.0) / (d - 1.0)));
    }

    private Value aTan2(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        double d2 = (Double) arguments[1].getValue();
        return new Value(Math.toDegrees(Math.atan2(d2, d)));
    }

    private Value ascii(Value... arguments) {
        char c = ((String) arguments[0].getValue()).charAt(0);
        return new Value(Character.hashCode(c));
    }

    private Value back(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.forward(-amount);
        return null;
    }

    private Value background() {
        return new Value(logo.colorHandler.getBackground());
    }

    private Value butFirst(Value... arguments) throws ExecutorException {
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

    private Value butLast(Value... arguments) throws ExecutorException {
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

    private Value buttonP() {
        return new Value(logo.mouseHandler.isLeftMouseDown());
    }

    private Value buttonS() {
        return new Value(logo.mouseHandler.isRightMouseDown());
    }

    private Value canvasSize() {
        Size s = logo.getCanvas().getSize();
        return new Value(new ListObject(s.getWidth(), s.getHeight()));
    }

    private Value catchM(Value... arguments) throws ParsingException, ExecutorException {
        String error = (String) arguments[0].getValue();
        ListObject l = (ListObject) arguments[1].getValue();

        try {
            logo.runRaw(l.getInner());
        } catch (Catch ex) {
            if (!ex.getError().equals(error)) throw ex;
        }

        return null;
    }

    private Value charM(Value... arguments) {
        int i = ((Double) arguments[0].getValue()).intValue();
        return new Value((char) i + "");
    }

    private Value clean() {
        logo.getCanvas().fillScreen(logo.colorHandler.getBackgroundColor());
        logo.getTurtle().getPath().clear();
        return null;
    }

    private Value clearScreen() {
        clean();
        home();
        logo.getTurtle().setPenDown(true);
        return null;
    }

    private Value colorAtPoint(Value... arguments) throws ExecutorException {
        Position pos = ValueUtil.valueToPosition(arguments[0], logo.getStart());

        Waitable<javafx.scene.paint.Color> waitable = new Waitable<>();
        Platform.runLater(() -> {
            FXCanvas fxCanvas = (FXCanvas) logo.getCanvas();
            WritableImage img = fxCanvas.getNode().snapshot(new SnapshotParameters(), null);
            waitable.setDone(img.getPixelReader().getColor((int) pos.getX(), (int) pos.getY()));
        });

        javafx.scene.paint.Color color = WaitableUtil.waitFor(waitable);

        return new Value(new ListObject((int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255)));
    }

    private Value cos(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.cos(Math.toRadians(d)));
    }

    private Value cosh(Value... arguments) {
        double d = (Double) arguments[0].getValue();
        return new Value(Math.cosh(Math.toRadians(d)));
    }

    private Value count(Value... arguments) {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> l = ((ListObject) v.getValue()).getList();
            return new Value(l.size());
        } else {
            String s = v.toString();
            return new Value(s.toCharArray().length);
        }
    }

    private Value currentPath() {
        List<Value> l = new ArrayList<>();
        Position[] points = logo.getTurtle().getPath().getPoints();
        for (Position pos : points) {
            l.add(ValueUtil.positionToValue(pos, logo.getStart()));
        }
        return new Value(new ListObject(l));
    }

    private Value date() {
        String s = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return new Value(s);
    }

    private Value define(Value... arguments) throws ExecutorException, ParsingException {
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

    private Value defineP(Value... arguments) {
        String name = (String) arguments[0].getValue();
        return new Value(logo.procedureHandler.getProcedures().keySet().contains(name));
    }

    private Value difference(Value... arguments) throws ExecutorException {
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

    private Value dot(Value... arguments) throws ExecutorException {
        Position pos = ValueUtil.valueToPosition(arguments[0], logo.getStart());
        logo.getCanvas().drawDot(pos);
        return null;
    }

    private Value emptyP(Value... arguments) {
        Value v = arguments[0];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> l = ((ListObject) v.getValue()).getList();
            return new Value(l.isEmpty());
        } else {
            String s = v.toString();
            return new Value(s.isEmpty());
        }
    }

    private Value equalP(Value... arguments) {
        String s1 = arguments[0].toString();
        String s2 = arguments[1].toString();
        return new Value(s1.equals(s2));
    }

    private Value exp(Value... arguments) {
        Double d = (double) arguments[0].getValue();
        return new Value(Math.exp(d));
    }

    private Value fillCurrentPath() throws ExecutorException {
        logo.fillPath();
        logo.getTurtle().getPath().clear();
        return null;
    }

    private Value fillPath(Value... arguments) throws ExecutorException {
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
        logo.getTurtle().getPath().clear();
        return null;
    }

    private Value first(Value... arguments) throws ExecutorException {
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

    private Value firstPut(Value... arguments) {
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

    private Value fontFace() {
        Font font = logo.getTurtle().getFont();
        return ValueUtil.fontToValue(font);
    }

    private Value fontFaces() {
        List<Value> fonts = javafx.scene.text.Font.getFontNames().stream()
                .map(ValueUtil::fontToValue)
                .collect(Collectors.toList());
        return new Value(new ListObject(fonts));
    }

    private Value fontFamilies() {
        List<Value> fonts = javafx.scene.text.Font.getFamilies().stream()
                .map(ValueUtil::fontToValue)
                .collect(Collectors.toList());
        return new Value(new ListObject(fonts));
    }

    private Value fontFamily() {
        Font font = logo.getTurtle().getFont();
        return ValueUtil.fontToValue(new javafx.scene.text.Font(font.getName(), font.getSize()).getFamily());
    }

    private Value fontTraits() {
        Font font = logo.getTurtle().getFont();
        return ValueUtil.fontToValue(new javafx.scene.text.Font(font.getName(), font.getSize()).getStyle());
    }

    private Value forward(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.forward(amount);
        return null;
    }

    private Value getMouseChange() throws ExecutorException {
        Waitable<Position> left = logo.mouseHandler.getLeftMouseClick();
        Waitable<Position> right = logo.mouseHandler.getRightMouseClick();
        Waitable<Position> moved = logo.mouseHandler.getMouseMove();

        Waitable updated = WaitableUtil.waitFor(left, right, moved);
        Position pos = (Position) updated.reset();

        return new Value(new ListObject(ValueUtil.positionToValue(pos, logo.getStart()), new Value(updated == left), new Value(updated == right)));
    }

    private Value getMouseClick() throws ExecutorException {
        Waitable<Position> left = logo.mouseHandler.getLeftMouseClick();
        Waitable<Position> right = logo.mouseHandler.getRightMouseClick();

        Waitable updated = WaitableUtil.waitFor(left, right);
        Position pos = (Position) updated.reset();

        return new Value(new ListObject(ValueUtil.positionToValue(pos, logo.getStart()), new Value(updated == left), new Value(updated == right)));
    }

    private Value getLeftMouseClick() throws ExecutorException {
        Waitable<Position> left = logo.mouseHandler.getLeftMouseClick();
        Position pos = WaitableUtil.waitFor(left);
        return ValueUtil.positionToValue(pos, logo.getStart());
    }

    private Value getRightMouseClick() throws ExecutorException {
        Waitable<Position> right = logo.mouseHandler.getRightMouseClick();
        Position pos = WaitableUtil.waitFor(right);
        return ValueUtil.positionToValue(pos, logo.getStart());
    }

    private Value getMouseMoved() throws ExecutorException {
        Waitable<Position> moved = logo.mouseHandler.getMouseMove();
        Position pos = WaitableUtil.waitFor(moved);
        return ValueUtil.positionToValue(pos, logo.getStart());
    }

    private Value getProp(Value... arguments) throws ExecutorException {
        String name = arguments[0].getAsString();
        String property = arguments[1].getAsString();
        Value r = logo.propertyHandler.getProperty(name, property);
        if (r == null) throw new ExecutorException("This property doesn't exist.");
        return r;
    }

    private Value graphicsType(Value... arguments) throws ExecutorException {
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

    private Value heading() {
        return new Value((360.0 - logo.getTurtle().getRotation()) % 360);
    }

    private Value hideTurtle() {
        logo.getTurtleGraphics().hide();
        return null;
    }

    private Value home(Value... arguments) {
        Size s = logo.getCanvas().getSize();
        Position newp = new Position(s.getWidth() / 2.0, s.getHeight() / 2.0);
        logo.setStart(newp);
        logo.getTurtle().setPosition(newp);
        logo.getTurtle().setRotation(0);
        return null;
    }

    private Value ifM(Value... arguments) throws ParsingException, ExecutorException {
        boolean b = arguments[0].getAsBoolean();
        ListObject trueL = arguments[1].getAsList();
        ListObject falseL = arguments[2].getAsList();

        return logo.runRaw((b) ? trueL.getInner() : falseL.getInner());
    }

    private Value image(Value... arguments) throws ExecutorException {
        String s = arguments[0].getAsString();
        Image image = new Image(new File(s));
        logo.drawImage(image);
        return null;
    }

    private Value integer(Value... arguments) throws ExecutorException {
        return new Value((double) (int) arguments[0].getAsNumber());
    }

    private Value item(Value... arguments) throws ExecutorException {
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

    private Value last(Value... arguments) throws ExecutorException {
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

    private Value lastPut(Value... arguments) {
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

    private Value left(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.left(amount);
        return null;
    }

    private Value list(Value... arguments) {
        Value first = arguments[0];
        Value second = arguments[1];
        return new Value(new ListObject(first, second));
    }

    private Value listP(Value... arguments) {
        return new Value(arguments[0].getType() == Value.ValueType.LIST);
    }

    private Value local(Value... arguments) throws ExecutorException {
        LocalVariableHandler variableHandler = logo.getCurrent();
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

    private Value log(Value... arguments) throws ExecutorException {
        return new Value(Math.log(arguments[0].getAsNumber()));
    }

    private Value log10(Value... arguments) throws ExecutorException {
        return new Value(Math.log10(arguments[0].getAsNumber()));
    }

    private Value lowerCase(Value... arguments) {
        return ValueUtil.stringToValue(arguments[0].toString().toLowerCase());
    }

    private Value make(Value... arguments) throws ExecutorException {
        VariableHandler variableHandler = logo.getCurrent();
        if (variableHandler == null) variableHandler = logo.getExecutor().getVariableHandler();
        String name = arguments[0].getAsString();
        Value value = arguments[1];
        variableHandler.setVariable(name, value);
        return null;
    }

    private Value memberP(Value... arguments) throws ExecutorException {
        Value v = arguments[1];
        if (v.getType() == Value.ValueType.LIST) {
            List<Value> list = v.getAsList().getList();
            return new Value(list.contains(arguments[0]));
        } else {
            String s = v.getAsString();
            return new Value(s.contains(arguments[0].getAsString()));
        }
    }

    private Value mouse() {
        return ValueUtil.positionToValue(logo.mouseHandler.getMouse(), logo.getStart());
    }

    private Value nameP(Value... arguments) throws ExecutorException {
        VariableHandler variableHandler = logo.getCurrent();
        if (variableHandler == null) variableHandler = logo.getExecutor().getVariableHandler();

        String name = arguments[0].getAsString();
        return new Value(variableHandler.contains(name));
    }

    private Value not(Value... arguments) throws ExecutorException {
        return new Value(!arguments[0].getAsBoolean());
    }

    private Value numberP(Value... arguments) {
        return new Value(ValueUtil.stringToValue(arguments[0].toString()).getType() == Value.ValueType.NUMBER);
    }

    private Value or(Value... arguments) throws ExecutorException {
        return new Value(arguments[0].getAsBoolean() || arguments[1].getAsBoolean());
    }

    private Value output(Value... arguments) {
        throw new Output(arguments[0]);
    }

    private Value pathBounds(Value... arguments) throws ExecutorException {
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

    private Value pathLength(Value... arguments) throws ExecutorException {
        return new Value(arguments[0].getAsList().getList().size());
    }

    private Value pen() {
        Turtle turtle = logo.getTurtle();
        int color = logo.colorHandler.getPen();
        return new Value(new ListObject(turtle.isPenDown(), color));
    }

    private Value penColor() {
        int color = logo.colorHandler.getPen();
        return new Value(color);
    }

    private Value penUp() {
        logo.getTurtle().setPenDown(false);
        logo.getTurtle().getPath().clear();
        ACSLogo.server.penUp();
        return null;
    }

    private Value penDown() {
        logo.getTurtle().setPenDown(true);
        ACSLogo.server.penDown();
        return null;
    }

    private Value penWidth() {
        return new Value(logo.getTurtle().getPenWidth());
    }

    private Value pi() {
        return new Value(Math.PI);
    }

    private Value position() {
        return ValueUtil.positionToValue(logo.getTurtle().getPosition(), logo.getStart());
    }

    private Value power(Value... arguments) throws ExecutorException {
        return new Value(Math.pow(arguments[0].getAsNumber(), arguments[1].getAsNumber()));
    }

    private Value print(Value... arguments) throws ExecutorException {
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

    private Value product(Value... arguments) throws ExecutorException {
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

    private Value propList(Value... arguments) throws ExecutorException {
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

    private Value putProp(Value... arguments) throws ExecutorException {
        String name = arguments[0].getAsString();
        String property = arguments[1].getAsString();
        Value value = arguments[2];
        logo.propertyHandler.setProperty(name, property, value);
        return null;
    }

    private Value quotient(Value... arguments) throws ExecutorException {
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

    private Value random(Value... arguments) throws ExecutorException {
        int max = (int) arguments[0].getAsNumber();
        double r = random.nextInt(Math.max(max, 1));
        return new Value(r);
    }

    private Value readChar() throws ExecutorException {
        ConsoleHandler console = logo.getExecutor().getConsoleHandler();
        console.waitFor((text, key) -> text.length() >= 1);
        console.enableInput();
        String s = String.valueOf(WaitableUtil.waitFor(console.getInput()).charAt(0));
        console.disableInput();
        return ValueUtil.stringToValue(s);
    }

    private Value readChars(Value... arguments) throws ExecutorException {
        int count = (int) arguments[0].getAsNumber();
        if (count < 1) throw new ExecutorException("Input for readChars should be bigger or equals to 1.");
        ConsoleHandler console = logo.getExecutor().getConsoleHandler();
        console.waitFor((text, key) -> text.length() >= count);
        console.enableInput();
        String s = WaitableUtil.waitFor(console.getInput()).substring(0, count);
        console.disableInput();
        return ValueUtil.stringToValue(s);
    }

    private Value readList() throws ExecutorException {
        ConsoleHandler console = logo.getExecutor().getConsoleHandler();
        console.waitFor((text, key) -> key == KeyCode.ENTER);
        console.enableInput();
        String s = String.format("[%s]", WaitableUtil.waitFor(console.getInput()));
        console.disableInput();
        return ValueUtil.stringToListValue(s);
    }

    private Value readWord() throws ExecutorException {
        ConsoleHandler console = logo.getExecutor().getConsoleHandler();
        console.waitFor((text, key) -> key == KeyCode.ENTER);
        console.enableInput();
        String s = WaitableUtil.waitFor(console.getInput());
        console.disableInput();
        return ValueUtil.stringToValue(s);
    }

    private Value remainder(Value... arguments) throws ExecutorException {
        double first = arguments[0].getAsNumber();
        double second = arguments[1].getAsNumber();
        return new Value(first % second);
    }

    private Value remProp(Value... arguments) throws ExecutorException {
        String name = arguments[0].getAsString();
        String property = arguments[1].getAsString();
        logo.propertyHandler.removeProperty(name, property);
        return null;
    }

    private Value repeat(Value... arguments) throws ParsingException, ExecutorException {
        int t = (int) arguments[0].getAsNumber();
        ListObject l = (ListObject) arguments[1].getValue();
        for (int i = 0; i < t; i++) {
            logo.runRaw(l.getInner());
        }
        return null;
    }

    private Value reverseList(Value... arguments) throws ExecutorException {
        List<Value> list = arguments[0].getAsList().getList();
        Collections.reverse(list);
        return new Value(new ListObject(list));
    }

    private Value rgb(Value... arguments) throws ExecutorException {
        int c = (int) arguments[0].getAsNumber();
        Color color = logo.colorHandler.getColor(c);
        return new Value(new ListObject(color.getRed(), color.getGreen(), color.getBlue()));
    }

    private Value right(Value... arguments) {
        double amount = (Double) arguments[0].getValue();
        logo.right(amount);
        return null;
    }

    private Value round(Value... arguments) throws ExecutorException {
        double d = arguments[0].getAsNumber();
        return new Value(Math.round(d));
    }

    private Value run(Value... arguments) throws ExecutorException, ParsingException {
        ListObject list = arguments[0].getAsList();
        return logo.runRaw(list.getInner());
    }

    private Value sentence(Value... arguments) throws ExecutorException {
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

    private Value setBackground(Value... arguments) throws ExecutorException {
        int color = (int) arguments[0].getAsNumber();
        logo.colorHandler.setBackground(color);
        return null;
    }

    private Value setCanvasSize(Value... arguments) throws ExecutorException {
        List<Value> list = arguments[0].getAsList().getList();
        int width = (int) list.get(0).getAsNumber();
        int height = (int) list.get(1).getAsNumber();
        logo.getCanvas().setSize(new Size(width, height));
        clearScreen();
        return null;
    }

    private Value setFontFace(Value... arguments) throws ExecutorException {
        String fontFace = arguments[0].getAsList().getInner();
        checkFont(fontFace);

        Font old = logo.getTurtle().getFont();
        logo.getTurtle().setFont(new Font(fontFace, old.getSize(), old.getColor()));
        return null;
    }

    private Value setFontFamily(Value... arguments) throws ExecutorException {
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

    private Value setFontTraits(Value... arguments) throws ExecutorException {
        Font old = logo.getTurtle().getFont();
        String s = new javafx.scene.text.Font(old.getName(), old.getSize()).getFamily();

        String style = arguments[0].getAsList().getInner();
        String name = String.format(String.format("%s %s", s, style).trim());
        checkFont(name);

        logo.getTurtle().setFont(new Font(name, old.getSize(), old.getColor()));
        return null;
    }

    private Value setFullScreen(Value... arguments) throws ExecutorException {
        boolean b = arguments[0].getAsBoolean();
        Platform.runLater(() -> ((Stage) ((FXCanvas) logo.getCanvas()).getCanvas().getScene().getWindow()).setFullScreen(b));
        return null;
    }

    private Value setHeading(Value... arguments) throws ExecutorException {
        double d = arguments[0].getAsNumber();
        logo.getTurtle().setRotation(360.0 - d);
        return null;
    }

    private Value setLineCap(Value... arguments) throws ExecutorException {
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

    private Value setLineDash(Value... arguments) throws ExecutorException {
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

    private Value setPen(Value... arguments) throws ExecutorException {
        List<Value> list = arguments[0].getAsList().getList();
        boolean pen = list.get(0).getAsBoolean();
        int color = (int) list.get(1).getAsNumber();
        logo.colorHandler.setPenColor(color);
        logo.getTurtle().setPenDown(pen);
        return null;
    }

    private Value setPenColor(Value... arguments) throws ExecutorException {
        int color = (int) arguments[0].getAsNumber();
        logo.colorHandler.setPenColor(color);
        return null;
    }

    private Value setPenWidth(Value... arguments) throws ExecutorException {
        double width = arguments[0].getAsNumber();
        logo.getTurtle().setPenWidth(width);
        return null;
    }

    private Value setPosition(Value... arguments) throws ExecutorException {
        Position position = ValueUtil.valueToPosition(arguments[0], logo.getStart());
        logo.drawToPosition(position);
        return null;
    }

    private Value setRGB(Value... arguments) throws ExecutorException {
        int i = (int) arguments[0].getAsNumber();
        List<Value> list = arguments[1].getAsList().getList();
        int r = (int) list.get(0).getAsNumber();
        int g = (int) list.get(1).getAsNumber();
        int b = (int) list.get(2).getAsNumber();
        double a = 1.0;
        if (list.size() >= 4) a = list.get(3).getAsNumber();
        Color color = new Color(r, g, b, a);
        logo.colorHandler.setColor(i, color);
        return null;
    }

    private Value setTypeSize(Value... arguments) throws ExecutorException {
        double size = arguments[0].getAsNumber();
        Font old = logo.getTurtle().getFont();
        logo.getTurtle().setFont(new Font(old.getName(), size, old.getColor()));
        return null;
    }

    private Value setX(Value... arguments) throws ExecutorException {
        double x = arguments[0].getAsNumber();
        Position position = new Position(x, logo.getTurtle().getPosition().getY());
        position.addX(logo.getStart().getX());
        logo.drawToPosition(position);
        return null;
    }

    private Value setY(Value... arguments) throws ExecutorException {
        double y = arguments[0].getAsNumber();
        Position position = new Position(logo.getTurtle().getPosition().getX(), -y);
        position.addY(logo.getStart().getY());
        logo.drawToPosition(position);
        return null;
    }

    private Value show(Value... arguments) {
        Value v = arguments[0];
        logo.getExecutor().getConsoleHandler().output(v);
        return null;
    }

    private Value shownP() {
        return new Value(logo.getTurtleGraphics().isVisible());
    }

    private Value showTurtle() {
        logo.getTurtleGraphics().show();
        return null;
    }

    private Value sin(Value... arguments) throws ExecutorException {
        double d = arguments[0].getAsNumber();
        return new Value(Math.sin(Math.toRadians(d)));
    }

    private Value sinh(Value... arguments) throws ExecutorException {
        double d = arguments[0].getAsNumber();
        return new Value(Math.sinh(Math.toRadians(d)));
    }

    private Value sqrt(Value... arguments) throws ExecutorException {
        double d = arguments[0].getAsNumber();
        return new Value(Math.sqrt(d));
    }

    private Value stop() {
        throw new Output(null);
    }

    private Value strokeCurrentPath() throws ExecutorException {
        strokePath(currentPath());
        return null;
    }

    private Value strokePath(Value... arguments) throws ExecutorException {
        List<Value> path = arguments[0].getAsList().getList();
        Position old = logo.getTurtle().getPosition();
        Position start = logo.getStart();

        Position from = null;
        for (Value value : path) {
            Position position = ValueUtil.valueToPosition(value, start);

            if (from == null) {
                from = position;
                logo.getTurtle().setPosition(from);
            } else {
                logo.drawToPosition(position);
                from = position;
            }
        }

        logo.getTurtle().setPosition(old);
        return null;
    }

    private Value sum(Value... arguments) throws ExecutorException {
        List<Value> list = arguments[0].getAsList().getList();
        Double r = null;
        for (Value value : list) {
            if (r == null) {
                r = value.getAsNumber();
            } else {
                r = r + value.getAsNumber();
            }
        }
        return new Value(r);
    }

    private Value tan(Value... arguments) throws ExecutorException {
        double d = arguments[0].getAsNumber();
        return new Value(Math.tan(Math.toRadians(d)));
    }

    private Value tanh(Value... arguments) throws ExecutorException {
        double d = arguments[0].getAsNumber();
        return new Value(Math.tanh(Math.toRadians(d)));
    }

    private Value text(Value... arguments) throws ExecutorException {
        String s = arguments[0].getAsString();
        Procedure procedure = logo.procedureHandler.getProcedure(s);
        ListObject pare = new ListObject(procedure.getParameters().toArray());
        String code = logo.getParser().makeReady(procedure.getCode());
        code = code.replace('\n', ' ');
        Value codeV = ValueUtil.stringToListValue(String.format("[%s]", code));
        return new Value(new ListObject(new Value(pare), codeV));
    }

    private Value textBox(Value... arguments) throws ExecutorException {
        String s = arguments[0].getAsString();
        Text text = new Text(s);
        Font font = logo.getTurtle().getFont();
        text.setFont(new javafx.scene.text.Font(font.getName(), font.getSize()));
        double w = text.getLayoutBounds().getWidth();
        double h = text.getLayoutBounds().getHeight();
        Position pos = logo.getTurtle().getPosition();
        pos.addPosition(logo.getStart().inverted());
        if (pos.getY() != 0) pos.setY(-pos.getY());
        return new Value(new ListObject(pos.getX(), pos.getY(), w, h));
    }

    private Value thing(Value... arguments) throws ExecutorException {
        String s = arguments[0].getAsString();
        return logo.getExecutor().getVariableHandler().getVariable(s);
    }

    private Value throwM(Value... arguments) throws ExecutorException {
        String s = arguments[0].getAsString();
        throw new Catch(s);
    }

    private Value time() {
        return new Value(timeFormat.format(new Date()));
    }

    private Value towards(Value... arguments) throws ExecutorException {
        Position position = ValueUtil.valueToPosition(arguments[0], new Position(0, 0));
        System.out.println(position);
        return new Value(MathUtil.getRotationTowardsRelative(position));
    }

    private Value type(Value... arguments) throws ExecutorException {
        Value v = arguments[0];
        ConsoleHandler console = logo.getExecutor().getConsoleHandler();
        if (v.getType() == Value.ValueType.LIST) {
            ListObject list = arguments[0].getAsList();
            console.type(list.getInner());
        } else {
            console.type(arguments[0]);
        }
        return null;
    }

    private Value upperCase(Value... arguments) {
        return ValueUtil.stringToValue(arguments[0].toString().toUpperCase());
    }

    private Value wait(Value... arguments) throws ExecutorException {
        int i = (int) arguments[0].getAsNumber();
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new ExecutorException("Stopped while waiting.");
        }
        return null;
    }

    private Value word(Value... arguments) throws ExecutorException {
        String s1 = arguments[0].getAsString();
        String s2 = arguments[1].getAsString();
        return new Value(s1 + s2);
    }

    private Value wordP(Value... arguments) {
        return new Value(arguments[0].getType() != Value.ValueType.LIST);
    }

    private Value xPos() {
        return new Value(logo.getTurtle().getPosition().getX() - logo.getStart().getX());
    }

    private Value yPos() {
        double y = logo.getTurtle().getPosition().getY() - logo.getStart().getY();
        if (y != 0) y = -y;
        return new Value(y);
    }

    private void checkFont(String font) throws ExecutorException {
        Font old = logo.getTurtle().getFont();
        String s = new javafx.scene.text.Font(font, 12).getName();
        if (!s.equalsIgnoreCase(font.trim()))
            throw new ExecutorException(String.format("Font %s doesn't exist.", font));
    }

    //Super secret easter eggs
    private Value upUpDownDownLeftRightLeftRightBA() {
        //Tip run dit commando nooit
        Runtime runtime = Runtime.getRuntime();
        String[] args = { "osascript", "-e", "tell application \"Spotify\" to activate\n" +
                "delay 0.5\n" +
                "if running of application \"Spotify\" is true then\n" +
                "tell application \"Spotify\"\n" +
                "play track \"spotify:track:7GhIk7Il098yCjg4BQjzvb\"\n" +
                "set sound volume to 100\n" +
                "end tell\n" +
                "repeat 10000000 times\n" +
                "if application \"Spotify\" is running then\n" +
                "tell application \"Spotify\" to activate\n" +
                "delay 0.5\n" +
                "try\n" +
                "tell application \"Spotify\"\n" +
                "play\n" +
                "set sound volume to 100\n" +
                "end tell\n" +
                "end try\n" +
                "set volume output volume 100 --100%\n" +
                "delay 0.1\n" +
                "else\n" +
                "tell application \"Spotify\" to activate\n" +
                "delay 0.5\n" +
                "tell application \"Spotify\"\n" +
                "play track \"spotify:track:7GhIk7Il098yCjg4BQjzvb\"\n" +
                "end tell\n" +
                "end if\n" +
                "end repeat\n" +
                "end if"};
        try {
            Process process = runtime.exec(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Value disgustedD0an() {
        logo.getTurtleGraphics().setImage(".eastereggs/d0an.png");
        return null;
    }

    private Value d0an() {
        try {
            Desktop.getDesktop().browse(new URI("https://daandvl.nl"));
        } catch (Exception ignored) {
        }
        return null;
    }

    private Value underKoen() {
        try {
            Desktop.getDesktop().browse(new URI("https://underkoen.nl"));
        } catch (Exception ignored) {
        }
        return null;
    }
}