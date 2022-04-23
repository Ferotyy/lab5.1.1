package lab5.exceptions;

public class FileNotExistsException extends FileException{
    public FileNotExistsException(){
        super("cannot find file");
    }
}