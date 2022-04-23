package lab5.exceptions;

public class IdentifierError extends RuntimeException {
    public IdentifierError() {
        super("Ошибка идентификатора");
    }
}

