package cabare.exceptions;

public class ZoneNotFoundException extends ApplicationException {
    private static String msg = "zone is not found";

    public ZoneNotFoundException(){
        super(msg);
    }

    public ZoneNotFoundException(String message){
        super(msg + "; " + message);
    }
}
