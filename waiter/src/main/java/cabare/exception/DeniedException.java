package cabare.exception;

public class DeniedException extends ApplicationException {

  private static String msg = "bill can be changed by owner only";

  public DeniedException() {
    super(msg);
  }

  public DeniedException(String message) {
    super(msg + "; " + message);
  }
}
