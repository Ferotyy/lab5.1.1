package lab5.exceptions;

public class RecursionException extends Exception{
    public RecursionException(){
        super("При выполнении скрипта возникает рекурсия");
    }
}
