/**
 * Created by Under_Koen on 04/10/2018.
 */
module edulogo.acslogo {
    requires edulogo.logo;
    requires edulogo.core;
    requires edulogo.display;
    requires edulogo.editor;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.scripting;

    exports nl.edulogo.acslogo;
    exports nl.edulogo.acslogo.script;
    exports nl.edulogo.acslogo.script.arguments to junit;
}