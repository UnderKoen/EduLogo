package nl.edulogo.acslogo.script;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 04/10/2018.
 */
public class Script {
    private String code;
    private List<Line> lines;

    public Script(String code) {
        this.code = code;
        this.lines = new ArrayList<>();
        for (String line : code.split("\n")) {
            lines.add(new Line(line));
        }
    }

    public Line getLine(int i) {
        return lines.get(i);
    }

    public Line[] getLines() {
        return lines.toArray(new Line[0]);
    }
}
