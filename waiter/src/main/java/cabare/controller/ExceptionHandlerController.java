package cabare.controller;

import cabare.exception.ApplicationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
@RestController
public class ExceptionHandlerController {

  private Map err(String msg) {
    return Collections.singletonMap("error", msg);
  }

  @ExceptionHandler(ApplicationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Map handleException(ApplicationException e) {
    return err(e.getMessage());
  }
}