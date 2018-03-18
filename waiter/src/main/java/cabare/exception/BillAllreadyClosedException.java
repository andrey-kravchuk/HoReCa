package cabare.exception;

public class BillAllreadyClosedException extends ApplicationException {

  private static String msg = "bill has already closed";

  public BillAllreadyClosedException() {
    super(msg);
  }

  public BillAllreadyClosedException(String message) {
    super(msg + "; " + message);
  }
}
