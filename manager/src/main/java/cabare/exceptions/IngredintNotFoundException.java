package cabare.exceptions;

public class IngredintNotFoundException extends ApplicationException {

  private static String msg = "ingredient was not found";

  public IngredintNotFoundException() {
    super(msg);
  }

  public IngredintNotFoundException(String message) {
    super(msg + "; " + message);
  }
}
