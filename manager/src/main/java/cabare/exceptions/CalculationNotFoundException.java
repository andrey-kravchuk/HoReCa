package cabare.exceptions;

public class CalculationNotFoundException extends ApplicationException {

  private static String msg = "calculation was not found";

  public CalculationNotFoundException() {
    super(msg);
  }

  public CalculationNotFoundException(String message) {
    super(msg + "; " + message);
  }
}
