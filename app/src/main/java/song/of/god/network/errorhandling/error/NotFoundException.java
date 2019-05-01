package song.of.god.network.errorhandling.error;

public class NotFoundException extends BaseException {

    public NotFoundException() {
        super("Not Found Exception");
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
