package cabare.exception;

public class EmployeeNotSpecifiedException extends ApplicationException {

  private static String msg = "employee is not specified";

  public EmployeeNotSpecifiedException() {
    super(msg);
  }

  public EmployeeNotSpecifiedException(String message) {
    super(msg + "; " + message);
  }
}
