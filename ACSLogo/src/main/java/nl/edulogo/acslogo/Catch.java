package nl.edulogo.acslogo;

public class Catch extends RuntimeException {
    String error;

    public Catch(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
