package nl.edulogo.acslogo;

import javafx.event.Event;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nl.edulogo.acslogo.display.Procedures;
import nl.edulogo.acslogo.display.TurtleGraphics;
import nl.edulogo.acslogo.display.fx.FXProcedures;
import nl.edulogo.acslogo.display.fx.FXTurtleGraphics;
import nl.edulogo.acslogo.handlers.ColorHandler;
import nl.edulogo.acslogo.handlers.CommandoHandler;
import nl.edulogo.acslogo.handlers.ConsoleHandler;
import nl.edulogo.acslogo.handlers.MouseHandler;
import nl.edulogo.acslogo.handlers.procedures.Procedure;
import nl.edulogo.acslogo.handlers.procedures.ProcedureHandler;
import nl.edulogo.acslogo.handlers.variable.LocalVariableHandler;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class ACSLogo extends AdvancedLogo {
    private static final int TURTLE_SIZE = 48;
    private static final double TURTLE_ALPHA = 0.8;
    private static Server server = new Server(8070);

    static {
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static OS os = new OS();

    private Document document;

    private CommandoHandler commandoHandler;
    private ConsoleHandler consoleHandler;
    ColorHandler colorHandler;
    MouseHandler mouseHandler;
    ProcedureHandler procedureHandler;
    PropertyHandler propertyHandler;
    private LocalVariableHandler current = null;
    private Parser parser;
    private Executor executor;
    private Editor editor;
    private TurtleGraphics<FXCanvas> turtleGraphics;
    private Position start;
    private MenuItem stop;

    private Turtle turtle;
    private Canvas canvas;
    private Thread running;

    private Display<? extends Procedures> proceduresDisplay;
    private Display<? extends Canvas> canvasDisplay;
    private Display<? extends Editor> editorDisplay;

    public ACSLogo(Document document) {
        this.document = document;

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

        executor = new Executor(consoleHandler, commandoHandler);
        parser = new Parser(consoleHandler, executor, this);

        load(document);
    }

    public void setTitles() {
        proceduresDisplay.setTitle(String.format("%s - Editor", document.getDocumentName()));
        canvasDisplay.setTitle(String.format("%s - Canvas", document.getDocumentName()));
        editorDisplay.setTitle(String.format("%s - Editor", document.getDocumentName()));
    }

    public void registerCommands() {
        commandoHandler.registerCommandos(new Commandos(this).getCommandos());
    }

    public void initLogo(Size size, Size editorSize) {
        canvas = new FXCanvas(size);
        turtleGraphics = new FXTurtleGraphics(TURTLE_SIZE);
        start = new Position(size.getWidth() / 2.0, size.getHeight() / 2.0);
        turtle = new ACSTurtle(start, 0, this);
        canvas.fillScreen(Color.WHITE);

        editor = new FXEditor();

        FXDisplay<FXCanvas> canvasD = new FXDisplay<>((FXCanvas) canvas);
        canvasDisplay = canvasD;

        FXDisplay<FXEditor> editorD = new FXDisplay<>(editorSize, (FXEditor) editor);
        editorD.getStage().setOnCloseRequest(this::close);
        editorDisplay = editorD;

        turtleGraphics.addToCanvas((FXCanvas) canvas);
        turtleGraphics.setImage("turtle.png");
        turtleGraphics.setAlpha(TURTLE_ALPHA);

        canvas.setLineCap(LineCap.BUTT);

        canvasD.show();
        editorD.show();
    }

    public void initMenu() {
        KeyCombination.Modifier mod = (os.getType() == OS.Type.MAC) ? KeyCombination.META_DOWN : KeyCombination.CONTROL_DOWN;

        Menu fileMenu = new Menu("File");
        Menu runMenu = new Menu("Run");
        Menu window = new Menu("Window");

        MenuItem newF = new MenuItem("New");
        MenuItem open = new MenuItem("Open...");
        MenuItem close = new MenuItem("Close");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save as...");

        fileMenu.getItems().addAll(newF, open, new SeparatorMenuItem(), close, save, saveAs);

        MenuItem run = new MenuItem("Run");
        MenuItem runSelected = new MenuItem("Run Selected");
        stop = new MenuItem("Stop");

        runMenu.getItems().addAll(run, runSelected, stop);

        MenuItem canvas = new MenuItem("Show canvas");
        MenuItem procedures = new MenuItem("Show procedures");

        window.getItems().addAll(canvas, procedures);

        newF.setAccelerator(new KeyCodeCombination(KeyCode.N, mod));
        newF.setOnAction(this::newFile);

        open.setAccelerator(new KeyCodeCombination(KeyCode.O, mod));
        open.setOnAction(this::open);

        close.setAccelerator(new KeyCodeCombination(KeyCode.W, mod));
        close.setOnAction(this::close);

        save.setAccelerator(new KeyCodeCombination(KeyCode.S, mod));
        save.setOnAction(this::save);

        saveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, mod, KeyCombination.SHIFT_DOWN));
        saveAs.setOnAction(this::saveAs);

        run.setAccelerator(new KeyCodeCombination(KeyCode.R, mod));
        run.setOnAction(this::run);

        runSelected.setAccelerator(new KeyCodeCombination(KeyCode.ENTER, mod));
        runSelected.setOnAction(this::runSelected);

        stop.setDisable(true);
        stop.setOnAction(this::stop);

        canvas.setOnAction(this::showCanvas);

        procedures.setOnAction(this::showProcedures);

        FXEditor fxEditor = (FXEditor) editor;
        fxEditor.getMenuBar().getMenus().addAll(fileMenu, runMenu, window);
    }

    public void load(Document document) {
        this.document = document;
        editor.setText(document.getCode());
        document.getProcedures().forEach(procedure -> {
            procedure.setLogo(this);
            procedureHandler.registerProcedure(procedure);
        });
        proceduresDisplay.getView().reload();
        updateDocument();
    }

    private void updateDocument() {
        document.setCode(editor.getText());
        document.setProcedures(new ArrayList<>(procedureHandler.getProcedures().values()));
        if (document.getOrgin() != null) {
            document.setDocumentName(document.getOrgin().getName());
        }
        setTitles();
    }

    private void newFile(Event event) {
        new ACSLogo(new Document());
    }

    private void open(Event event) {
        updateDocument();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("EduLogo", "*.edul", "*.edus"));
        File file = fileChooser.showOpenDialog(((FXDisplay) editorDisplay).getStage());
        try {
            if (document.getCode().isEmpty() && document.getProcedures().isEmpty()) {
                document = new Document(file);
                load(document);
            } else {
                new ACSLogo(new Document(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close(Event event) {
        ((FXDisplay) editorDisplay).getStage().close();
        ((FXDisplay) canvasDisplay).getStage().close();
        ((FXDisplay) proceduresDisplay).getStage().close();
    }

    private void save(Event event) {
        if (document.getOrgin() == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("EduLogo", "*.edul", "*.edus"));
            File file = fileChooser.showSaveDialog(((FXDisplay) editorDisplay).getStage());
            document.setOrgin(file);
        }
        updateDocument();
        try {
            document.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveAs(Event event) {
        document.setOrgin(null);
        save(null);
    }

    private void run(Event event) {
        String text = editor.getText();
        run(text);
    }

    private void runSelected(Event event) {
        String text = editor.getSelectedText();
        if (text.isEmpty()) text = editor.getCurrentLine();
        run(text);
    }

    private void stop(Event event) {
        if (running == null) return;
        running.interrupt();
        running = null;
        stop.setDisable(true);
    }

    private void showCanvas(Event event) {
        FXCanvas c = (FXCanvas) this.canvas;
        Stage s = (Stage) c.getNode().getScene().getWindow();
        if (!s.isShowing()) s.show();
    }

    private void showProcedures(Event event) {
        proceduresDisplay.show();
        proceduresDisplay.getView().reload();
    }

    public void run(String code) {
        if (running != null) return;
        running = new Thread(() -> {
            Script script = parser.parseSafe(code);
            if (script != null) executor.executeSafe(script);
            running = null;
            stop.setDisable(true);
        });
        stop.setDisable(false);
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

    public LocalVariableHandler getCurrent() {
        return current;
    }

    public void setCurrent(LocalVariableHandler current) {
        this.current = current;
    }

    @Override
    public void forward(double amount) {
        super.forward(amount);
        if (amount < 0) {
            server.back(-amount);
        } else {
            server.forward(amount);
        }
    }

    @Override
    public void right(double rotation) {
        super.right(rotation);
        if (rotation < 0) {
            server.left(-rotation);
        } else {
            server.right(rotation);
        }
    }
}