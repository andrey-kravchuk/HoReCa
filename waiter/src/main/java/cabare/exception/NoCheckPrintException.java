package cabare.exception;

public class NoCheckPrintException extends ApplicationException {

  private static String msg = "it's need to checkprint before";

  public NoCheckPrintException() {
    super(msg);
  }

  public NoCheckPrintException(String message) {
    super(msg + "; " + message);
  }
}
