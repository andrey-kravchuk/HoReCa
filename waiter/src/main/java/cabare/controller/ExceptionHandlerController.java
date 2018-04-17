package cabare.controller;

import cabare.exception.ApplicationException;
import cabare.exception.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Controller
public class ExceptionHandlerController {

  private Map err(Object msg) {
    return Collections.singletonMap("error", msg);
  }

  @ExceptionHandler(ApplicationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public Map handleException(ApplicationException e) {
    return err(e.getMessage());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public byte[] handleException(ResourceNotFoundException e) {
    return new byte[0];
  }

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Map handle(MethodArgumentNotValidException ex) {
    ex.printStackTrace();
    return err(ex.getBindingResult().getAllErrors().stream()
        .map(er -> er.getDefaultMessage())
        .collect(Collectors.toList()));
  }

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Map handle(ConstraintViolationException ex) {
    ex.printStackTrace();
    return err(ex.getConstraintViolations().stream()
        .map(er -> er.getMessage())
        .collect(Collectors.toList()));
  }
}