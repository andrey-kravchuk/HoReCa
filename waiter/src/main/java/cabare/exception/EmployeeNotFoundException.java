package cabare.exception;

public class EmployeeNotFoundException extends ApplicationException {

  private static String msg = "employee is not found";

  public EmployeeNotFoundException() {
    super(msg);
  }

  public EmployeeNotFoundException(String message) {
    super(msg + "; " + message);
  }
}
