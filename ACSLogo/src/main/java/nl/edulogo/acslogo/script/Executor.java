package nl.edulogo.acslogo.script;

/**
 * Created by Under_Koen on 24/10/2018.
 */
public class Executor {
    public void test() {
        /*
        for (int i = 0; i < pieces.length; i++) {
            String piece = pieces[i];
            PieceType type = types[i];

            System.out.println(piece + " -=- " + type + " -=- " + i);
            if (type == PieceType.COMMANDO) {
                Commando cmd = commandoHandler.getCommando(piece);
                if (cmd == null) continue; //TODO error shit

                ArgumentType[] argumentTypes = cmd.getArguments();

                if (i + argumentTypes.length + 1 > pieces.length) {
                    throw new IllegalArgumentException(String.format("To little arguments for commando: %s", cmd.getName()));
                }

                Argument[] arguments = new Argument[argumentTypes.length];
                for (int a = 0; a < argumentTypes.length; a++) {
                    ArgumentType aType = argumentTypes[a];
                    String aPiece = pieces[i + a + 1];
                    PieceType aPieceType = types[i + a + 1];

                    System.out.println(aPieceType);

                    Argument argument = null;

                    if (aPieceType == PieceType.NUMBER) {
                        argument = parseNumber(aPiece);
                    } else if (aPieceType == PieceType.STATEMENT) {
                        argument = parseStatement(aPiece);
                    }

                    if (argument != null && argument.getType() == aType) {
                        arguments[a] = argument;
                    } else {
                        //TODO error shit
                    }

                    //TODO for the other types
                }

                cmd.call(arguments);
            }
        }*/
    }
}
