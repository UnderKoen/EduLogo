package nl.edulogo.javalogo;

public class AnimationHandler {

    private boolean animation;
    private TekenApplet applet;
    private Thread animationThread;

    public AnimationHandler(TekenApplet applet) {
        animation = false;
        this.applet = applet;
    }

    public boolean isAnimation() {
        return animation;
    }

    public void startAnimation() {
        if (animation) return;
        animationThread = new Thread(applet::animatie);
        animation = true;
    }

    public void stopAnimatie() {
        if (!animation) return;
        animationThread.interrupt();
        animation = false;
    }

}
