package balloon.exception;

public class UnknownCommandException extends BalloonException {
    public UnknownCommandException() {
        super("Unrecognised command received");
    }
}
