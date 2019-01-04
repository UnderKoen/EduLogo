package nl.edulogo.javalogo;

public class AnimationHandler {

    private boolean animation;
    private boolean mogelijk;
    private TekenApplet applet;
    private Thread animationThread;

    public AnimationHandler(TekenApplet applet) {
        animation = false;
        mogelijk = false;
        this.applet = applet;
    }

    public boolean isAnimation() {
        return animation;
    }

    public boolean isMogelijk() {
        return mogelijk;
    }

    public void maakMogelijk() {
        mogelijk = true;
    }

    public void startAnimation() {
        if (isAnimation() || !isMogelijk()) return;
        animationThread = new Thread(applet::animatie);
        animationThread.start();
        animation = true;
    }

    public void stopAnimation() {
        if (!animation || !isMogelijk()) return;
        animationThread.interrupt();
        animation = false;
    }

}
