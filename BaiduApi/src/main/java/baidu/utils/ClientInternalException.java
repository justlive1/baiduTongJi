package baidu.utils;

public class ClientInternalException extends RuntimeException {

    private static final long serialVersionUID = -4878447156010807963L;

    public ClientInternalException() {
        super();
    }

    public ClientInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientInternalException(String message) {
        super(message);
    }

    public ClientInternalException(Throwable cause) {
        super(cause);
    }

}
