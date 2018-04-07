package cabare.service;


import cabare.entity.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

  @Autowired
  private EmployeeService employeeService;

  public Employee getEmployeeFromSession() {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    return employeeService.findByEmail(email);
  }
}
