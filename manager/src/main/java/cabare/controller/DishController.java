package cabare.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/manager")
public class DishController {

  @RequestMapping(value = "/test")
  public String test(){
    return "test";
  }
}