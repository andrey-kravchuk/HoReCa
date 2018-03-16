package cabare.service;


import cabare.entity.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class SecurityService {

  @Autowired
  private EmployeeService employeeService;

  private Employee employee;

  public Employee getEmployeeFromSession() {
//    String login = SecurityContextHolder.getContext().getAuthentication().getName();
//    return employeeService.getEmployeeByLogin(login);
    return employee;
  }

  public void authorizeEmployee(Long id) {
    employee = employeeService.getById(id);
  }
}
