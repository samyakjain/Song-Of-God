package song.of.god.network.errorhandling.error;

public class BadRequestException extends BaseException {

    public BadRequestException() {
        super("Bad Request Exception");
    }

    public BadRequestException(String msg) {
        super(msg);
    }
}
