package lab5.exceptions;

public class EmptyPathException extends FileException {
    public EmptyPathException() {
        super("path is empty");
    }
}
