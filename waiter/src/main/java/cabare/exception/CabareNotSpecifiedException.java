package cabare.exception;

public class CabareNotSpecifiedException extends ApplicationException {

  private static String msg = "cabare id is not specified";

  public CabareNotSpecifiedException() {
    super(msg);
  }

  public CabareNotSpecifiedException(String message) {
    super(msg + "; " + message);
  }
}
