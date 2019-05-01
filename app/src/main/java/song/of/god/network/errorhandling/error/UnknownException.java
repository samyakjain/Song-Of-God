package song.of.god.network.errorhandling.error;

public class UnknownException extends BaseException {

    public UnknownException() {
        super("Error Occured");
    }

    public UnknownException(String msg) {
        super(msg);
    }
}

