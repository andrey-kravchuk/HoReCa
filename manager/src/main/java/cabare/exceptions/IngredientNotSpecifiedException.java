package cabare.exceptions;

public class IngredientNotSpecifiedException extends ApplicationException {
  private static String msg = "ingredient id was not specified";

  public IngredientNotSpecifiedException() {
  super(msg);
  }

  public IngredientNotSpecifiedException(String message) {
    super(msg + "; " + message);
  }
}
