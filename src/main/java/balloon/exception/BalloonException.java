package balloon.exception;

public class BalloonException extends Exception {
    private String message;

    public BalloonException() {}

    public BalloonException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
