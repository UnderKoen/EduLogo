package nl.edulogo.acslogo;

import nl.edulogo.acslogo.script.Parser;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.arguments.ArgumentType;
import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.acslogo.script.commandos.CommandoHandler;
import nl.edulogo.core.Canvas;
import nl.edulogo.core.Color;
import nl.edulogo.core.Position;
import nl.edulogo.core.Size;
import nl.edulogo.core.utils.FileUtil;
import nl.edulogo.display.fx.FXCanvas;
import nl.edulogo.display.fx.FXDisplay;
import nl.edulogo.editor.Editor;
import nl.edulogo.editor.fx.FXEditor;
import nl.edulogo.logo.Logo;
import nl.edulogo.logo.Turtle;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class ACSLogo extends Logo {
    private static ACSLogo acsLogo;

    private CommandoHandler handler;
    private Parser parser;
    private Editor editor;
    //LOGO
    private Turtle turtle;
    private Canvas canvas;

    public ACSLogo() {
        handler = new CommandoHandler();
        registerCommands();

        parser = new Parser(handler);

        initLogo(new Size(500, 500));
        setupDisplay();
    }

    public static void main(String[] args) {
        new ACSLogo();
    }

    public void setupDisplay() {
        Starter.start(() -> {
            FXDisplay<FXCanvas> canvas = new FXDisplay<>((FXCanvas) this.canvas);
            FXDisplay<FXEditor> editor = new FXDisplay<>(new Size(350, 500), new FXEditor());

            this.editor = editor.getView();

            canvas.show();
            editor.show();

            init();
        });
    }

    public void init() {
        Script script = new Script(FileUtil.getResourceContent("test.logo"));
        parser.parse(script);
    }

    public void registerCommands() {
        handler.registerCommandos(new Commando("Forward", arguments -> {
                    Number amount = (Number) arguments[0].getValue();
                    forward(amount.doubleValue());
                }, ArgumentType.NUMBER),
                new Commando("fd", arguments -> {
                    Number amount = (Number) arguments[0].getValue();
                    forward(amount.doubleValue());
                }, ArgumentType.NUMBER),
                new Commando("Right", arguments -> {
                    Number amount = (Number) arguments[0].getValue();
                    right(amount.doubleValue());
                }, ArgumentType.NUMBER),
                new Commando("Left", arguments -> {
                    Number amount = (Number) arguments[0].getValue();
                    left(amount.doubleValue());
                }, ArgumentType.NUMBER));
    }

    public void initLogo(Size size) {
        canvas = new FXCanvas(size);
        turtle = new Turtle(new Position(size.getWidth() / 2, size.getHeight() / 2), 0);
        canvas.fillScreen(Color.WHITE);
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public Turtle getTurtle() {
        return turtle;
    }
}