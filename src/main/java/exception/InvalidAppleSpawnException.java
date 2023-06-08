package exception;

public class InvalidAppleSpawnException extends RuntimeException{
    public InvalidAppleSpawnException() {
        super();
    }

    public InvalidAppleSpawnException(String message) {
        super(message);
    }
}
