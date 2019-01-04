package nl.edulogo.acslogo.handlers;

public class Waitable<T> {
    private T value;
    private boolean active;

    public Waitable() {
        active = false;
    }

    public void setDone(T value) {
        this.value = value;
        active = false;
    }

    public boolean isDone() {
        return !active && value != null;
    }

    public void start() {
        active = true;
    }

    public void stop() {
        value = null;
        active = false;
    }

    public T reset() {
        T old = value;
        stop();
        return old;
    }
}
