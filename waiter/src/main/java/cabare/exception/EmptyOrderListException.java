package cabare.exception;

public class EmptyOrderListException extends ApplicationException {

  private static String msg = "order list is empty";

  public EmptyOrderListException() {
    super(msg);
  }

  public EmptyOrderListException(String message) {
    super(msg + "; " + message);
  }
}
