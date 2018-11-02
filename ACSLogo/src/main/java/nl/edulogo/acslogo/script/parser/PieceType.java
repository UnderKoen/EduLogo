package nl.edulogo.acslogo.script.parser;

/**
 * Created by Under_Koen on 02/11/2018.
 */
public enum PieceType {
    STRING,     // "hey\ guys, "test
    NUMBER,     // 10, 20.4, 0.1
    BOOLEAN,    // "true, "false, true, false
    VARIABLE,   // :test, :x, :y
    OPERATOR,   // =, >, <
    CALCULATION, // +, -, *, /
    LIST,       // [10 "de 10]
    STATEMENT,  // (10 * 10)
    COMMANDO    // forward
}
