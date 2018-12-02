package nl.edulogo.acslogo.script.parser.pieces;

import nl.edulogo.acslogo.handlers.CommandoHandler;
import nl.edulogo.acslogo.script.commandos.Commando;
import nl.edulogo.acslogo.script.commandos.Value;
import nl.edulogo.acslogo.script.executor.ExecutorException;
import nl.edulogo.acslogo.script.parser.ParsingException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 07/11/2018.
 */
public class CommandoPiece implements Piece {
    private Commando commando;
    private int index = 0;
    private Piece[] arguments;

    public CommandoPiece(Piece piece, CommandoHandler handler) throws ParsingException {
        if (piece.getType() != PieceType.COMMANDO) throw new IllegalArgumentException();
        commando = handler.getCommando(piece.getPiece());
        if (commando == null) throw new ParsingException(String.format("Commando %s not found", piece.getPiece()));
        arguments = new Piece[commando.getArguments()];
    }

    public static List<Piece> findArguments(List<Piece> pieces) {
        pieces = new ArrayList<>(pieces);
        for (int i = pieces.size() - 1; i >= 0; i--) {
            Piece piece = pieces.get(i);
            CommandoPiece cmd = piece instanceof CommandoPiece ? ((CommandoPiece) piece) : null;
            if (cmd == null && piece instanceof CalculationPiece) {
                CalculationPiece calc = (CalculationPiece) piece;
                Piece mostRight = calc.getMostRight();
                cmd = mostRight instanceof CommandoPiece ? ((CommandoPiece) mostRight) : null;
            }
            if (cmd == null || cmd.arguments.length == 0) continue;
            int size = cmd.arguments.length;
            for (int j = 0; j < size; j++) {
                cmd.addArgument(pieces.remove(i + 1));
            }
        }
        return pieces;
    }

    @Override
    public PieceType getType() {
        return PieceType.COMMANDO;
    }

    public Commando getCommando() {
        return commando;
    }

    public void addArgument(Piece piece) {
        if (index >= arguments.length) return;
        arguments[index] = piece;
        index += 1;
    }

    @Override
    public Value getValue() throws ParsingException, ExecutorException {
        Value[] values = new Value[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            values[i] = arguments[i].getValueSafe();
        }
        return commando.call(values);
    }
}
