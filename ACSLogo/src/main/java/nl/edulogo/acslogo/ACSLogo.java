package nl.edulogo.acslogo;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import nl.edulogo.acslogo.display.Procedures;
import nl.edulogo.acslogo.display.TurtleGraphics;
import nl.edulogo.acslogo.display.fx.FXProcedures;
import nl.edulogo.acslogo.display.fx.FXTurtleGraphics;
import nl.edulogo.acslogo.handlers.ColorHandler;
import nl.edulogo.acslogo.handlers.CommandoHandler;
import nl.edulogo.acslogo.handlers.ConsoleHandler;
import nl.edulogo.acslogo.handlers.MouseHandler;
import nl.edulogo.acslogo.handlers.procedures.ProcedureHandler;
import nl.edulogo.acslogo.handlers.variable.PropertyHandler;
import nl.edulogo.acslogo.script.ExecutorException;
import nl.edulogo.acslogo.script.ParsingException;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.Executor;
import nl.edulogo.acslogo.script.parser.Parser;
import nl.edulogo.core.*;
import nl.edulogo.display.Display;
import nl.edulogo.display.fx.FXCanvas;
import nl.edulogo.display.fx.FXDisplay;
import nl.edulogo.editor.Editor;
import nl.edulogo.editor.fx.FXEditor;
import nl.edulogo.logo.AdvancedLogo;
import nl.edulogo.logo.Turtle;

import java.io.File;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class ACSLogo extends AdvancedLogo {
    private static final int TURTLE_SIZE = 48;
    private static final double TURTLE_ALPHA = 0.8;

    //TODO when closing editor window close canvas and everything else
    private static OS os = new OS();

    private CommandoHandler commandoHandler;
    private ConsoleHandler consoleHandler;
    ColorHandler colorHandler;
    MouseHandler mouseHandler;
    ProcedureHandler procedureHandler;
    PropertyHandler propertyHandler;
    private Parser parser;
    private Executor executor;
    private Editor editor;
    private TurtleGraphics<FXCanvas> turtleGraphics;
    private Position start;

    private Turtle turtle;
    private Canvas canvas;
    private Thread running;

    private Display<? extends Procedures> proceduresDisplay;

    public ACSLogo() {
        setupDisplay();
    }

    public static void main(String[] args) {
        new ACSLogo();
    }

    public void setupDisplay() {
        Starter.start(this::init);
    }

    public void init() {
        initLogo(new Size(500, 500), new Size(350, 500));
        initMenu();

        commandoHandler = new CommandoHandler();
        consoleHandler = new ConsoleHandler(editor.getConsole());
        colorHandler = new ColorHandler(turtle, Color.WHITE, Color.BLACK, Color.RED, Color.BLUE, Color.YELLOW, new Color(177, 121, 51),
                Color.CYAN, new Color(146, 146, 146), Color.MAGENTA, new Color(255, 144, 0), new Color(161, 0, 149),
                new Color(190, 0, 242), new Color(194, 171, 0), new Color(217, 192, 166),
                new Color(238, 214, 188), new Color(0, 238, 217), Color.GREEN);
        mouseHandler = new MouseHandler((FXCanvas) canvas);
        propertyHandler = new PropertyHandler();

        registerCommands();

        procedureHandler = new ProcedureHandler(this, commandoHandler);
        proceduresDisplay = new FXDisplay<>(new Size(700, 500), new FXProcedures(procedureHandler));
        proceduresDisplay.setTitle("Procedures");

        executor = new Executor(consoleHandler, commandoHandler);
        parser = new Parser(consoleHandler, executor);
    }

    public void registerCommands() {
        commandoHandler.registerCommandos(Commandos.getCommandos(this));
    }

    public void initLogo(Size size, Size editorSize) {
        canvas = new FXCanvas(size);
        turtleGraphics = new FXTurtleGraphics(TURTLE_SIZE);
        start = new Position(size.getWidth() / 2.0, size.getHeight() / 2.0);
        turtle = new ACSTurtle(start, 0, this);
        canvas.fillScreen(Color.WHITE);

        editor = new FXEditor();

        FXDisplay<FXCanvas> canvasD = new FXDisplay<>((FXCanvas) canvas);
        canvasD.setTitle("Canvas");

        FXDisplay<FXEditor> editorD = new FXDisplay<>(editorSize, (FXEditor) editor);
        editorD.setTitle("Editor");

        turtleGraphics.addToCanvas((FXCanvas) canvas);
        turtleGraphics.setImage(new Image(new File("C:\\Users\\koenv\\Desktop\\img.png")));
        turtleGraphics.setAlpha(TURTLE_ALPHA);

        canvasD.show();
        editorD.show();
    }

    public void initMenu() {
        FXEditor fxEditor = (FXEditor) editor;

        Menu runMenu = new Menu("Run");
        MenuItem run = new MenuItem("Run");
        MenuItem runSelected = new MenuItem("Run Selected");
        MenuItem stop = new MenuItem("Stop");

        KeyCombination.Modifier mod = (os.getType() == OS.Type.MAC) ? KeyCombination.META_DOWN : KeyCombination.CONTROL_DOWN;
        run.setAccelerator(new KeyCodeCombination(KeyCode.R, mod));
        runSelected.setAccelerator(new KeyCodeCombination(KeyCode.ENTER, mod));

        run.setOnAction(event -> {
            String text = editor.getText();
            run(text);
        });

        runSelected.setOnAction(event -> {
            String text = editor.getSelectedText();
            if (text.isEmpty()) text = editor.getCurrentLine();
            run(text);
        });

        stop.setOnAction(event -> {
            if (running == null) return;
            running.interrupt();
            running = null;
        });

        runMenu.getItems().addAll(run, runSelected, stop);

        Menu window = new Menu("Window");
        MenuItem canvas = new MenuItem("Show canvas");
        MenuItem procedures = new MenuItem("Show procedures");

        canvas.setOnAction(event -> {
            FXCanvas c = (FXCanvas) this.canvas;
            Stage s = (Stage) c.getNode().getScene().getWindow();
            if (!s.isShowing()) s.show();
        });

        procedures.setOnAction(event -> {
            proceduresDisplay.show();
        });

        window.getItems().addAll(canvas, procedures);

        fxEditor.getMenuBar().getMenus().addAll(runMenu, window);
    }

    public void run(String code) {
        if (running != null) return;
        running = new Thread(() -> {
            Script script = parser.parseSafe(code);
            if (script != null) executor.executeSafe(script);
            running = null;
        });
        running.start();
    }

    public Value runRaw(String code) throws ParsingException, ExecutorException {
        Script script = parser.parse(code);
        if (script != null) return executor.execute(script);
        return null;
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public Turtle getTurtle() {
        return turtle;
    }

    public Parser getParser() {
        return parser;
    }

    public Executor getExecutor() {
        return executor;
    }

    TurtleGraphics getTurtleGraphics() {
        return turtleGraphics;
    }

    Position getStart() {
        return start;
    }

    void setStart(Position start) {
        this.start = start;
    }
}