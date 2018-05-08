package cabare.exceptions;

public class MeasureNotFoundException extends ApplicationException {

  private static String msg = "measure was not found";

  public MeasureNotFoundException() {
    super(msg);
  }

  public MeasureNotFoundException(String message) {
    super(msg + "; " + message);
  }
}
