package nl.edulogo.acslogo.script;

import nl.edulogo.acslogo.script.parser.Piece;

import java.util.List;

/**
 * Created by Under_Koen on 04/10/2018.
 */
public class Script {
    List<Piece> pieces;

    public Script(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}