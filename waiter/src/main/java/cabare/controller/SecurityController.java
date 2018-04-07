package cabare.controller;

import cabare.dto.WaiterDto;
import cabare.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecurityController {

  @Autowired
  private SecurityService securityService;

  @RequestMapping(value = "/waiter/user_data")
  @ResponseBody
  public WaiterDto login() {
    return new WaiterDto(securityService.getEmployeeFromSession());
  }
}
