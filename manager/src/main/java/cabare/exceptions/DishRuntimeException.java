package cabare.exceptions;

public class DishRuntimeException extends ApplicationException {

  private static String msg = "there is a sale for this dish";

  public DishRuntimeException() {
    super(msg);
  }

  public DishRuntimeException(String message) {
    super(msg + "; " + message);
  }
}
