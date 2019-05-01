package song.of.god.network.errorhandling.error;

public class InternalServerException extends BaseException {

    public InternalServerException() {
        super("Internal Server Exception");
    }

    public InternalServerException(String msg) {
        super(msg);
    }
}
