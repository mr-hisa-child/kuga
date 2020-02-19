package application;

public class KugaApplicationException extends Exception {
    public KugaApplicationException(String message) {
        super(message);
    }

    public KugaApplicationException(String message, Object input) {
        super(message);
    }
}
