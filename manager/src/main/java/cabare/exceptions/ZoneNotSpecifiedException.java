package cabare.exceptions;

public class ZoneNotSpecifiedException extends ApplicationException {

    private static String msg = "zone id is not specified";

    public ZoneNotSpecifiedException() {
        super(msg);
    }

    public ZoneNotSpecifiedException(String message) {
        super(msg + "; " + message);
    }
}
