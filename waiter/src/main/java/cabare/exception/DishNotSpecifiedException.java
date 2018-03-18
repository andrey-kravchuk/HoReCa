package cabare.exception;

public class DishNotSpecifiedException extends ApplicationException {

  private static String msg = "dish id is not sppecified";

  public DishNotSpecifiedException() {
    super(msg);
  }

  public DishNotSpecifiedException(String message) {
    super(msg + "; " + message);
  }
}
