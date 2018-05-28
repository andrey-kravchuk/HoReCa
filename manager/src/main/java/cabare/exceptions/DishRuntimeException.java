package cabare.exceptions;

public class DishRuntimeException extends ApplicationException {

  private static String msg = "";

  public DishRuntimeException() {
    super(msg);
  }

  public DishRuntimeException(String message) {
    super(msg + "; " + message);
  }
}
