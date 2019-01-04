package nl.edulogo.javalogo;

public class Trace {
    private TraceSoort traceSoort;
    private Object[] traceValues;

    public Trace(TraceSoort soort, Object... values) {
        traceSoort = soort;
        traceValues = values;
    }

    public TraceSoort getSoort() {
        return traceSoort;
    }

    public Object[] getValues() {
        return traceValues;
    }

    @Override
    public String toString() {
        String show = traceSoort.name + "(";
        boolean first = true;
        for (Object o : traceValues) {
            if (!first) show += ", ";

            show += o.toString();
            first = false;
        }
        return show + ")";
    }

    public enum TraceSoort {
        VOORUIT("vooruit"), PENUIT("penuit"), RECHTS("rechts"), LINKS("links"), PENAAN("penaan"), STAP("stap"), SCHRIJF("schrijf"), VULAAN("vulaan"), VULUIT("vuluit");

        String name;

        TraceSoort(String naam) {
            name = naam;
        }
    }

}
