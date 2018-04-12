package cabare.exception;

public class CabareNotFoundException extends ApplicationException {

    private static String msg = "cabare is not found";

    public CabareNotFoundException() { super(msg); }

    public CabareNotFoundException(String message) { super(msg + ";" + message); }
}
