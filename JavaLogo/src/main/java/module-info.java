module edulogo.javalogo {
    requires edulogo.core;
    requires edulogo.display;
    requires edulogo.logo;

    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;

    exports nl.edulogo.javalogo;
    exports nl.edulogo.javalogo.variabele;
}