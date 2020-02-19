package domain;

public class KugaDomainException extends Exception {

    public KugaDomainException(String message) {
        super(message);
    }

    public KugaDomainException(String message, Object input) {
        super(message);
    }
}
