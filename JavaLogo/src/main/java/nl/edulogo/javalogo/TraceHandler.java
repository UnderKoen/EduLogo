package nl.edulogo.javalogo;

import java.util.ArrayList;
import java.util.List;

public class TraceHandler {
    private List<Trace> traces;
    private int index;

    public TraceHandler() {
        traces = new ArrayList<>();
        index = 0;
    }

    public void addTrace(Trace trace) {
        traces.add(trace);
        System.out.println(trace.toString());
    }
}
