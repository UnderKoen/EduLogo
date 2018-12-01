package nl.edulogo.acslogo;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import nl.edulogo.acslogo.script.Script;
import nl.edulogo.acslogo.script.commandos.CommandoHandler;
import nl.edulogo.acslogo.script.executor.Executor;
import nl.edulogo.acslogo.script.parser.Parser;
import nl.edulogo.core.Canvas;
import nl.edulogo.core.Color;
import nl.edulogo.core.Position;
import nl.edulogo.core.Size;
import nl.edulogo.display.fx.FXCanvas;
import nl.edulogo.display.fx.FXDisplay;
import nl.edulogo.editor.Editor;
import nl.edulogo.editor.fx.FXEditor;
import nl.edulogo.logo.AdvancedLogo;
import nl.edulogo.logo.Turtle;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class ACSLogo extends AdvancedLogo {
    private static OS os = new OS();

    private CommandoHandler commandoHandler;
    private ConsoleHandler consoleHandler;
    private Parser parser;
    private Executor executor;
    private Editor editor;

    private Turtle turtle;
    private Canvas canvas;

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
        registerCommands();

        executor = new Executor(consoleHandler, commandoHandler);
        parser = new Parser(consoleHandler, executor);
    }

    public void registerCommands() {
        commandoHandler.registerCommandos(Commandos.getCommandos(this));
    }

    public void initLogo(Size size, Size editorSize) {
        canvas = new FXCanvas(size);
        turtle = new Turtle(new Position(size.getWidth() / 2, size.getHeight() / 2), 0);
        canvas.fillScreen(Color.WHITE);

        editor = new FXEditor();

        FXDisplay<FXCanvas> canvasD = new FXDisplay<>((FXCanvas) canvas);
        FXDisplay<FXEditor> editorD = new FXDisplay<>(editorSize, (FXEditor) editor);

        canvasD.show();
        editorD.show();
    }

    public void initMenu() {
        FXEditor fxEditor = (FXEditor) editor;

        Menu runMenu = new Menu("Run");
        MenuItem run = new MenuItem("Run");
        MenuItem runSelected = new MenuItem("Run Selected");

        KeyCombination.Modifier mod = (os.getType() == OS.Type.MAC)? KeyCombination.META_DOWN : KeyCombination.CONTROL_DOWN;
        run.setAccelerator(new KeyCodeCombination(KeyCode.R,mod));
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

        runMenu.getItems().addAll(run, runSelected);

        fxEditor.getMenuBar().getMenus().addAll(runMenu);
    }

    public void run(String code) {
        Script script = parser.parseSafe(code);
        executor.executeSafe(script);
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