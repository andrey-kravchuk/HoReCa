package cabare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ApplicationException {

  private static String msg = "resource is not found";

  public ResourceNotFoundException() {
    super(msg);
  }

  public ResourceNotFoundException(String message) {
    super(msg + "; " + message);
  }
}
