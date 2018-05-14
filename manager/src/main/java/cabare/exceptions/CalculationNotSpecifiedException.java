package cabare.exceptions;

public class CalculationNotSpecifiedException extends ApplicationException {

  private static String msg = "calculation id was not specified";

  public CalculationNotSpecifiedException() {
    super(msg);
  }

  public CalculationNotSpecifiedException(String message) {
    super(msg + "; " + message);
  }
}
