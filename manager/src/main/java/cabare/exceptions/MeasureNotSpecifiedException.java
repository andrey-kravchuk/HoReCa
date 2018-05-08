package cabare.exceptions;

public class MeasureNotSpecifiedException extends ApplicationException {

  private static String msg = "measure id is not specified";

  public MeasureNotSpecifiedException() {
    super(msg);
  }

  public MeasureNotSpecifiedException(String message) {
    super(msg + "; " + message);
  }
}
