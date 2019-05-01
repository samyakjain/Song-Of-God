package song.of.god.network.errorhandling.error;

public class BadGatewayException extends BaseException {

    public BadGatewayException() {
        super("Bad Gateway Exception");
    }

    public BadGatewayException(String msg) {
        super(msg);
    }
}
