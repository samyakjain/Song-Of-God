package song.of.god.network.errorhandling.error;

public class ForbiddenException extends BaseException {

    public ForbiddenException() {
        super("Forbidden Exception");
    }

    public ForbiddenException(String msg) {
        super(msg);
    }
}
