package cabare.controller;

import cabare.dto.EmployeeDto;

import cabare.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @RequestMapping(value = "/by_id", method = RequestMethod.GET)
  public EmployeeDto getById(Long id) {
    return employeeService.getById(id);
  }

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<EmployeeDto> getAllWaiters() {
    return employeeService.getAllWaiters();
  }

  @RequestMapping(value = "/enabled", method = RequestMethod.GET)
  public List<EmployeeDto> getAllEnabledWaiters() {
    return employeeService.getAllEnabledWaiters();
  }

  @RequestMapping(value = "/enable", method = RequestMethod.POST)
  public void enable(@RequestParam(name = "id") Long id) {
    employeeService.enable(id);
  }

  @RequestMapping(value = "/disable", method = RequestMethod.POST)
  public void disable(@RequestParam(name = "id") Long id) {
    employeeService.disable(id);
  }
}
