package cabare.controller;

import cabare.exception.ApplicationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.Map;

public class ExceptionHandlerController {

  private Map err(String msg) {
    return Collections.singletonMap("error", msg);
  }

  @ExceptionHandler(ApplicationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map handleException(ApplicationException e) {
    return err(e.getMessage());
  }
}