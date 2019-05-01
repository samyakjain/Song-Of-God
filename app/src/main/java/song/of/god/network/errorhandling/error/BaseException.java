package song.of.god.network.errorhandling.error;

import java.io.IOException;

public class BaseException extends IOException {

    public BaseException(String message){
        super(message);
    }
}
