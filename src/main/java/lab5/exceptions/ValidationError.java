package lab5.exceptions;

public class ValidationError extends Exception {
    public ValidationError(String message) {
        super(message);
    }

    public ValidationError() {
        super();
    }
}
