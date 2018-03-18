package cabare.exception;

public class DiscountNotFoundException extends ApplicationException {

  private static String msg = "discount card is not found";

  public DiscountNotFoundException() {
    super(msg);
  }

  public DiscountNotFoundException(String message) {
    super(msg + "; " + message);
  }
}
