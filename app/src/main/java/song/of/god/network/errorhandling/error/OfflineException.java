package song.of.god.network.errorhandling.error;

public class OfflineException extends BaseException {

    public OfflineException() {
        super("Client is offline");
    }
}
