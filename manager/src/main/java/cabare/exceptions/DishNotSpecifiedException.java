package cabare.exceptions;

public class DishNotSpecifiedException extends ApplicationException {

  private static String msg = "dish id was not specified";

  public DishNotSpecifiedException() {
    super(msg);
  }

  public DishNotSpecifiedException(String message) {
    super(msg + "; " + message);
  }
}
