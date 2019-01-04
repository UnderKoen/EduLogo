package nl.edulogo.javalogo;

public class TraceHandler {
    private String[] trace;
    private boolean animation;
    private int index;

    public TraceHandler() {
        trace = new String[100];
        index = 0;
    }

    public void addTrace(String totrace) {
        /*
        //TODO: gwn echt werkend maken, nu slaat het alleen nog maar op
        System.out.println(totrace);
        trace[index] = totrace;
        System.out.println("lenght of thingie: " + index + " en daarbij hoort: " + trace[index]);
        index++;
        */
    }

    public boolean isAnimation() {
        return animation;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
    }
}
