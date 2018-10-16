package nl.edulogo.acslogo.script.arguments;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Under_Koen on 15/10/2018.
 */
public class ArgumentTest {
    @Test
    public void typeTest() {
        Argument str = new Argument<>("Test");
        assertEquals(ArgumentType.STRING, str.getType());

        Argument boolT = new Argument<>(true);
        assertEquals(ArgumentType.BOOLEAN, boolT.getType());

        Argument boolF = new Argument<>(false);
        assertEquals(ArgumentType.BOOLEAN, boolF.getType());

        Argument numI = new Argument<>(10);
        assertEquals(ArgumentType.NUMBER, numI.getType());

        Argument numD = new Argument<>(13.37);
        assertEquals(ArgumentType.NUMBER, numD.getType());

        Argument numF = new Argument<>(1200.2f);
        assertEquals(ArgumentType.NUMBER, numF.getType());

        Argument numL = new Argument<>(9900000000000000L);
        assertEquals(ArgumentType.NUMBER, numL.getType());

        try {
            new Argument<>(str);
            fail();
        } catch (Exception ignored) {
        }

        try {
            new Argument<>(ArgumentType.NUMBER);
            fail();
        } catch (Exception ignored) {
        }
    }
}