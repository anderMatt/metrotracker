package metrotracker;

public class WmataApiException extends Exception {
    public WmataApiException(String msg) {
        super(msg);
    }

    public WmataApiException(Throwable e) {
        super(e);
    }

    public WmataApiException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
