package cabare.exception;

public class BillNotFoundException extends ApplicationException {

  private static String msg = "bill is not found";

  public BillNotFoundException() {
    super(msg);
  }

  public BillNotFoundException(String message) {
    super(msg + "; " + message);
  }
}
