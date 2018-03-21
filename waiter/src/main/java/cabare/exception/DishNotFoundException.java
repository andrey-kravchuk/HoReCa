package cabare.exception;

public class DishNotFoundException extends ApplicationException {

  private static String msg = "dish is not found";

  public DishNotFoundException() {
    super(msg);
  }

  public DishNotFoundException(String message) {
    super(msg + "; " + message);
  }
}
