public class UnknownCommandException extends BalloonException {

    public UnknownCommandException(String firstWord) {
        super("Unrecognised command received. <" + firstWord + "> is not a valid command");
    }
}
