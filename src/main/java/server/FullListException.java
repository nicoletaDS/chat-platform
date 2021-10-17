package server;

public class FullListException extends IllegalStateException{
    public FullListException(String errorMessage) {
        super(errorMessage);
    }
}
